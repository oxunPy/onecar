package com.example.onecar.dto;

import java.math.BigDecimal;

public class RepairDetailDto extends BaseDto {
    private BigDecimal price;

    private String serviceType;      // оказанный услуг

    private String bodyPart;         // Запчасть

    private RepairDto repair;

    private UserDto mechanic;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public RepairDto getRepair() {
        return repair;
    }

    public void setRepair(RepairDto repair) {
        this.repair = repair;
    }

    public UserDto getMechanic() {
        return mechanic;
    }

    public void setMechanic(UserDto mechanic) {
        this.mechanic = mechanic;
    }
}
