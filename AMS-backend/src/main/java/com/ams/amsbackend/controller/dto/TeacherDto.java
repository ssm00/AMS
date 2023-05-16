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
        private int examNumber;
        private Subject examSubject;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class PostDistributionTableRes {
        private int examNumber;
        private Subject examSubject;
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
    @NoArgsConstructor
    public static class GetAverageGraphReq {
        private Subject examSubject;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class GetAverageGraphRes {
        private Subject examSubject;
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
    public static class PostInputAnswersReq {
        private int examNumber;
        private Subject examSubject;
        private String answer;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class getToDoMarkCountRes {
        private int examNumber;
        private Subject examSubject;
        private int numberOfStudent;
    }
}
