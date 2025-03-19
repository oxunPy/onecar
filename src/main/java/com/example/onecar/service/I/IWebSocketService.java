package com.example.onecar.service.I;

import com.example.onecar.entity.base.RepairCycle;

public interface IWebSocketService {

    void notifyUpdateRepairCycle(Long customerId, RepairCycle repCycle);
}
