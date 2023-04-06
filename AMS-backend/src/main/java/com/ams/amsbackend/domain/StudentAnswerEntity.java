package com.ams.amsbackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class StudentAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examStudentId;

    private Integer studentScore;

    private String studentAnswer;

    private Integer studentRank;

    private Integer examNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private StudentEntity studentEntity;

    @Builder
    public StudentAnswerEntity(Integer studentScore, String studentAnswer, Integer studentRank, Integer examNumber) {
        this.studentScore = studentScore;
        this.studentAnswer = studentAnswer;
        this.studentRank = studentRank;
        this.examNumber = examNumber;
    }


    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

}



