package com.example.onecar.repository;

import com.example.onecar.entity.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<RepairEntity, Long> {
}
