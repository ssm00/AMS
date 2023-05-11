package com.ams.amsbackend.service;

import com.ams.amsbackend.controller.dto.UserDto;
import com.ams.amsbackend.domain.ExamAnswerEntity;
import com.ams.amsbackend.domain.StudentAnswerEntity;
import com.ams.amsbackend.domain.StudentEntity;
import com.ams.amsbackend.repository.ExamAnswerRepository;
import com.ams.amsbackend.repository.StudentAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private StudentAnswerRepository studentAnswerRepository;
    private ExamAnswerRepository examAnswerRepository;

    public UserService(StudentAnswerRepository studentAnswerRepository, ExamAnswerRepository examAnswerRepository){
        this.studentAnswerRepository = studentAnswerRepository;
        this.examAnswerRepository = examAnswerRepository;
    }
    public UserDto.PostGradeCardInfoRes getGradeCardeInfo(Long userId, int examNumber, String examSubject) {
        // output = 전체 응시 학생 수, 등수(studentRank), 만점(100), 점수(studentScore), 회차(examNumber),
        // 각 번호별 정보(정답(ExamAnswer.examAnswer, 학생이 적은 답(StudentAnswer.studentAnswer), 오답률)
        int totalStudent = this.studentAnswerRepository.countStudentAnswerEntitiesByExamNumberAndExamSubject(examNumber, examSubject);
        ExamAnswerEntity examAnswerEntity = this.examAnswerRepository.findAllByExamNumberAndSubject(examNumber, examSubject);
        String[] examAnswers = examAnswerEntity.getExamAnswer().split(",");
        StudentAnswerEntity studentAnswerEntity = this.studentAnswerRepository.findAllByExamNumberAndExamSubjectAndUserId(userId, examNumber, examSubject);
        String[] studentAnswers = studentAnswerEntity.getStudentAnswer().split(",");
        float[] incorrectRate = new float[studentAnswers.length];
        List<UserDto.EachProblemInfo> eachProblemInfoList = new ArrayList<>();
        for(int i=0; i<examAnswers.length; i++){
            eachProblemInfoList.add(new UserDto.EachProblemInfo(
                    i+1,
                    examAnswers[i],
                    studentAnswers[i],
                    incorrectRate[i]
            ));
        }
        return new UserDto.PostGradeCardInfoRes(
                totalStudent,
                studentAnswerEntity.getStudentRank(),
                100,
                studentAnswerEntity.getStudentScore(),
                examNumber,
                examSubject,
                eachProblemInfoList
        );
    }
}
