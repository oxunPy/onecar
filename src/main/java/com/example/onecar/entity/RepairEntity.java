package com.example.onecar.entity;

import com.example.onecar.entity.base.BaseEntity;
import com.example.onecar.entity.base.RepairCycle;
import com.example.onecar.entity.base.RepairTimeline;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "repairs")
public class RepairEntity extends BaseEntity {

    private Date started;

    private Date ended;

    private RepairCycle repairCycle;

    private RepairTimeline repairTimeline;

    @Column(name = "customer_id")
    private Long customerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private UserEntity customer;
}
