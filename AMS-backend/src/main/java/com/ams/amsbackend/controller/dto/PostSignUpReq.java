package com.ams.amsbackend.controller.dto;

import com.ams.amsbackend.domain.SchoolType;
import com.ams.amsbackend.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostSignUpReq {
    private String logInId;
    private String email;
    private String name;
    private String password;
    private SchoolType schoolType;

    //flag
    private String type;

    //student
    private String className;
    private String schoolName;
    private Integer grade;

    //teacher
    private Integer manageGrade;
    private Subject manageSubject;
}
