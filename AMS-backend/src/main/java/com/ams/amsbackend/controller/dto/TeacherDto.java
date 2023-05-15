package com.ams.amsbackend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TeacherDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class BasicGetExamInfo {
        private int examNumber;
        private String examSubject;
    }
}
