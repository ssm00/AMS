package com.ams.amsbackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("T")
@Entity
public class TeacherEntity extends UserEntity{

    @Column(name = "manageSubject")
    @Enumerated(EnumType.STRING)
    private Subject subject;

    private Integer manageGrade;

    @Column(name = "manageSchoolType")
    @Enumerated(EnumType.STRING)
    private SchoolType schoolType;

    public TeacherEntity(String loginId, String name, String email, String password, Role role, Subject subject, Integer manageGrade, SchoolType schoolType) {
        super(loginId, name, email, password, role);
        this.subject = subject;
        this.manageGrade = manageGrade;
        this.schoolType = schoolType;
    }
}
