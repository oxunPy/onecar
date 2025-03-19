package com.example.onecar.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RepairDetailDto extends BaseDto {
    @NotNull(message = "Service price should not be null")
    @Min(value = 1L)
    private BigDecimal price;

    @NotBlank(message = "Service type should not be empty")
    private String serviceType;      // оказанный услуг

    @NotBlank(message = "Body part should not be empty")
    private String bodyPart;         // Запчасть

    @NotNull(message = "Repair request should not be null")
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
