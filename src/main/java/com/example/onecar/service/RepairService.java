package com.example.onecar.service;

import com.example.onecar.dto.RepairDto;
import com.example.onecar.dto.response.OneCarHttpResponse;
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
    public OneCarHttpResponse<RepairDto> add(RepairDto item) {
        return null;
    }

    @Override
    public OneCarHttpResponse<RepairDto> update(RepairDto item) {
        return null;
    }

    @Override
    public OneCarHttpResponse<Boolean> delete(Long id) {
        return null;
    }

    @Override
    public OneCarHttpResponse<List<RepairDto>> findAll() {
        return null;
    }

    @Override
    public OneCarHttpResponse<Boolean> closeRepair(Long id) {
        return null;
    }
}
