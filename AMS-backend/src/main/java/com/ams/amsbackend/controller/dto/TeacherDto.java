package com.ams.amsbackend.controller.dto;

import com.ams.amsbackend.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class TeacherDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class BasicGetExamInfo {
        private int grade;
        private int examNumber;
        private Subject examSubject;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class PostDistributionTableRes {
        private int grade;
        private int examNumber;
        private Subject examSubject;
        private int takeStudentCount;
        List<EachStudentScore> eachStudentScoreList;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class EachStudentScore {
        private String name;
        private int studentScore;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class PostDistributionCountTableRes {
        private int grade;
        private int examNumber;
        private Subject examSubject;
        private int takeStudentCount;
        List<EachStudentScoreCount> eachStudentScoreCountList;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class EachStudentScoreCount {
        private int score;
        private int count;
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetAverageGraphReq {
        private int grade;
        private Subject examSubject;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class GetAverageGraphRes {
        private int grade;
        private Subject examSubject;
        private int takeStudentCount;
        List<EachAverageScore> eachAverageScoreList;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class EachAverageScore {
        private int examNumber;
        private float averageScore;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class GetExistAnswerRes {
        private boolean existAnswer;
        private String message;
    }
    @Getter
    @AllArgsConstructor
    public static class PostInputAnswersReq {
        private int grade;
        private int examNumber;
        private Subject examSubject;
        private int numberOfQuestion;
        private String allotment;
        private String examAnswer;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class getToDoMarkCountRes {
        private int grade;
        private int examNumber;
        private Subject examSubject;
        private int numberOfStudent;
    }
}
