package com.ams.amsbackend.controller.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
public class UserDto {
    @Getter
    @AllArgsConstructor
    public class PostInputAnswersReq {
        private int examNumber;
        private String examSubject;
        private String studentAnswer;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public class PostGradeCardInfoRes {
        private int totalStudents;
        private int studentRank;
        private int perfectScore;
        private int studentScore;
        private int examNumber;
        private List<EachProblemInfo> eachProblemInfoList;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public class EachProblemInfo {
        private int problemNumber;
        private String examAnswer;
        private String studentAnswer;
        private float wrongRate;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public class GetGradeGraphRes {
        private List<EachExamNumberInfo> eachExamNumberInfos;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public class EachExamNumberInfo {
        private int examNumber;
        private int studentScore;
        private int studentRank;
        private int totalStudents;
    }
}
