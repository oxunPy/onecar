package com.example.onecar.service;

import com.example.onecar.dto.records.ReportCarFixResponse;
import com.example.onecar.dto.response.OneCarHttpResponse;
import com.example.onecar.entity.RepairDetailEntity;
import com.example.onecar.entity.RepairEntity;
import com.example.onecar.entity.base.BaseStatus;
import com.example.onecar.repository.RepairDetailRepository;
import com.example.onecar.repository.RepairRepository;
import com.example.onecar.service.I.IReportService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReportService implements IReportService {

    private final RepairRepository repairRepository;
    private final RepairDetailRepository repairDetailRepository;

    public ReportService(RepairRepository repairRepository,
                         RepairDetailRepository repairDetailRepository) {
        this.repairRepository = repairRepository;
        this.repairDetailRepository = repairDetailRepository;
    }


    @Override
    public OneCarHttpResponse<ReportCarFixResponse> reportCarFix(Long repairId) {
        Optional<RepairEntity> repairOpt = repairRepository.findById(repairId);
        if(repairOpt.isPresent()) {
            List<RepairDetailEntity> repDetails = repairDetailRepository.findAllActiveRepairDetails(repairId, BaseStatus.CREATED, BaseStatus.ACTIVE);
            BigDecimal total = repDetails.stream().map(RepairDetailEntity::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

            return OneCarHttpResponse.<ReportCarFixResponse>builder()
                    .message("Report car fix detail:")
                    .status(OneCarHttpResponse.Status.SUCCESS)
                    .object(new ReportCarFixResponse(repairOpt.get().toDto(),
                            repDetails.stream().map(RepairDetailEntity::toDto).toList(),
                            total))
                    .build();
        }

        return OneCarHttpResponse.<ReportCarFixResponse>builder()
                .message("Repair is not found!")
                .status(OneCarHttpResponse.Status.FAILED)
                .build();
    }

    @Override
    public OneCarHttpResponse<List<ReportCarFixResponse>> reportAllCarFixes() {
        List<RepairEntity> allRepairs = repairRepository.findActiveRepairs(BaseStatus.CREATED, BaseStatus.ACTIVE);
        List<RepairDetailEntity> allRepairDetails = repairDetailRepository.findAllActiveRepairDetails(BaseStatus.CREATED, BaseStatus.ACTIVE);

        for(RepairEntity rep : allRepairs) {
            for(RepairDetailEntity repDetail : allRepairDetails) {
                if(Objects.equals(repDetail.getRepairId(), rep.getId())) {
                    rep.getRepairDetails().add(repDetail);
                }
            }
        }

        List<ReportCarFixResponse> result = new ArrayList<>();
        for(RepairEntity rep : allRepairs) {
            BigDecimal total = rep.getRepairDetails().stream().map(RepairDetailEntity::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            result.add(new ReportCarFixResponse(rep.toDto(),
                    rep.getRepairDetails().stream().map(RepairDetailEntity::toDto).toList(),
                    total));
        }

        return OneCarHttpResponse.<List<ReportCarFixResponse>>builder()
                .message("Here there are all report car fix detail list")
                .status(OneCarHttpResponse.Status.SUCCESS)
                .object(result)
                .build();
    }
}
