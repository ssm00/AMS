package com.ams.amsbackend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostSignUpRes {
    private String userName;
    private String logInId;
    private String type;
}
