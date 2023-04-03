package com.ams.amsbackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
public class StudentEntity extends UserEntity {

    @Enumerated(EnumType.STRING)
    private SchoolType schoolType;

    private String school;

    private Integer grade;

    private String className;

    @OneToMany(mappedBy = "studentEntity")
    private List<StudentAnswerEntity> studentAnswerList = new ArrayList<>();

    public StudentEntity(String loginId, String name, String email, String password, Role role, SchoolType schoolType, String school, Integer grade, String className) {
        super(loginId, name, email, password, role);
        this.schoolType = schoolType;
        this.school = school;
        this.grade = grade;
        this.className = className;
    }

}
