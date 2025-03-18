package com.example.onecar.service;

import com.example.onecar.dto.RepairDto;
import com.example.onecar.repository.RepairRepository;
import com.example.onecar.service.I.IRepairService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService implements IRepairService {
    private final RepairRepository repairRepository;

    public RepairService(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    @Override
    public void add(RepairDto item) {

    }

    @Override
    public void update(RepairDto item) {

    }

    @Override
    public void delete(RepairDto item) {

    }

    @Override
    public List<RepairDto> findAll() {
        return List.of();
    }
}
