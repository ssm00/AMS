package com.ams.amsbackend.service;

import com.ams.amsbackend.controller.dto.EachStudentInfo;
import com.ams.amsbackend.domain.StudentAnswerEntity;
import com.ams.amsbackend.domain.StudentEntity;
import com.ams.amsbackend.repository.StudentAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final StudentAnswerRepository studentAnswerRepository;

    public List<EachStudentInfo> findTop5Student(Integer examNumber, String examSubject) {
        List<EachStudentInfo> top5studentList = new ArrayList<>();
        List<StudentAnswerEntity> top5ScoreStudentAnswer = studentAnswerRepository.findTop5ByExamNumberAndExamSubjectOrderByStudentScoreDesc(examNumber,examSubject);
        for (StudentAnswerEntity studentAnswerEntity : top5ScoreStudentAnswer) {
            Integer studentScore = studentAnswerEntity.getStudentScore();
            Integer studentRank = studentAnswerEntity.getStudentRank();
            String studentName = studentAnswerEntity.getStudentEntity().getName();
            EachStudentInfo eachStudentInfo = EachStudentInfo.builder()
                    .studentScore(studentScore)
                    .studentRank(studentRank)
                    .name(studentName)
                    .build();
            top5studentList.add(eachStudentInfo);
        }
        return top5studentList;
    }



}


