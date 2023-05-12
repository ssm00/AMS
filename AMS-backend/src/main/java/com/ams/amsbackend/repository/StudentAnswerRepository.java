package com.ams.amsbackend.repository;

import com.ams.amsbackend.domain.StudentAnswerEntity;
import com.ams.amsbackend.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswerEntity,Long> {
        int countStudentAnswerEntitiesByExamNumberAndExamSubject(int examNumber, String examSubject);
        StudentAnswerEntity findByStudentEntityAndExamNumberAndExamSubject(StudentEntity studentEntity, int examNumber, String examSubject);
}
