package com.example.onecar.repository;

import com.example.onecar.entity.RepairDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairDetailRepository extends JpaRepository <RepairDetailEntity, Long> {
}
