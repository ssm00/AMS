package com.ams.amsbackend.repository;

import com.ams.amsbackend.domain.StudentAnswerEntity;
import com.ams.amsbackend.domain.StudentEntity;
import com.ams.amsbackend.domain.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {


    @EntityGraph(attributePaths = {"studentAnswerList"})
    Optional<StudentEntity> findById(Long userId);


    Optional<StudentEntity> findByLoginId(String loginId);
    List<StudentEntity> findAllByGrade(int grade);

}
