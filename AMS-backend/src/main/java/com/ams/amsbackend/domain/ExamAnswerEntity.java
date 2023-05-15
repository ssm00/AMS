package com.ams.amsbackend.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ExamAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    private String examGrade;

    private Integer examNumber;

    private String subject;

    private Integer numberOfQuestion;

    private String allotment;

    private String examAnswer;

    @Builder
    public ExamAnswerEntity(String examGrade, Integer examNumber, String subject, Integer numberOfQuestion, String allotment, String examAnswer) {
        this.examGrade = examGrade;
        this.examNumber = examNumber;
        this.subject = subject;
        this.numberOfQuestion = numberOfQuestion;
        this.allotment = allotment;
        this.examAnswer = examAnswer;
    }
}
