package com.ams.amsbackend.service;

import com.ams.amsbackend.controller.dto.TeacherDto;
import com.ams.amsbackend.domain.ExamAnswerEntity;
import com.ams.amsbackend.domain.StudentAnswerEntity;
import com.ams.amsbackend.domain.StudentEntity;
import com.ams.amsbackend.repository.ExamAnswerRepository;
import com.ams.amsbackend.repository.StudentAnswerRepository;
import com.ams.amsbackend.util.BaseException;
import com.ams.amsbackend.util.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final StudentAnswerRepository studentAnswerRepository;
    private final ExamAnswerRepository examAnswerRepository;
    public String markExams(TeacherDto.BasicGetExamInfo examInfo) throws BaseException {
        try{
            ExamAnswerEntity examAnswerEntity = this.examAnswerRepository.findAllByExamNumberAndSubject(examInfo.getExamNumber(), examInfo.getExamSubject());
            String[] examAnswers = examAnswerEntity.getExamAnswer().split(",");
            String[] examAllotment = examAnswerEntity.getAllotment().split(",");
            List<StudentAnswerEntity> studentAnswerEntityList = this.studentAnswerRepository.findAllByExamNumberAndExamSubject(examInfo.getExamNumber(), examInfo.getExamSubject());
            for(StudentAnswerEntity studentEntity : studentAnswerEntityList){
                String[] studentAnswers = studentEntity.getStudentAnswer().split(",");
                for(int i=0; i<examAnswerEntity.getNumberOfQuestion(); i++){
                    if(studentAnswers[i].equals(examAnswers[i])){
                        studentEntity.updateCorrectAnswer(i+1);
                        studentEntity.updateStudentScore(Integer.parseInt(examAllotment[i]));
                        studentAnswerRepository.save(studentEntity);
                    }else {
                        studentEntity.updateIncorrectAnswer(i+1);
                        studentAnswerRepository.save(studentEntity);
                    }
                }
            }
            return "채점이 완료되었습니다.";
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }

    }
}
