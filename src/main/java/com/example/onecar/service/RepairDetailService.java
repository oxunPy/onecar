package com.example.onecar.service;

import com.example.onecar.dto.RepairDetailDto;
import com.example.onecar.dto.response.OneCarHttpResponse;
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
    public OneCarHttpResponse<RepairDetailDto> add(RepairDetailDto item) {
        return null;
    }

    @Override
    public OneCarHttpResponse<RepairDetailDto> update(RepairDetailDto item) {
        return null;
    }

    @Override
    public OneCarHttpResponse<Boolean> delete(Long id) {
        return null;
    }

    @Override
    public OneCarHttpResponse<List<RepairDetailDto>> findAll() {
        return null;
    }
}
