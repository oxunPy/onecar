package com.example.onecar.repository;

import com.example.onecar.entity.RepairEntity;
import com.example.onecar.entity.base.BaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepairRepository extends JpaRepository<RepairEntity, Long> {
    @Query("from RepairEntity r where r.status = :active or r.status = :created")
    List<RepairEntity> findActiveRepairs(@Param("active") BaseStatus active,
                                         @Param("created") BaseStatus created);
}
