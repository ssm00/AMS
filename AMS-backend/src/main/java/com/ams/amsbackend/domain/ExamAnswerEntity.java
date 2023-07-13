package com.ams.amsbackend.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
public class ExamAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    private Integer examGrade;

    private Integer examNumber;
    @Enumerated(EnumType.STRING)
    private Subject subject;

    private Integer numberOfQuestion;

    private String allotment;

    private String examAnswer;

    @Builder
    public ExamAnswerEntity(Integer examGrade, Integer examNumber, Subject subject, Integer numberOfQuestion, String allotment, String examAnswer) {
        this.examGrade = examGrade;
        this.examNumber = examNumber;
        this.subject = subject;
        this.numberOfQuestion = numberOfQuestion;
        this.allotment = allotment;
        this.examAnswer = examAnswer;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion){
        this.numberOfQuestion = numberOfQuestion;
    }
    public void setAllotment(String allotment){
        this.allotment = allotment;
    }
    public void setExamAnswer(String examAnswer){
        this.examAnswer = examAnswer;
    }
}
