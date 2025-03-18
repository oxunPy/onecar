package com.example.onecar.controller;

import com.example.onecar.service.I.IRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/repairs")
public class RepairController {
    private final IRepairService repairService;

    public RepairController(IRepairService repairService) {
        this.repairService = repairService;
    }
}
