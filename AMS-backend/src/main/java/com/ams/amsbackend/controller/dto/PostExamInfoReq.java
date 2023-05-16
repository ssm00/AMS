package com.ams.amsbackend.controller.dto;


import com.ams.amsbackend.domain.Subject;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostExamInfoReq {
    private Integer examNumber;
    private Subject examSubject;
}
