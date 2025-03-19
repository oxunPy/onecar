package com.example.onecar.controller;

import com.example.onecar.dto.records.ReportCarFixResponse;
import com.example.onecar.dto.response.OneCarHttpResponse;
import com.example.onecar.service.I.IReportService;
import com.example.onecar.utils.ValidatorUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/report-car-fix")
public class ReportCarFixController {

    private final IReportService reportService;

    public ReportCarFixController(IReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/detail")
    public OneCarHttpResponse<ReportCarFixResponse> reportFixing(@RequestParam("rep_id") Long repairId) {
        if(ValidatorUtils.notValidId(repairId)) {
            return OneCarHttpResponse.<ReportCarFixResponse>builder()
                    .status(OneCarHttpResponse.Status.BAD_REQUEST)
                    .message("Provided id is valid!")
                    .build();
        }

        return reportService.reportCarFix(repairId);
    }

    @GetMapping("/detail-list")
    public OneCarHttpResponse<List<ReportCarFixResponse>> reportFixingList() {
        return reportService.reportAllCarFixes();
    }
}
