package com.example.onecar.controller;

import com.example.onecar.dto.RepairDetailDto;
import com.example.onecar.dto.response.OneCarHttpResponse;
import com.example.onecar.service.I.IRepairDetailService;
import com.example.onecar.utils.ValidatorUtils;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/repair-details")
public class RepairDetailController {
    private final IRepairDetailService repairDetailService;

    public RepairDetailController(IRepairDetailService repairDetailService) {
        this.repairDetailService = repairDetailService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('MECHANIC')")
    public OneCarHttpResponse<RepairDetailDto> createRepDetail(@Valid @RequestBody RepairDetailDto repDetailDto) {
        return repairDetailService.add(repDetailDto);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('MECHANIC')")
    public OneCarHttpResponse<RepairDetailDto> updateRepDetail(@RequestBody RepairDetailDto repDetailDto) {
        if(ValidatorUtils.notValidId(repDetailDto.getId())) {
            return OneCarHttpResponse.<RepairDetailDto>builder()
                    .message("Id is not provided!")
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .build();
        }

        return repairDetailService.update(repDetailDto);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('MECHANIC')")
    public OneCarHttpResponse<Boolean> deleteRepDetail(@RequestParam("rep_detail_id") Long repDetailId) {
        if(ValidatorUtils.notValidId(repDetailId)) {
            return OneCarHttpResponse.<Boolean>builder()
                    .message("Id is not provided!")
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .object(Boolean.FALSE)
                    .build();
        }
        return repairDetailService.delete(repDetailId);
    }
}
