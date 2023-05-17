package com.ams.amsbackend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostLogInRes {
    private String token;
    private String userName;
    private String logInId;
    private String email;
}
