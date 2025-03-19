package com.example.onecar.service;

import com.example.onecar.config.security.SecurityUtils;
import com.example.onecar.dto.RepairDetailDto;
import com.example.onecar.dto.response.OneCarHttpResponse;
import com.example.onecar.entity.RepairDetailEntity;
import com.example.onecar.entity.RepairEntity;
import com.example.onecar.entity.UserEntity;
import com.example.onecar.entity.base.BaseStatus;
import com.example.onecar.repository.RepairDetailRepository;
import com.example.onecar.repository.RepairRepository;
import com.example.onecar.repository.UserRepository;
import com.example.onecar.service.I.IRepairDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairDetailService implements IRepairDetailService {
    private final RepairDetailRepository repairDetailRepository;
    private final RepairRepository repairRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objMapper;

    public RepairDetailService(RepairDetailRepository repairDetailRepository,
                               RepairRepository repairRepository,
                               UserRepository userRepository,
                               SimpMessagingTemplate messagingTemplate,
                               ObjectMapper objMapper) {
        this.repairDetailRepository = repairDetailRepository;
        this.repairRepository = repairRepository;
        this.userRepository = userRepository;
        this.messagingTemplate = messagingTemplate;
        this.objMapper = objMapper;
    }

    @Override
    public OneCarHttpResponse<RepairDetailDto> add(RepairDetailDto item) {
        UserEntity mechanic = SecurityUtils.getAuthenticatedUser();
        if(mechanic == null) return null;

        Optional<RepairEntity> repairEntityOpt = repairRepository.findById(item.getRepair().getId());
        if(repairEntityOpt.isEmpty()) {
            return OneCarHttpResponse.<RepairDetailDto>builder()
                    .message("Repair is not found!")
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .build();
        }

        RepairDetailEntity newEntity = new RepairDetailEntity();
        BeanUtils.copyProperties(item, newEntity);
        newEntity.setMechanic(mechanic);
        newEntity.setMechanicId(mechanic.getId());
        newEntity.setRepairId(item.getRepair().getId());
        newEntity.setRepair(repairEntityOpt.get());
        newEntity.forCreate();
        repairDetailRepository.save(newEntity);

        // notify customer
        notifyCustomerAboutRepairmentDetail(newEntity.toDto());

        return OneCarHttpResponse.<RepairDetailDto>builder()
                .message("RepairDetail is saved successfully")
                .status(OneCarHttpResponse.Status.SUCCESS)
                .object(newEntity.toDto())
                .build();
    }

    @Async
    void notifyCustomerAboutRepairmentDetail(RepairDetailDto dto) {
        Optional<UserEntity> customer = userRepository.findCustomerByRepairId(dto.getRepair().getId());
        if(customer.isPresent()) {
            try {
                messagingTemplate.convertAndSendToUser(customer.get().getPhone(), "/add-repair-detail", objMapper.writeValueAsString(dto));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public OneCarHttpResponse<RepairDetailDto> update(RepairDetailDto item) {
        Optional<RepairDetailEntity> repDetail = repairDetailRepository.findById(item.getId());
        if(repDetail.isPresent()) {
            if(item.getBodyPart() != null) {
                repDetail.get().setBodyPart(item.getBodyPart());
            }

            if(item.getServiceType() != null) {
                repDetail.get().setServiceType(item.getServiceType());
            }

            if(item.getPrice() != null) {
                repDetail.get().setPrice(item.getPrice());
            }

            repairDetailRepository.save(repDetail.get());

            // notify customer
            notifyCustomerAboutRepairmentDetail(repDetail.get().toDto());

            return OneCarHttpResponse.<RepairDetailDto>builder()
                    .message("RepairDetail is updated successfully!")
                    .status(OneCarHttpResponse.Status.SUCCESS)
                    .object(repDetail.get().toDto())
                    .build();
        }

        return OneCarHttpResponse.<RepairDetailDto>builder()
                .message("RepairDetail is not found")
                .status(OneCarHttpResponse.Status.FAILED)
                .build();
    }

    @Override
    public OneCarHttpResponse<Boolean> delete(Long id) {
        Optional<RepairDetailEntity> repDetail = repairDetailRepository.findById(id);
        if(repDetail.isPresent()) {
            repDetail.get().forDelete();
            repairDetailRepository.save(repDetail.get());

            // notify customer
            notifyCustomerAboutRepairmentDetail(repDetail.get().toDto());

            return OneCarHttpResponse.<Boolean>builder()
                    .message("RepairDetail is deleted successfully!")
                    .status(OneCarHttpResponse.Status.SUCCESS)
                    .object(true)
                    .build();
        }

        return OneCarHttpResponse.<Boolean>builder()
                .message("RepairDetail is not found")
                .status(OneCarHttpResponse.Status.FAILED)
                .object(false)
                .build();
    }

    @Override
    public OneCarHttpResponse<List<RepairDetailDto>> findAll() {
        List<RepairDetailEntity> repDetails = repairDetailRepository.findAllActives(BaseStatus.CREATED, BaseStatus.ACTIVE);
        return OneCarHttpResponse.<List<RepairDetailDto>>builder()
                .message("Here there are all repair details!")
                .status(OneCarHttpResponse.Status.SUCCESS)
                .object(repDetails.stream().map(RepairDetailEntity::toDto).toList())
                .build();
    }
}
