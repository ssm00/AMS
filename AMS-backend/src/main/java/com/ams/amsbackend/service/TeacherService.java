package com.ams.amsbackend.service;

import com.ams.amsbackend.controller.dto.TeacherDto;
import com.ams.amsbackend.domain.ExamAnswerEntity;
import com.ams.amsbackend.domain.StudentAnswerEntity;
import com.ams.amsbackend.domain.StudentEntity;
import com.ams.amsbackend.repository.ExamAnswerRepository;
import com.ams.amsbackend.repository.StudentAnswerRepository;
import com.ams.amsbackend.repository.StudentRepository;
import com.ams.amsbackend.util.BaseException;
import com.ams.amsbackend.util.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final StudentAnswerRepository studentAnswerRepository;
    private final ExamAnswerRepository examAnswerRepository;
    private final StudentRepository studentRepository;
    public String markExams(String logInId, TeacherDto.BasicGetExamInfo examInfo) throws BaseException {
        // 예외처리: logInId가 teacher인지
        int todoCount = countToDoMark(examInfo);
        if(todoCount == 0) throw new BaseException(BaseResponseStatus.POST_USERS_NOT_FOUND_MARK);
        ExamAnswerEntity examAnswerEntity = this.examAnswerRepository.findAllByExamGradeAndExamNumberAndSubject(examInfo.getGrade(), examInfo.getExamNumber(), examInfo.getExamSubject());
        if(examAnswerEntity == null) throw new BaseException(BaseResponseStatus.POST_USERS_NOT_FOUND_ANSWER);
        try{
            // 채점하기

            String[] examAnswers = examAnswerEntity.getExamAnswer().split(",");
            String[] examAllotment = examAnswerEntity.getAllotment().split(",");
            List<StudentEntity> studentEntities = this.studentRepository.findAllByGrade(examInfo.getGrade());
            List<StudentAnswerEntity> studentAnswerEntityList = this.studentAnswerRepository.findAllByExamNumberAndExamSubjectAndStudentEntityIn(examInfo.getExamNumber(), examInfo.getExamSubject(), studentEntities);
            for(StudentAnswerEntity studentEntity : studentAnswerEntityList){
                if(studentEntity.getStudentScore() != null) continue;
                String[] studentAnswers = studentEntity.getStudentAnswer().split(",");
                for(int i=0; i<examAnswerEntity.getNumberOfQuestion(); i++){
                    if(studentAnswers[i].equals(examAnswers[i])){
                        studentEntity.updateCorrectAnswer(i+1);
                        studentEntity.updateStudentScore(Integer.parseInt(examAllotment[i]));
                        studentAnswerRepository.save(studentEntity);
                    }else {
                        studentEntity.updateIncorrectAnswer(i+1);
                        studentAnswerRepository.save(studentEntity);
                    }
                }
            }
            // 등수 매기기
            studentAnswerEntityList.sort(new Comparator<StudentAnswerEntity>() {
                @Override
                public int compare(StudentAnswerEntity o1, StudentAnswerEntity o2) {
                    return Integer.compare(o1.getStudentScore(), o2.getStudentScore()) == 1 ? -1 : Integer.compare(o1.getStudentScore(), o2.getStudentScore()) == 0 ? 0 : 1;
                }
            });
            int rank = 1;
            for(StudentAnswerEntity studentEntity : studentAnswerEntityList){
                studentEntity.updateStudentRank(rank);
                studentAnswerRepository.save(studentEntity);
                rank++;
            }
            return "채점이 완료되었습니다.";
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }

    }

    public TeacherDto.PostDistributionTableRes getDistributionTable(String logInId, TeacherDto.BasicGetExamInfo examInfo) throws BaseException{
        // input = userId(Teacher), 학년(grade), 회차(examNumber), 과목(examSubject)
        // output = 해당 학년(grade), 회차(examNumber), 과목(examSubject), 학생별 정보(학생 이름(name), 시험 점수(studentScore))
        // 예외처리: logInId가 teacher인지
        try {
            List<StudentEntity> studentEntities = this.studentRepository.findAllByGrade(examInfo.getGrade());
            List<StudentAnswerEntity> studentAnswerEntityList = this.studentAnswerRepository.findAllByExamNumberAndExamSubjectAndStudentEntityIn(examInfo.getExamNumber(), examInfo.getExamSubject(), studentEntities);
            List<TeacherDto.EachStudentScore> eachStudentScoreList = new ArrayList<>();
            int takeStudentCount = 0;
            for (StudentAnswerEntity studentAnswerEntity : studentAnswerEntityList) {
                if(studentAnswerEntity.getStudentScore() == null) continue;
                eachStudentScoreList.add(new TeacherDto.EachStudentScore(
                        studentAnswerEntity.getStudentEntity().getName(),
                        studentAnswerEntity.getStudentScore()
                ));
                takeStudentCount++;
            }
            return new TeacherDto.PostDistributionTableRes(
                    examInfo.getGrade(),
                    examInfo.getExamNumber(),
                    examInfo.getExamSubject(),
                    takeStudentCount,
                    eachStudentScoreList
            );
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public TeacherDto.PostDistributionCountTableRes getDistributionCountTable(String logInId, TeacherDto.BasicGetExamInfo examInfo) throws BaseException{
        // input = userId(Teacher), 학년(grade), 회차(examNumber), 과목(examSubject)
        // output = 해당 학년(grade), 회차(examNumber), 과목(examSubject),  시험 점수(studentScore), 해당점수의 명수)
        // 예외처리: logInId가 teacher인지
        try {
            List<StudentEntity> studentEntities = this.studentRepository.findAllByGrade(examInfo.getGrade());
            List<StudentAnswerEntity> studentAnswerEntityList = this.studentAnswerRepository.findAllByExamNumberAndExamSubjectAndStudentEntityIn(examInfo.getExamNumber(), examInfo.getExamSubject(), studentEntities);
            HashMap<Integer, Integer> scoreDistributionMap = new HashMap<>();
            int takeStudentCount = 0;
            for (StudentAnswerEntity studentAnswerEntity : studentAnswerEntityList) {
                if(studentAnswerEntity.getStudentScore() == null) continue;
                scoreDistributionMap.put(studentAnswerEntity.getStudentScore(), scoreDistributionMap.getOrDefault(studentAnswerEntity.getStudentScore(), 0) + 1);
                takeStudentCount++;
            }
            //내림차순정리
            ArrayList<Map.Entry<Integer, Integer>> sortedMap = new ArrayList<>(scoreDistributionMap.entrySet());
            sortedMap.sort(Map.Entry.comparingByKey(Comparator.reverseOrder()));
            ArrayList<TeacherDto.EachStudentScoreCount> eachStudentScoreCounts = new ArrayList<>();
            sortedMap.stream().forEach((scoreInfo) -> {
                Integer score = scoreInfo.getKey();
                Integer count = scoreInfo.getValue();
                TeacherDto.EachStudentScoreCount eachStudentScoreCount = TeacherDto.EachStudentScoreCount.builder()
                        .score(score)
                        .count(count)
                        .build();
                eachStudentScoreCounts.add(eachStudentScoreCount);
            });
            return new TeacherDto.PostDistributionCountTableRes(
                    examInfo.getGrade(),
                    examInfo.getExamNumber(),
                    examInfo.getExamSubject(),
                    takeStudentCount,
                    eachStudentScoreCounts
            );
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public TeacherDto.GetAverageGraphRes getAverageGraph(String logInId, TeacherDto.GetAverageGraphReq requestInfo) throws BaseException{
        // input = userId(Teacher), 학년(grade), 과목(examSubject)
        // output = 학년(grade), 과목(examSubject), 회차별 정보(회차(examNumber), 해당 회차 시험 평균 점수)
        // 예외처리: logInId가 Teacher인지
        try {
            List<StudentEntity> studentEntities = this.studentRepository.findAllByGrade(requestInfo.getGrade());
            List<StudentAnswerEntity> studentAnswerEntityList = this.studentAnswerRepository.findStudentAnswerEntitiesByStudentEntityInAndExamSubject(studentEntities, requestInfo.getExamSubject());
            HashMap<Integer, EachExamScore> examScoreHashMap = new HashMap<>();
            int takeStudentCount = 0;
            for (StudentAnswerEntity studentAnswerEntity : studentAnswerEntityList) {
                if(studentAnswerEntity.getStudentScore() == null) continue;
                takeStudentCount++;
                int examNumber = studentAnswerEntity.getExamNumber();
                int score = studentAnswerEntity.getStudentScore();
                if (examScoreHashMap.get(examNumber) == null) {
                    examScoreHashMap.put(examNumber, new EachExamScore(examNumber, score));
                } else {
                    EachExamScore examScore = examScoreHashMap.get(examNumber);
                    examScore.addScore(score);
                }
            }
            List<TeacherDto.EachAverageScore> eachAverageScoreList = new ArrayList<>();
            for (int i = 1; i <= examScoreHashMap.size(); i++) {
                eachAverageScoreList.add(new TeacherDto.EachAverageScore(
                        i,
                        examScoreHashMap.get(i).getAverageScore()
                ));
            }
            return new TeacherDto.GetAverageGraphRes(
                    requestInfo.getGrade(),
                    requestInfo.getExamSubject(),
                    takeStudentCount,
                    eachAverageScoreList
            );
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
    public TeacherDto.GetExistAnswerRes existAnswer(String logInId, TeacherDto.BasicGetExamInfo existAnswerInfo) throws BaseException{
        try {
            ExamAnswerEntity examAnswerEntity = this.examAnswerRepository.findAllByExamGradeAndExamNumberAndSubject(existAnswerInfo.getGrade(), existAnswerInfo.getExamNumber(), existAnswerInfo.getExamSubject());
            if (examAnswerEntity == null) return new TeacherDto.GetExistAnswerRes(false, "해당 정답이 존재하지 않습니다.");
            else return new TeacherDto.GetExistAnswerRes(true, "해당 정답이 존재합니다.");
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
    public String inputAnswer(String logInId, TeacherDto.PostInputAnswersReq inputAnswerInfo) throws BaseException{
        // 예외처리: logInId가 teacher인지
        try {
            ExamAnswerEntity examAnswerEntity = ExamAnswerEntity.builder()
                    .examGrade(inputAnswerInfo.getGrade())
                    .examNumber(inputAnswerInfo.getExamNumber())
                    .subject(inputAnswerInfo.getExamSubject())
                    .numberOfQuestion(inputAnswerInfo.getNumberOfQuestion())
                    .allotment(inputAnswerInfo.getAllotment())
                    .examAnswer(inputAnswerInfo.getExamAnswer())
                    .build();
            this.examAnswerRepository.save(examAnswerEntity);
            return "정답이 입력되었습니다.";
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public TeacherDto.getToDoMarkCountRes getToDoMarkCount(String logInId, TeacherDto.BasicGetExamInfo examInfo) throws BaseException{
        // input = userId(Teacher), 학년(grade), 회차(examNumber), 과목(examSubject)
        // output = 학년(grade), 회차(examNumber), 과목(examSubject), 채점 해야할 학생 수(점수가 입력 안 되어있는 학생들 수)
        // 예외처리: logInId가 teacher인지
        try {
            int todoCount = countToDoMark(examInfo);
            return new TeacherDto.getToDoMarkCountRes(
                    examInfo.getGrade(),
                    examInfo.getExamNumber(),
                    examInfo.getExamSubject(),
                    todoCount
            );
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    private int countToDoMark(TeacherDto.BasicGetExamInfo examInfo) {
        int todoCount = 0;
        List<StudentEntity> studentEntities = this.studentRepository.findAllByGrade(examInfo.getGrade());
        List<StudentAnswerEntity> studentAnswerEntityList = this.studentAnswerRepository.findAllByExamNumberAndExamSubjectAndStudentEntityIn(examInfo.getExamNumber(), examInfo.getExamSubject(), studentEntities);
        for (StudentAnswerEntity studentAnswerEntity : studentAnswerEntityList) {
            if (studentAnswerEntity.getStudentScore() == null) todoCount++;
        }
        return todoCount;
    }

    private class EachExamScore{
        public int examNumber;
        public int totalScore;
        public int count;

        public EachExamScore(int examNumber, int score){
            this.examNumber = examNumber;
            this.totalScore = score;
            this.count = 1;
        }
        public void addScore(int score){
            this.totalScore += score;
            this.count++;
        }
        public float getAverageScore(){
            return totalScore / (float)count;
        }
    }
}
