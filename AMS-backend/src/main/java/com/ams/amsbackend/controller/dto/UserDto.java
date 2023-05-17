package com.ams.amsbackend.controller.dto;

import com.ams.amsbackend.domain.Subject;
import lombok.*;

import java.util.List;

@NoArgsConstructor
public class UserDto {
    @Getter
    @AllArgsConstructor
    public static class PostInputStudentAnswersReq {
        private int examNumber;
        private Subject examSubject;
        private String studentAnswer;
    }
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
    public static class PostGradeCardInfoRes {
        private int totalStudents;
        private int studentRank;
        private int perfectScore;
        private int studentScore;
        private int examNumber;
        private Subject examSubject;
        private List<EachProblemInfo> eachProblemInfoList;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class EachProblemInfo {
        private int problemNumber;
        private String examAnswer;
        private String studentAnswer;
        private float wrongRate;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class GetGradeGraphRes {
        private List<EachExamNumberInfo> eachExamNumberInfos;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class EachExamNumberInfo {
        private int examNumber;
        private Subject subject;
        private int studentScore;
        private int studentRank;
        private int totalStudents;
    }
}
