package com.example.onecar.repository;

import com.example.onecar.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

    @Query(value = "select exists(\n" +
            "    select 1\n" +
            "    from cars\n" +
            "    where vin_number = :vin and mark = :mark and model = :model\n" +
            ")", nativeQuery = true)
    Boolean existsByVehicleIdentityNum(@Param("vin") String vin,
                                                 @Param("mark") String mark,
                                                 @Param("model") String model);

    @Query(value = "select *\n" +
            "from cars\n" +
            "where status = :active or status = :created", nativeQuery = true)
    List<CarEntity> findAllActive(@Param("active") int active,
                                  @Param("created") int created);
}
