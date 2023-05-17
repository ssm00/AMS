package com.ams.amsbackend.repository;

import com.ams.amsbackend.domain.ExamAnswerEntity;
import com.ams.amsbackend.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamAnswerRepository extends JpaRepository<ExamAnswerEntity, Long>{

    ExamAnswerEntity findAllByExamNumberAndSubject(int examNumber, Subject subject);

    ExamAnswerEntity findAllByExamGradeAndExamNumberAndSubject(int grade, int examNumber, Subject subject);

}
