package com.example.onecar.service.I;

import com.example.onecar.dto.RepairDto;
import com.example.onecar.dto.response.OneCarHttpResponse;
import com.example.onecar.service.I.base.ICrudService;

public interface IRepairService extends ICrudService<RepairDto> {
    OneCarHttpResponse<Boolean> closeRepair(Long id);


}
