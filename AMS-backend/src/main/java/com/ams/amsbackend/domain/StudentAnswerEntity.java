package com.ams.amsbackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
public class StudentAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examStudentId;

    private Integer studentScore;
    @Column(nullable = false)
    private String studentAnswer;

    private Integer studentRank;
    @Column(nullable = false)
    private Integer examNumber;
    @ColumnDefault("null")
    private String correctAnswer;
    @ColumnDefault("null")
    private String incorrectAnswer;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Subject examSubject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private StudentEntity studentEntity;

    @Builder
    public StudentAnswerEntity(String studentAnswer, Integer examNumber, Subject examSubject, StudentEntity studentEntity) {
        this.studentAnswer = studentAnswer;
        this.examNumber = examNumber;
        this.examSubject = examSubject;
        this.studentEntity = studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public void updateCorrectAnswer(int correctAnswer){
        if(this.correctAnswer == null || this.correctAnswer.equals("null")) this.correctAnswer = correctAnswer+"";
        else this.correctAnswer = this.correctAnswer + "," + correctAnswer;
    }
    public void updateIncorrectAnswer(int incorrectAnswer){
        if(this.incorrectAnswer == null || this.incorrectAnswer.equals("null")) this.incorrectAnswer = incorrectAnswer+"";
        else this.incorrectAnswer = this.incorrectAnswer + "," + incorrectAnswer;
    }
    public void updateStudentScore(int score){
        if(this.studentScore == null) this.studentScore = score;
        else this.studentScore = this.studentScore + score;
    }
    public void updateStudentRank(int rank){
        this.studentRank = rank;
    }
}



