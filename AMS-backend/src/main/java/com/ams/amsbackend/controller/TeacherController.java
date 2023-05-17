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
    @PostMapping("distribution-table")
    public BaseResponse<TeacherDto.PostDistributionTableRes> getDistributionTable(@RequestParam Long userId, @RequestBody TeacherDto.BasicGetExamInfo examInfo){
        // 해당 시험 성적 분포표
        // input = userId(Teacher), 학년(grade), 회차(examNumber), 과목(examSubject)
        // output = 해당 학년(grade), 회차(examNumber), 과목(examSubject), 학생별 정보(학생 이름(name), 시험 점수(studentScore))
        try {
            TeacherDto.PostDistributionTableRes distributionTableRes = this.teacherService.getDistributionTable(examInfo);
            return new BaseResponse<>(distributionTableRes);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("average-graph")
    public BaseResponse<TeacherDto.GetAverageGraphRes> getAverageGraph(@RequestParam Long userId, @RequestBody TeacherDto.GetAverageGraphReq requestInfo){
        // 모든 시험 성적 평균 그래프
        // input = userId(Teacher), 학년(grade), 과목(examSubject)
        // output = 학년(grade), 과목(examSubject), 회차별 정보(회차(examNumber), 해당 회차 시험 평균 점수)
        try {
            TeacherDto.GetAverageGraphRes averageGraphInfo = this.teacherService.getAverageGraph(requestInfo);
            return new BaseResponse<>(averageGraphInfo);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("answers")
    public BaseResponse<String> inputAnswer(@RequestParam Long userId, @RequestBody TeacherDto.PostInputAnswersReq inputAnswerInfo){
        // 정답 입력
        // input = userId(Teacher), 학년(grade), 회차(examNumber), 과목(examSubject), 정답(answer),
        // output = 저장 성공 여부
        try {
            String result = this.teacherService.inputAnswer(inputAnswerInfo);
            return new BaseResponse<>(result);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("mark-exams")
    public BaseResponse<String> markExams(@RequestParam Long userId, @RequestBody TeacherDto.BasicGetExamInfo examInfo){
        // 채점하기
        // input = userId(Teacher), 학년(grade), 회차(examNumber), 과목(examSubject)
        // output = "채점이 완료되었습니다."
        try {
            String result = this.teacherService.markExams(examInfo);
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("todo-mark-count")
    public BaseResponse<TeacherDto.getToDoMarkCountRes> getToDoMarkCount(@RequestParam Long userId, @RequestBody TeacherDto.BasicGetExamInfo examInfo){
        // 채점해야 할 학생 수
        // input = userId(Teacher), 학년(grade), 회차(examNumber), 과목(examSubject)
        // output = 학년(grade), 회차(examNumber), 과목(examSubject), 채점 해야할 학생 수(점수가 입력 안 되어있는 학생들 수)
        try {
            TeacherDto.getToDoMarkCountRes toDoMarkCountInfo = this.teacherService.getToDoMarkCount(examInfo);
            return new BaseResponse<>(toDoMarkCountInfo);
        } catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
}
