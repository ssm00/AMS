package com.ams.amsbackend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EachScoreInfo {
    private Integer examNumber;
    private Integer score;
    private Integer rank;
}
