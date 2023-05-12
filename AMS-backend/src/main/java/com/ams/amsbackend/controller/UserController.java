package com.ams.amsbackend.controller;

import com.ams.amsbackend.controller.dto.UserDto;
import com.ams.amsbackend.service.UserService;
import com.ams.amsbackend.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("sing-up")
    public BaseResponse<?> singUp() {
        return null;
    }

    @PostMapping("log-in")
    public BaseResponse<?> logIn() {
        return null;
    }

    @ResponseBody
    @PostMapping("grade-card")
    public BaseResponse<UserDto.PostGradeCardInfoRes> getGradeCardInfo(@RequestBody int examNumber, String examSubject) {
        //개인 성적표(등수, 오답률, 점수)
        // input = userId(Student), examNumber(시험 회차). examSubject(시험 과목)
        // output = 전체 응시 학생 수, 등수(studentRank), 만점(100), 점수(studentScore), 회차(examNumber), 각 번호별 정보(정답(ExamAnswer.examAnswer, 학생이 적은 답(StudentAnswer.studentAnswer), 오답률)
        Long userId = null;
        UserDto.PostGradeCardInfoRes gradeCardInfo = this.userService.getGradeCardeInfo(userId, examNumber, examSubject);
        return new BaseResponse<>(gradeCardInfo);
    }

    @GetMapping("grade-graph")
    public BaseResponse<UserDto.GetGradeGraphRes> getGradeGraphInfo() {
        // 개인 성적그래프
        // input = userId(Student)
        // output = 모든 회차별(회차, 해당 회차 점수, 해당 회차 등수(내 등수/전체 학생 수))

        return null;
    }

    @ResponseBody
    @PostMapping("answers")
    public BaseResponse<String> inputAnswers(@RequestBody UserDto.PostInputAnswersReq studentInput){
        // 학생 정답 입력
        // input = userId(Student), 회차(examNumber), 과목(examSubject), 학생이 작성한 정답(studentAnswer),
        // output = 저장 성공 여부
        return null;
    }
}
