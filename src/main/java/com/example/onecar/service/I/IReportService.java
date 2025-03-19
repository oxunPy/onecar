package com.example.onecar.service.I;

import com.example.onecar.dto.records.ReportCarFixResponse;
import com.example.onecar.dto.response.OneCarHttpResponse;

import java.util.List;

public interface IReportService {
    OneCarHttpResponse<ReportCarFixResponse> reportCarFix(Long repairId);

    OneCarHttpResponse<List<ReportCarFixResponse>> reportAllCarFixes();
}
