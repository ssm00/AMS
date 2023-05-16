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

    private String correctAnswer;

    private String incorrectAnswer;

    @Enumerated(EnumType.STRING)
    private Subject examSubject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private StudentEntity studentEntity;

    @Builder
    public StudentAnswerEntity(String studentAnswer, Integer examNumber) {
        this.studentAnswer = studentAnswer;
        this.examNumber = examNumber;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

}



