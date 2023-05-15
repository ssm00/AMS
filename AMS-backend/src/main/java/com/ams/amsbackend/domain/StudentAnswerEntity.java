package com.ams.amsbackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class StudentAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examStudentId;
    @ColumnDefault("0")
    private Integer studentScore;

    private String studentAnswer;
    @ColumnDefault("0")
    private Integer studentRank;

    private Integer examNumber;
    @ColumnDefault("")
    private String correctAnswer;
    @ColumnDefault("")
    private String incorrectAnswer;

    private String examSubject;

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

    public void updateCorrectAnswer(int correctAnswer){
        if(this.correctAnswer == null || this.correctAnswer.equals("")) this.correctAnswer = correctAnswer+"";
        else this.correctAnswer = this.correctAnswer + "," + correctAnswer;
    }
    public void updateIncorrectAnswer(int incorrectAnswer){
        if(this.incorrectAnswer == null || this.incorrectAnswer.equals("")) this.incorrectAnswer = incorrectAnswer+"";
        else this.incorrectAnswer = this.incorrectAnswer + "," + incorrectAnswer;
    }
    public void updateStudentScore(int score){
        if(this.studentScore == null) this.studentScore = score;
        else this.studentScore = this.studentScore + score;
    }
}



