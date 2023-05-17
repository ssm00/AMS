package com.ams.amsbackend.service;

import com.ams.amsbackend.controller.dto.PostSignUpRes;
import com.ams.amsbackend.controller.dto.UserDto;

import com.ams.amsbackend.domain.*;
import com.ams.amsbackend.repository.*;

import com.ams.amsbackend.domain.ExamAnswerEntity;
import com.ams.amsbackend.domain.StudentAnswerEntity;
import com.ams.amsbackend.domain.StudentEntity;
import com.ams.amsbackend.domain.Subject;
import com.ams.amsbackend.repository.ExamAnswerRepository;
import com.ams.amsbackend.repository.StudentAnswerRepository;
import com.ams.amsbackend.repository.StudentRepository;

import com.ams.amsbackend.util.BaseException;
import com.ams.amsbackend.util.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    public UserDto.PostGradeCardInfoRes getGradeCardInfo(String logInId, UserDto.BasicGetExamInfo examInfo) throws BaseException{
        // output = 전체 응시 학생 수, 등수(studentRank), 만점(100), 점수(studentScore), 회차(examNumber),
        // 각 번호별 정보(정답(ExamAnswer.examAnswer, 학생이 적은 답(StudentAnswer.studentAnswer), 오답률)
        Optional<StudentEntity> studentEntity = this.studentRepository.findByLoginId(logInId);
        StudentAnswerEntity studentAnswer = this.studentAnswerRepository.findByStudentEntityAndExamNumberAndExamSubject(studentEntity.get(), examInfo.getExamNumber(), examInfo.getExamSubject());
        if(studentAnswer == null) throw new BaseException(BaseResponseStatus.POST_USERS_NOT_FOUND_ANSWER);
        // 해당 학생의 해당 회차, 과목의 entity
        StudentAnswerEntity studentAnswerEntity = this.studentAnswerRepository.findByStudentEntityAndExamNumberAndExamSubject(studentEntity.get(), examInfo.getExamNumber(), examInfo.getExamSubject());
        if(studentAnswerEntity.getStudentScore() == null) throw new BaseException(BaseResponseStatus.POST_USERS_NOT_MARK);
        try {
            // 같은 학년의 학생들
            List<StudentEntity> studentEntities = this.studentRepository.findAllByGrade(studentEntity.get().getGrade());
            // 전체 학생수
            Long totalStudentLong = this.studentAnswerRepository.countStudentAnswerEntitiesByExamNumberAndExamSubjectAndStudentEntityIn(examInfo.getExamNumber(), examInfo.getExamSubject(), studentEntities);
            int totalStudent = totalStudentLong.intValue();
            // 번호별 정답
            ExamAnswerEntity examAnswerEntity = this.examAnswerRepository.findAllByExamGradeAndExamNumberAndSubject(studentEntity.get().getGrade(), examInfo.getExamNumber(), examInfo.getExamSubject());
            String[] examAnswers = examAnswerEntity.getExamAnswer().split(",");
            // 번호별 학생 답
            String[] studentAnswers = studentAnswerEntity.getStudentAnswer().split(",");
            // 번호별 오답률
            float[] incorrectRate = calculateIncorrectRate(studentEntities, examInfo, examAnswers.length, totalStudent);

            List<UserDto.EachProblemInfo> eachProblemInfoList = new ArrayList<>();
            for (int i = 0; i < examAnswers.length; i++) {
                eachProblemInfoList.add(new UserDto.EachProblemInfo(
                        i + 1,
                        examAnswers[i],
                        studentAnswers[i],
                        incorrectRate[i]
                ));
            }
            return new UserDto.PostGradeCardInfoRes(
                    totalStudent,
                    studentAnswerEntity.getStudentRank(),
                    100,
                    studentAnswerEntity.getStudentScore(),
                    examInfo.getExamNumber(),
                    examInfo.getExamSubject(),
                    eachProblemInfoList
            );
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
    private float[] calculateIncorrectRate(List<StudentEntity> studentEntities, UserDto.BasicGetExamInfo examInfo, int numberOfQuestion, int totalStudent) throws Exception{
        float[] incorrectRate = new float[numberOfQuestion];
        HashMap<String, Integer> incorrectQuestionCounter = new HashMap<>();
        for(int i=1;i<=numberOfQuestion; i++){
            incorrectQuestionCounter.put(Integer.toString(i), 0);
        }
        List<StudentAnswerEntity> studentAnswerEntityList = this.studentAnswerRepository.findAllByExamNumberAndExamSubjectAndStudentEntityIn(examInfo.getExamNumber(), examInfo.getExamSubject(), studentEntities);
        for(StudentAnswerEntity studentEntity : studentAnswerEntityList){
            if(studentEntity.getStudentScore() == null) {
                totalStudent--;
                continue;
            }
            String[] incorrectAnswers = studentEntity.getIncorrectAnswer().split(",");
            for(int i=0;i<incorrectAnswers.length;i++){
                incorrectQuestionCounter.put(incorrectAnswers[i], incorrectQuestionCounter.get(incorrectAnswers[i])+1);
            }
        }
        for(int i=1; i<=numberOfQuestion; i++){
            if(incorrectQuestionCounter.get(i+"") == 0) continue;
            else incorrectRate[i-1] = incorrectQuestionCounter.get(Integer.toString(i)) / (float)totalStudent;
        }
        return incorrectRate;
    }

    public UserDto.GetGradeGraphRes getGradeGraphInfo(String logInId) throws BaseException{
        // output = 모든 회차별(회차, 과목, 해당 회차 점수, 해당 회차 등수(내 등수/전체 학생 수))
        try {
            List<UserDto.EachExamNumberInfo> eachExamNumberInfos = new ArrayList<>();
            Optional<StudentEntity> studentEntity = this.studentRepository.findByLoginId(logInId);
            List<StudentAnswerEntity> studentAnswerEntityList = this.studentAnswerRepository.findStudentAnswerEntitiesByStudentEntity(studentEntity.get());
            List<StudentEntity> studentEntities = this.studentRepository.findAllByGrade(studentEntity.get().getGrade());
            for (StudentAnswerEntity studentAnswerEntity : studentAnswerEntityList) {
                if(studentAnswerEntity.getStudentScore() == null) continue;
                Long totalStudentsLong = this.studentAnswerRepository.countStudentAnswerEntitiesByExamNumberAndExamSubjectAndStudentEntityIn(studentAnswerEntity.getExamNumber(), studentAnswerEntity.getExamSubject(), studentEntities);
                int totalStudents = totalStudentsLong.intValue();
                eachExamNumberInfos.add(new UserDto.EachExamNumberInfo(
                        studentAnswerEntity.getExamNumber(),
                        studentAnswerEntity.getExamSubject(),
                        studentAnswerEntity.getStudentScore(),
                        studentAnswerEntity.getStudentRank(),
                        totalStudents
                ));
            }
            return new UserDto.GetGradeGraphRes(eachExamNumberInfos);
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public String inputStudentAnswers(String logInId, UserDto.PostInputStudentAnswersReq studentInput) throws BaseException{
        // input = userId(Student), 회차(examNumber), 과목(examSubject), 학생이 작성한 정답(studentAnswer),
        // output = 저장 성공 여부
        Optional<StudentEntity> studentEntity = this.studentRepository.findByLoginId(logInId);
        StudentAnswerEntity studentAnswer = this.studentAnswerRepository.findByStudentEntityAndExamNumberAndExamSubject(studentEntity.get(), studentInput.getExamNumber(), studentInput.getExamSubject());
        if(studentAnswer != null) throw new BaseException(BaseResponseStatus.POST_USERS_INPUT_ANSWER);
        try {
            StudentAnswerEntity studentAnswerEntity = StudentAnswerEntity.builder()
                    .examNumber(studentInput.getExamNumber())
                    .examSubject(studentInput.getExamSubject())
                    .studentAnswer(studentInput.getStudentAnswer())
                    .studentEntity(studentEntity.get())
                    .build();
            this.studentAnswerRepository.save(studentAnswerEntity);
            return "정답이 입력되었습니다.";
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }

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
