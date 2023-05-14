package com.ams.amsbackend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class EachWrongRateInfo {
    private Integer problemNumber;
    private float wrongRate;
    private Integer correctAnswer;
    private float firstRate;
    private float secondRate;
    private float thirdRate;
    private float fourthRate;
    private float fifthRate;
}
