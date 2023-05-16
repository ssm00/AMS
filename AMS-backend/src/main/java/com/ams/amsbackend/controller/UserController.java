package com.ams.amsbackend.controller;

import com.ams.amsbackend.controller.dto.UserDto;
import com.ams.amsbackend.service.UserService;
import com.ams.amsbackend.util.BaseException;
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
    public BaseResponse<UserDto.PostGradeCardInfoRes> getGradeCardInfo(@RequestParam Long userId, @RequestBody UserDto.BasicGetExamInfo examInfo) {
        //개인 성적표(등수, 오답률, 점수)
        // input = userId(Student), examNumber(시험 회차). examSubject(시험 과목)
        // output = 전체 응시 학생 수, 등수(studentRank), 만점(100), 점수(studentScore), 회차(examNumber), 각 번호별 정보(정답(ExamAnswer.examAnswer, 학생이 적은 답(StudentAnswer.studentAnswer), 오답률)
        try {
            UserDto.PostGradeCardInfoRes gradeCardInfo = this.userService.getGradeCardeInfo(userId, examInfo);
            return new BaseResponse<>(gradeCardInfo);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("grade-graph")
    public BaseResponse<UserDto.GetGradeGraphRes> getGradeGraphInfo(@RequestParam Long userId) {
        // 개인 성적그래프
        // input = userId(Student)
        // output = 모든 회차별(회차, 해당 회차 점수, 해당 회차 등수(내 등수/전체 학생 수))
        try {
            UserDto.GetGradeGraphRes gradeGraphInfo = this.userService.getGradeGraphInfo(userId);
            return new BaseResponse<>(gradeGraphInfo);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("answers")
    public BaseResponse<String> inputStudentAnswers(@RequestParam Long userId, @RequestBody UserDto.PostInputStudentAnswersReq studentInput){
        // 학생 정답 입력
        // input = userId(Student), 회차(examNumber), 과목(examSubject), 학생이 작성한 정답(studentAnswer),
        // output = 저장 성공 여부
        try {
            String result = this.userService.inputStudentAnswers(userId, studentInput);
            return new BaseResponse<>(null);
        } catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("test")
    public BaseResponse<String> test(){
        return new BaseResponse<>("test");
    }
}
