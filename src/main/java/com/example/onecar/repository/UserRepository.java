package com.example.onecar.repository;

import com.example.onecar.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "from UserEntity u where u.phone = :p")
    Optional<UserEntity> findUserByPhone(@Param("p") String phone);

    @Query(value = "from UserEntity u where u.phone = :p or u.email = :e")
    Optional<UserEntity> findUserByPhoneOrEmail(@Param("p") String phone,
                                                @Param("e") String email);

    @Query(value = "from UserEntity u where u.phone = :u or u.email = :u")
    Optional<UserEntity> findUserByPhoneOrEmail(@Param("u") String username);
}
