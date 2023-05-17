package com.ams.amsbackend.repository;

import com.ams.amsbackend.domain.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

}
