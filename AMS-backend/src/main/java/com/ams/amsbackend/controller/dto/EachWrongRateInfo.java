package com.ams.amsbackend.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EachWrongRateInfo {
    private Integer problemNumber;
    private float wrongRate;
    private Integer correctAnswer;
}
