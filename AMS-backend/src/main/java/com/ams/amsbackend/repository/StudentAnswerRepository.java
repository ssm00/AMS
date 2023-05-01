package com.ams.amsbackend.repository;

import com.ams.amsbackend.domain.StudentAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswerEntity,Long> {

}
