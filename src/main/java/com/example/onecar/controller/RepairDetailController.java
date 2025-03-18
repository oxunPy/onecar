package com.example.onecar.controller;

import com.example.onecar.service.I.IRepairDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/repair-details")
public class RepairDetailController {
    private final IRepairDetailService repairDetailService;

    public RepairDetailController(IRepairDetailService repairDetailService) {
        this.repairDetailService = repairDetailService;
    }
}
