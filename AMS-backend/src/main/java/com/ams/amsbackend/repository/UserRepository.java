package com.ams.amsbackend.repository;

import com.ams.amsbackend.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByLoginId(String logInId);

    UserEntity findByLoginIdAndPassword(String logInId, String password);
}
