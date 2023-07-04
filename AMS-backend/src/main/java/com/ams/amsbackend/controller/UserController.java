package com.ams.amsbackend.controller;

import com.ams.amsbackend.controller.dto.*;
import com.ams.amsbackend.domain.SchoolType;
import com.ams.amsbackend.domain.Subject;
import com.ams.amsbackend.domain.UserEntity;
import com.ams.amsbackend.security.TokenProvider;
import com.ams.amsbackend.service.UserService;
import com.ams.amsbackend.util.BaseException;
import com.ams.amsbackend.util.BaseResponse;
import com.ams.amsbackend.util.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    /**
     * 회원가입
     *  타입은 S(student),T(teacher)
     */
    @PostMapping("sign-up")
    public BaseResponse<PostSignUpRes> singUp(@RequestBody PostSignUpReq signUpDto) {
        if (signUpDto.getType() == null) {
            return new BaseResponse(BaseResponseStatus.NO_USER_TYPE);
        }
        String userName = signUpDto.getName();
        String email = signUpDto.getEmail();
        String logInId = signUpDto.getLogInId();
        String password = signUpDto.getPassword();
        SchoolType schoolType = signUpDto.getSchoolType();
        try {
            if (signUpDto.getType().equals("S")) {
                String schoolName = signUpDto.getSchoolName();
                String className = signUpDto.getClassName();
                Integer grade = signUpDto.getGrade();
                userService.signUpStudent(userName, email, logInId, password, schoolName, schoolType, grade, className);
            } else if (signUpDto.getType().equals("T")) {
                Integer manageGrade = signUpDto.getManageGrade();
                Subject manageSubject = signUpDto.getManageSubject();
                userService.signUpTeacher(userName, email, logInId, password, schoolType, manageGrade, manageSubject);
            }
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        PostSignUpRes response = new PostSignUpRes(userName, logInId, signUpDto.getType());
        return new BaseResponse(response);
    }

    @PostMapping("log-in")
    public BaseResponse<PostLogInRes> logIn(@RequestBody PostLogInReq logInDto) {
        String logInId = logInDto.getLogInId();
        String password = logInDto.getPassword();
        UserEntity userEntity = null;
        try {
            userEntity = userService.logIn(logInId, password);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        final String token = tokenProvider.create(userEntity);
        PostLogInRes postLogInRes = PostLogInRes.builder()
                .token(token)
                .userName(userEntity.getName())
                .email(userEntity.getEmail())
                .logInId(userEntity.getLoginId())
                .userType(userEntity.getDiscriminatorValue())
                .build();
        return new BaseResponse(postLogInRes);
    }


    @ResponseBody
    @PostMapping("grade-card")
    public BaseResponse<UserDto.PostGradeCardInfoRes> getGradeCardInfo(@AuthenticationPrincipal String logInId, @RequestBody UserDto.BasicGetExamInfo examInfo) {
        //개인 성적표(등수, 오답률, 점수)
        // input = userId(Student), examNumber(시험 회차). examSubject(시험 과목)
        // output = 전체 응시 학생 수, 등수(studentRank), 만점(100), 점수(studentScore), 회차(examNumber), 각 번호별 정보(정답(ExamAnswer.examAnswer, 학생이 적은 답(StudentAnswer.studentAnswer), 오답률)
        try {
            UserDto.PostGradeCardInfoRes gradeCardInfo = this.userService.getGradeCardInfo(logInId, examInfo);
            return new BaseResponse<>(gradeCardInfo);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("grade-graph")
    public BaseResponse<UserDto.GetGradeGraphRes> getGradeGraphInfo(@AuthenticationPrincipal String logInId) {
        // 개인 성적그래프
        // input = userId(Student)
        // output = 모든 회차별(회차, 해당 회차 점수, 해당 회차 등수(내 등수/전체 학생 수))
        try {
            UserDto.GetGradeGraphRes gradeGraphInfo = this.userService.getGradeGraphInfo(logInId);
            return new BaseResponse<>(gradeGraphInfo);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("answers")
    public BaseResponse<String> inputStudentAnswers(@AuthenticationPrincipal String logInId, @RequestBody UserDto.PostInputStudentAnswersReq studentInput){
        // 학생 정답 입력
        // input = userId(Student), 회차(examNumber), 과목(examSubject), 학생이 작성한 정답(studentAnswer),
        // output = 저장 성공 여부
        try {
            String result = this.userService.inputStudentAnswers(logInId, studentInput);
            return new BaseResponse<>(result);
        } catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("test")
    public BaseResponse<String> test(){
        return new BaseResponse<>("test");
    }
}
