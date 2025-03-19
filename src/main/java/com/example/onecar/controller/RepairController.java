package com.example.onecar.controller;

import com.example.onecar.dto.RepairDto;
import com.example.onecar.dto.response.OneCarHttpResponse;
import com.example.onecar.service.I.IRepairService;
import com.example.onecar.utils.ValidatorUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/repairs")
public class RepairController {
    private final IRepairService repairService;

    public RepairController(IRepairService repairService) {
        this.repairService = repairService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public OneCarHttpResponse<RepairDto> createNewRepair(@Valid @RequestBody RepairDto repairDto) {
        if(repairDto.getCar() == null || ValidatorUtils.notValidId(repairDto.getCar().getId())) {
            return OneCarHttpResponse.<RepairDto>builder()
                    .message("Car id is not valid!")
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .build();
        }

        return repairService.add(repairDto);
    }

    @PutMapping("/customer-update")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public OneCarHttpResponse<RepairDto> updateRepairByCustomer(@RequestBody RepairDto repairDto) {

        if(ValidatorUtils.notValidId(repairDto.getId())) {
            return OneCarHttpResponse.<RepairDto>builder()
                    .message("Repair id is not valid!")
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .build();
        }

        return repairService.update(repairDto);
    }

    // changing cycle, started, ended
    @PutMapping("/manager-update")
    @PreAuthorize("hasAuthority('MANAGER')")
    public OneCarHttpResponse<RepairDto> updateRepairByManager(@RequestBody RepairDto repairDto) {
        if(ValidatorUtils.notValidId(repairDto.getId())) {
            return OneCarHttpResponse.<RepairDto>builder()
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .message("Repair id is not valid!")
                    .build();
        }

        return repairService.update(repairDto);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public OneCarHttpResponse<Boolean> delete(Long repairId) {
        if(ValidatorUtils.notValidId(repairId)) {
            return OneCarHttpResponse.<Boolean>builder()
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .message("Repair id is not valid!")
                    .object(Boolean.FALSE)
                    .build();
        }

        return repairService.delete(repairId);
    }

    @PutMapping("/close")
    @PreAuthorize("hasAuthority('MANAGER')")
    public OneCarHttpResponse<Boolean> close(Long repairId) {
        if(ValidatorUtils.notValidId(repairId)) {
            return OneCarHttpResponse.<Boolean>builder()
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .message("Repair id is not valid!")
                    .object(Boolean.FALSE)
                    .build();
        }

        return repairService.closeRepair(repairId);
    }

}
