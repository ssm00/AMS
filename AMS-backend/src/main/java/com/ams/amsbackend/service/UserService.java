package com.ams.amsbackend.service;

import com.ams.amsbackend.controller.dto.PostSignUpRes;
import com.ams.amsbackend.controller.dto.UserDto;
import com.ams.amsbackend.domain.*;
import com.ams.amsbackend.repository.*;
import com.ams.amsbackend.util.BaseException;
import com.ams.amsbackend.util.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final StudentAnswerRepository studentAnswerRepository;
    private final ExamAnswerRepository examAnswerRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    public UserDto.PostGradeCardInfoRes getGradeCardeInfo(Long userId, int examNumber, Subject examSubject) {
//        // output = 전체 응시 학생 수, 등수(studentRank), 만점(100), 점수(studentScore), 회차(examNumber),
//        // 각 번호별 정보(정답(ExamAnswer.examAnswer, 학생이 적은 답(StudentAnswer.studentAnswer), 오답률)
//        int totalStudent = this.studentAnswerRepository.countStudentAnswerEntitiesByExamNumberAndExamSubject(examNumber, examSubject);
//        ExamAnswerEntity examAnswerEntity = this.examAnswerRepository.findAllByExamNumberAndSubject(examNumber, examSubject);
//        String[] examAnswers = examAnswerEntity.getExamAnswer().split(",");
//        Optional<StudentEntity> studentEntity = this.studentRepository.findById(userId);
//        StudentAnswerEntity studentAnswerEntity = this.studentAnswerRepository.findByStudentEntityAndExamNumberAndExamSubject(studentEntity.get(), examNumber, examSubject);
//        String[] studentAnswers = studentAnswerEntity.getStudentAnswer().split(",");
//        float[] incorrectRate = new float[studentAnswers.length];
//        List<UserDto.EachProblemInfo> eachProblemInfoList = new ArrayList<>();
//        for(int i=0; i<examAnswers.length; i++){
//            eachProblemInfoList.add(new UserDto.EachProblemInfo(
//                    i+1,
//                    examAnswers[i],
//                    studentAnswers[i],
//                    incorrectRate[i]
//            ));
//        }
//        return new UserDto.PostGradeCardInfoRes(
//                totalStudent,
//                studentAnswerEntity.getStudentRank(),
//                100,
//                studentAnswerEntity.getStudentScore(),
//                examNumber,
//                examSubject,
//                eachProblemInfoList
//        );
//    }


    public StudentEntity signUpStudent(String userName, String email, String logInId, String password, String schoolName, SchoolType schoolType, Integer grade, String className) throws BaseException {
        if (userName == null || email == null || logInId == null || password == null || schoolType == null || schoolName == null || grade == null || className == null) {
            throw new BaseException(BaseResponseStatus.CREATE_STUDENT_DATA_NULL);
        }
        if (userRepository.existsByLoginId(logInId)) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_LOGINID);
        }
        StudentEntity studentEntity = new StudentEntity(logInId, userName, email, passwordEncoder.encode(password), Role.ROLE_USER, schoolType, schoolName, grade, className);
        return studentRepository.save(studentEntity);
    }

    /**
     * 선생님 회원가입
     * 가입시 userRepository에서 id확인
     * save는 teacherRepository
     */
    public TeacherEntity signUpTeacher(String userName, String email, String logInId, String password, SchoolType schoolType, Integer manageGrade, Subject subject) throws BaseException {
        if (userName == null || email == null || logInId == null || password == null || schoolType == null || manageGrade == null || subject == null) {
            throw new BaseException(BaseResponseStatus.CREATE_TEACHER_DATA_NULL);
        }
        if (userRepository.existsByLoginId(logInId)) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_LOGINID);
        }
        TeacherEntity teacherEntity = new TeacherEntity(logInId, userName, email, passwordEncoder.encode(password), Role.ROLE_USER, subject, manageGrade, schoolType);
        return teacherRepository.save(teacherEntity);
    }

    public UserEntity logIn(String logInId, String password) throws BaseException {
        if (logInId == null) {
            throw new BaseException(BaseResponseStatus.POST_USERS_EMPTY_LOGINID);
        }
        if (password == null) {
            throw new BaseException(BaseResponseStatus.POST_USERS_EMPTY_PASSWORD);
        }
        UserEntity userEntity = userRepository.findByLoginId(logInId);
        if (userEntity == null || !passwordEncoder.matches(password, userEntity.getPassword())){
            throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
        }
        return userEntity;
    }

}
