package com.example.onecar.service;

import com.example.onecar.dto.RepairDetailDto;
import com.example.onecar.repository.RepairDetailRepository;
import com.example.onecar.service.I.IRepairDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairDetailService implements IRepairDetailService {
    private final RepairDetailRepository repairDetailRepository;

    public RepairDetailService(RepairDetailRepository repairDetailRepository) {
        this.repairDetailRepository = repairDetailRepository;
    }

    @Override
    public void add(RepairDetailDto item) {

    }

    @Override
    public void update(RepairDetailDto item) {

    }

    @Override
    public void delete(RepairDetailDto item) {

    }

    @Override
    public List<RepairDetailDto> findAll() {
        return List.of();
    }
}
