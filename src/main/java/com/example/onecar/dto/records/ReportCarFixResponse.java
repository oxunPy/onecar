package com.example.onecar.dto.records;

import com.example.onecar.dto.RepairDetailDto;
import com.example.onecar.dto.RepairDto;

import java.math.BigDecimal;
import java.util.List;

public record ReportCarFixResponse(
    RepairDto repair,
    List<RepairDetailDto> details,
    BigDecimal total
) { }
