package com.ams.amsbackend.service;

import com.ams.amsbackend.controller.dto.UserDto;
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

    public UserDto.PostGradeCardInfoRes getGradeCardInfo(Long userId, UserDto.BasicGetExamInfo examInfo) throws BaseException{
        // output = 전체 응시 학생 수, 등수(studentRank), 만점(100), 점수(studentScore), 회차(examNumber),
        // 각 번호별 정보(정답(ExamAnswer.examAnswer, 학생이 적은 답(StudentAnswer.studentAnswer), 오답률)
        Optional<StudentEntity> studentEntity = this.studentRepository.findById(userId);
        StudentAnswerEntity studentAnswer = this.studentAnswerRepository.findByStudentEntityAndExamNumberAndExamSubject(studentEntity.get(), examInfo.getExamNumber(), examInfo.getExamSubject());
        if(studentAnswer == null) throw new BaseException(BaseResponseStatus.POST_USERS_NOT_FOUND_ANSWER);
        // 해당 학생의 해당 회차, 과목의 entity
        StudentAnswerEntity studentAnswerEntity = this.studentAnswerRepository.findByStudentEntityAndExamNumberAndExamSubject(studentEntity.get(), examInfo.getExamNumber(), examInfo.getExamSubject());
        if(studentAnswerEntity.getStudentScore() == null) throw new BaseException(BaseResponseStatus.POST_USERS_NOT_MARK);
        try {
            // 같은 학년의 학생들
            List<StudentEntity> studentEntities = this.studentRepository.findAllByGrade(studentEntity.get().getGrade());
            // 전체 학생수
            int totalStudent = this.studentAnswerRepository.countStudentAnswerEntitiesByExamNumberAndExamSubjectAndStudentEntityIn(examInfo.getExamNumber(), examInfo.getExamSubject(), studentEntities);
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

    public UserDto.GetGradeGraphRes getGradeGraphInfo(Long userId) throws BaseException{
        // output = 모든 회차별(회차, 과목, 해당 회차 점수, 해당 회차 등수(내 등수/전체 학생 수))
        try {
            List<UserDto.EachExamNumberInfo> eachExamNumberInfos = new ArrayList<>();
            Optional<StudentEntity> studentEntity = this.studentRepository.findById(userId);
            List<StudentAnswerEntity> studentAnswerEntityList = this.studentAnswerRepository.findStudentAnswerEntitiesByStudentEntity(studentEntity.get());
            List<StudentEntity> studentEntities = this.studentRepository.findAllByGrade(studentEntity.get().getGrade());
            for (StudentAnswerEntity studentAnswerEntity : studentAnswerEntityList) {
                if(studentAnswerEntity.getStudentScore() == null) continue;
                int totalStudents = this.studentAnswerRepository.countStudentAnswerEntitiesByExamNumberAndExamSubjectAndStudentEntityIn(studentAnswerEntity.getExamNumber(), studentAnswerEntity.getExamSubject(), studentEntities);
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

    public String inputStudentAnswers(Long userId, UserDto.PostInputStudentAnswersReq studentInput) throws BaseException{
        // input = userId(Student), 회차(examNumber), 과목(examSubject), 학생이 작성한 정답(studentAnswer),
        // output = 저장 성공 여부
        Optional<StudentEntity> studentEntity = this.studentRepository.findById(userId);
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
}
