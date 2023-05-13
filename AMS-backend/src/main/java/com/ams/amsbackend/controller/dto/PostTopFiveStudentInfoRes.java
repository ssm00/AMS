package com.ams.amsbackend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostTopFiveStudentInfoRes {
    private Integer examNumber;
    private String examSubject;
    //5명의 이름,점수,등수 리스트로반환
    private List<EachStudentInfo> top5StudentList;
}
