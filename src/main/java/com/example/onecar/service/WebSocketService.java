package com.example.onecar.service;

import com.example.onecar.entity.base.RepairCycle;
import com.example.onecar.service.I.IWebSocketService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService implements IWebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void notifyUpdateRepairCycle(Long customerId, RepairCycle repCycle) {
        messagingTemplate.convertAndSendToUser(customerId.toString(), "/queue/rep-cycle", repCycle);
    }
}
