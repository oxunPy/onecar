package com.example.onecar.repository;

import com.example.onecar.entity.RepairDetailEntity;
import com.example.onecar.entity.base.BaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepairDetailRepository extends JpaRepository <RepairDetailEntity, Long> {

    @Query(value = "from RepairDetailEntity rd where rd.status = :created or rd.status = :active")
    List<RepairDetailEntity> findAllActives(@Param("created") BaseStatus created,
                                            @Param("active")BaseStatus active);

    @Query(value = "from RepairDetailEntity rd where rd.repair.id = :rep_id and (rd.status = :created or rd.status = :active)")
    List<RepairDetailEntity> findAllActiveRepairDetails(@Param("rep_id") Long repId,
                                                        @Param("created") BaseStatus created,
                                                        @Param("active") BaseStatus active);

    @Query(value = "from RepairDetailEntity rd where (rd.status = :created or rd.status = :active)")
    List<RepairDetailEntity> findAllActiveRepairDetails(@Param("created") BaseStatus created,
                                                        @Param("active") BaseStatus active);
}
