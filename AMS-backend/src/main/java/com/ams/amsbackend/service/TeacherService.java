package com.ams.amsbackend.service;

import com.ams.amsbackend.controller.dto.TeacherDto;
import com.ams.amsbackend.domain.ExamAnswerEntity;
import com.ams.amsbackend.domain.StudentAnswerEntity;
import com.ams.amsbackend.domain.StudentEntity;
import com.ams.amsbackend.repository.ExamAnswerRepository;
import com.ams.amsbackend.repository.StudentAnswerRepository;
import com.ams.amsbackend.util.BaseException;
import com.ams.amsbackend.util.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final StudentAnswerRepository studentAnswerRepository;
    private final ExamAnswerRepository examAnswerRepository;
    public String markExams(TeacherDto.BasicGetExamInfo examInfo) throws BaseException {
        try{
            // 채점하기
            ExamAnswerEntity examAnswerEntity = this.examAnswerRepository.findAllByExamNumberAndSubject(examInfo.getExamNumber(), examInfo.getExamSubject());
            String[] examAnswers = examAnswerEntity.getExamAnswer().split(",");
            String[] examAllotment = examAnswerEntity.getAllotment().split(",");
            List<StudentAnswerEntity> studentAnswerEntityList = this.studentAnswerRepository.findAllByExamNumberAndExamSubject(examInfo.getExamNumber(), examInfo.getExamSubject());
            for(StudentAnswerEntity studentEntity : studentAnswerEntityList){
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

    public TeacherDto.PostDistributionTableRes getDistributionTable(TeacherDto.BasicGetExamInfo examInfo) throws BaseException{
        // input = userId(Teacher), 회차(examNumber), 과목(examSubject)
        // output = 해당 회차(examNumber), 과목(examSubject), 학생별 정보(학생 이름(name), 시험 점수(studentScore))
        try {
            List<StudentAnswerEntity> studentAnswerEntityList = this.studentAnswerRepository.findAllByExamNumberAndExamSubject(examInfo.getExamNumber(), examInfo.getExamSubject());
            List<TeacherDto.EachStudentScore> eachStudentScoreList = new ArrayList<>();
            for (StudentAnswerEntity studentAnswerEntity : studentAnswerEntityList) {
                eachStudentScoreList.add(new TeacherDto.EachStudentScore(
                        studentAnswerEntity.getStudentEntity().getName(),
                        studentAnswerEntity.getStudentScore()
                ));
            }
            return new TeacherDto.PostDistributionTableRes(
                    examInfo.getExamNumber(),
                    examInfo.getExamSubject(),
                    eachStudentScoreList
            );
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public TeacherDto.GetAverageGraphRes getAverageGraph(TeacherDto.GetAverageGraphReq requestInfo) {
        // input = userId(Teacher), 과목(examSubject)
        // output = 과목(examSubject), 회차별 정보(회차(examNumber), 해당 회차 시험 평균 점수)

    }
}
