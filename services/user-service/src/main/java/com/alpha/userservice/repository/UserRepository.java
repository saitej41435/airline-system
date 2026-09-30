package com.alpha.userservice.repository;

import com.alpha.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);

    UserEntity findByEmail(String email);
}
