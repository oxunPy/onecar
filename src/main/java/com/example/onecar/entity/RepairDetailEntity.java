package com.example.onecar.entity;

import com.example.onecar.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "repair_details")
public class RepairDetailEntity extends BaseEntity {

    private BigDecimal price;

    private String serviceType;      // оказанный услуг

    private String bodyPart;         // Запчасть

    @Column(name = "repair_id", nullable = false)
    private Long repairId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repair_id", insertable = false, updatable = false)
    private RepairEntity repair;

    @Column(name = "mechanic_id")
    private Long mechanicId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_id", insertable = false, updatable = false)
    private UserEntity mechanic;
}
