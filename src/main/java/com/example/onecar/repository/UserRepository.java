package com.example.onecar.repository;

import com.example.onecar.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "from UserEntity u where u.phone = :phone")
    Optional<UserEntity> findUserByPhone(String phone);
}
