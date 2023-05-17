package com.ams.amsbackend.controller.dto;

import com.ams.amsbackend.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostTopFiveWrongRateRes {
    private Integer examNumber;
    private Subject examSubject;
    private List<EachWrongRateInfo> wrongRateInfoList;
}
