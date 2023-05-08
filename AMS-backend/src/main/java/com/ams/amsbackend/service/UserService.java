package com.ams.amsbackend.service;

import com.ams.amsbackend.controller.dto.UserDto;
import com.ams.amsbackend.repository.ExamAnswerRepository;
import com.ams.amsbackend.repository.StudentAnswerRepository;

public class UserService {
    private StudentAnswerRepository studentAnswerRepository;
    private ExamAnswerRepository examAnswerRepository;

    public UserService(StudentAnswerRepository studentAnswerRepository, ExamAnswerRepository examAnswerRepository){
        this.studentAnswerRepository = studentAnswerRepository;
        this.examAnswerRepository = examAnswerRepository;
    }
    public static UserDto.PostGradeCardInfoRes getGradeCardeInfo(int examNumber) {
        
    }
}
