package com.example.onecar.service;

import com.example.onecar.config.security.SecurityUtils;
import com.example.onecar.dto.RepairDto;
import com.example.onecar.dto.response.OneCarHttpResponse;
import com.example.onecar.entity.RepairEntity;
import com.example.onecar.entity.UserEntity;
import com.example.onecar.entity.base.BaseStatus;
import com.example.onecar.entity.base.RepairCycle;
import com.example.onecar.entity.base.UserType;
import com.example.onecar.repository.RepairRepository;
import com.example.onecar.repository.UserRepository;
import com.example.onecar.service.I.IRepairService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairService implements IRepairService {
    private final RepairRepository repairRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objMapper;

    public RepairService(RepairRepository repairRepository,
                         UserRepository userRepository,
                         SimpMessagingTemplate messagingTemplate,
                         ObjectMapper objMapper) {
        this.repairRepository = repairRepository;
        this.userRepository = userRepository;
        this.messagingTemplate = messagingTemplate;
        this.objMapper = objMapper;
    }

    @Override
    public OneCarHttpResponse<RepairDto> add(RepairDto item) {
        UserEntity customer = SecurityUtils.getAuthenticatedUser();
        if(customer == null) return null;

        RepairEntity rep = new RepairEntity();
        BeanUtils.copyProperties(item, rep);
        rep.forCreate();
        rep.setCustomerId(customer.getId());
        rep.setCustomer(customer);
        rep.setCarId(item.getCar().getId());
        rep.setRepairCycle(RepairCycle.REQUESTED);
        repairRepository.save(rep);

        return OneCarHttpResponse.<RepairDto>builder()
                .message("Repair car is requested. Wait for manager accepting...")
                .status(OneCarHttpResponse.Status.SUCCESS)
                .object(rep.toDto())
                .build();
    }

    @Override
    public OneCarHttpResponse<RepairDto> update(RepairDto item) {
        UserEntity updatingUser = SecurityUtils.getAuthenticatedUser();
        // determine user type
        if(updatingUser == null || updatingUser.getAuthorities() == null) {
            return OneCarHttpResponse.<RepairDto>builder()
                    .message("You don't have permission!")
                    .status(OneCarHttpResponse.Status.FAILED)
                    .build();
        }

        // Fetch repair from DB
        Optional<RepairEntity> repairOpt = repairRepository.findById(item.getId());
        if(repairOpt.isEmpty()) {
            return OneCarHttpResponse.<RepairDto>builder()
                    .message("Repair is not found!")
                    .status(OneCarHttpResponse.Status.FAILED)
                    .build();
        }

        if(updatingUser.getAuthorities().contains(UserType.CUSTOMER)) {
            if(item.getDemand() != null) {
                repairOpt.get().setDemand(item.getDemand());
            }

            if(item.getRepairTimeline() != null && repairOpt.get().getRepairCycle() == RepairCycle.REQUESTED) {
                repairOpt.get().setRepairTimeline(item.getRepairTimeline());
            }

            repairRepository.save(repairOpt.get());

            return OneCarHttpResponse.<RepairDto>builder()
                    .message("Repair request is updated successfully!")
                    .status(OneCarHttpResponse.Status.SUCCESS)
                    .object(repairOpt.get().toDto())
                    .build();
        }

        if(updatingUser.getAuthorities().contains(UserType.MANAGER)) {
            if(item.getStarted() != null) {
                repairOpt.get().setStarted(item.getStarted());
            }

            if(item.getEnded() != null) {
                repairOpt.get().setEnded(item.getEnded());
            }

            if(item.getRepairCycle() != null) {
                repairOpt.get().setRepairCycle(item.getRepairCycle());
            }

            repairRepository.save(repairOpt.get());

            // send by websocket to notify the customer
            if(repairOpt.get().getCustomer() != null) {
                try {
                    messagingTemplate.convertAndSendToUser(repairOpt.get().getCustomer().getPhone(),
                            "/update-repair",
                            objMapper.writeValueAsString(repairOpt.get().toDto()));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

            return OneCarHttpResponse.<RepairDto>builder()
                    .message("Repair request is updated successfully!")
                    .status(OneCarHttpResponse.Status.SUCCESS)
                    .object(repairOpt.get().toDto())
                    .build();
        }

        return null;
    }

    @Override
    public OneCarHttpResponse<Boolean> delete(Long id) {
        // Fetch repair from DB
        Optional<RepairEntity> repairOpt = repairRepository.findById(id);
        if(repairOpt.isEmpty()) {
            return OneCarHttpResponse.<Boolean>builder()
                    .message("Repair is not found!")
                    .status(OneCarHttpResponse.Status.FAILED)
                    .object(false)
                    .build();
        }

        repairOpt.get().forDelete();
        repairRepository.save(repairOpt.get());

        // send by websocket to notify the manager
        notifyManager(repairOpt.get().toDto());

        return OneCarHttpResponse.<Boolean>builder()
                .status(OneCarHttpResponse.Status.SUCCESS)
                .message("Repair request deleted!")
                .object(true)
                .build();
    }

    @Async
    void notifyManager(RepairDto repairDto) {
        // Find manager in DB
        List<UserEntity> managers = userRepository.findManagers(UserType.MANAGER, BaseStatus.ACTIVE, BaseStatus.CREATED);
        managers.forEach(m -> {
            try {
                messagingTemplate.convertAndSendToUser(m.getPhone(), "/delete-repair", objMapper.writeValueAsString(repairDto));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public OneCarHttpResponse<List<RepairDto>> findAll() {
        List<RepairEntity> repairs = repairRepository.findActiveRepairs(BaseStatus.ACTIVE, BaseStatus.CREATED);
        return OneCarHttpResponse.<List<RepairDto>>builder()
                .message("Here there are all repairs!")
                .status(OneCarHttpResponse.Status.SUCCESS)
                .object(repairs.stream().map(RepairEntity::toDto).toList())
                .build();
    }

    @Override
    public OneCarHttpResponse<Boolean> closeRepair(Long id) {
        Optional<RepairEntity> repairOpt = repairRepository.findById(id);
        if(repairOpt.isPresent()) {
            repairOpt.get().forClose();
            repairRepository.save(repairOpt.get());

            return OneCarHttpResponse.<Boolean>builder()
                    .message("Repair request is closed")
                    .status(OneCarHttpResponse.Status.SUCCESS)
                    .object(true)
                    .build();
        }

        return OneCarHttpResponse.<Boolean>builder()
                .message("Repair is not found!")
                .status(OneCarHttpResponse.Status.FAILED)
                .object(false)
                .build();
    }
}
