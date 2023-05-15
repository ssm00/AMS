package com.ams.amsbackend.controller;

import com.ams.amsbackend.controller.dto.TeacherDto;
import com.ams.amsbackend.service.TeacherService;
import com.ams.amsbackend.util.BaseException;
import com.ams.amsbackend.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers/")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @ResponseBody
    @PostMapping("mark-exams")
    public BaseResponse<String> markExams(@RequestBody TeacherDto.BasicGetExamInfo examInfo){
        try {
            String result = this.teacherService.markExams(examInfo);
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
