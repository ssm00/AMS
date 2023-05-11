package com.ams.amsbackend.controller.dto;

import lombok.Builder;
import lombok.Getter;


/**
 * 시험 성적 top5 학생정보 dto
 */
@Getter
@Builder
public class EachStudentInfo {
    private String name;
    private Integer studentScore;
    private Integer studentRank;
}
