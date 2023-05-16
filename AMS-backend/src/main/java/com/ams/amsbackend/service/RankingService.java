package com.ams.amsbackend.service;

import com.ams.amsbackend.controller.dto.EachScoreInfo;
import com.ams.amsbackend.controller.dto.EachStudentInfo;
import com.ams.amsbackend.controller.dto.EachWrongRateInfo;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class RankingService {

    private final StudentRepository studentRepository;
    private final StudentAnswerRepository studentAnswerRepository;
    private final ExamAnswerRepository examAnswerRepository;

    public List<EachStudentInfo> findTop5Student(Integer examNumber, Subject examSubject) throws BaseException {
        if (examNumber == null || examSubject == null) {
            throw new BaseException(BaseResponseStatus.PARAMTOP5STUDENTNULL);
        }
        List<EachStudentInfo> top5studentList = new ArrayList<>();
        List<StudentAnswerEntity> top5ScoreStudentAnswer = studentAnswerRepository.findTop5ByExamNumberAndExamSubjectOrderByStudentScoreDesc(examNumber,examSubject);
        if (top5ScoreStudentAnswer.isEmpty()) {
            // 데이터를 찾지 못한 경우 예외 처리
            throw new BaseException(BaseResponseStatus.JPATOP5STUDENTNULL);
        }
        for (StudentAnswerEntity studentAnswerEntity : top5ScoreStudentAnswer) {
            Integer studentScore = studentAnswerEntity.getStudentScore();
            Integer studentRank = studentAnswerEntity.getStudentRank();
            String studentName = studentAnswerEntity.getStudentEntity().getName();
            if (studentScore == null || studentRank == null || studentName == null) {
                throw new BaseException(BaseResponseStatus.STUDENTANSWERNULL);
            }
            EachStudentInfo eachStudentInfo = EachStudentInfo.builder()
                    .studentScore(studentScore)
                    .studentRank(studentRank)
                    .name(studentName)
                    .build();
            top5studentList.add(eachStudentInfo);
        }
        return top5studentList;
    }

    public List<EachWrongRateInfo> findTop5WrongRate(Integer examNumber, Subject examSubject) throws BaseException{
        ArrayList<EachWrongRateInfo> top5WrongRateList = new ArrayList<>();
        if (examNumber == null || examSubject == null) {
            throw new BaseException(BaseResponseStatus.PARAMTOP5WRONGRATENULL);
        }
        List<StudentAnswerEntity> studentAnswerList = studentAnswerRepository.findAllByExamNumberAndExamSubject(examNumber, examSubject);
        ExamAnswerEntity examAnswerEntity = examAnswerRepository.findAllByExamNumberAndSubject(examNumber, examSubject);
        if (studentAnswerList.isEmpty() || examAnswerEntity == null) {
            // 데이터를 찾지 못한 경우 RuntimeException 처리
            throw new BaseException(BaseResponseStatus.JPATOP5WRONGRATENULL);
        }
        HashMap<String, Integer> wrongRateTable = makeWrongRateTable(studentAnswerList);
        List<Map.Entry<String, Integer>> descWrongRate = tableDescByValues(wrongRateTable);
        //limit는 size 유동적 결정 3개밖에없으면 3개만 return
        Stream<Map.Entry<String, Integer>> top5WrongProblems = descWrongRate.stream().limit(5);

        Integer numberOfStudent = studentAnswerList.size();
        float numberOfStudentFloat = numberOfStudent.floatValue();
        top5WrongProblems.forEach(entry -> {
            Integer problemNumber = Integer.valueOf(entry.getKey());
            Integer wrongPersonCount = entry.getValue();
            String[] examAnswerArray = examAnswerEntity.getExamAnswer().trim().split(",");
            Integer correctAnswer = Integer.valueOf(examAnswerArray[problemNumber-1]);
            float first = 0;
            float second = 0;
            float third = 0;
            float fourth = 0;
            float fifth = 0;
            for (StudentAnswerEntity studentAnswerEntity : studentAnswerList) {
                String[] studentAnswerArray = studentAnswerEntity.getStudentAnswer().trim().split(",");
                Integer studentSelectNumber = Integer.valueOf(studentAnswerArray[problemNumber - 1]);
                if (studentSelectNumber == 1) {
                    first++;
                }else if(studentSelectNumber == 2) {
                    second++;
                }else if (studentSelectNumber == 3) {
                    third++;
                }else if (studentSelectNumber == 4) {
                    fourth++;
                }else if (studentSelectNumber == 5) {
                    fifth++;
                }else{
                    log.error("1,2,3,4,5가 아닌값 있음 : "+studentSelectNumber);
                }
            }
            EachWrongRateInfo wrongRateInfo = EachWrongRateInfo.builder()
                    .problemNumber(problemNumber)
                    .correctAnswer(correctAnswer)
                    .wrongRate(calculatePercent(wrongPersonCount.floatValue(), numberOfStudentFloat))
                    .firstRate(calculatePercent(first, numberOfStudentFloat))
                    .secondRate(calculatePercent(second, numberOfStudentFloat))
                    .thirdRate(calculatePercent(third, numberOfStudentFloat))
                    .fourthRate(calculatePercent(fourth, numberOfStudentFloat))
                    .fifthRate(calculatePercent(fifth, numberOfStudentFloat))
                    .build();
            top5WrongRateList.add(wrongRateInfo);
        });
        return top5WrongRateList;
    }

    public List<EachScoreInfo> findTop5Score(Long userId) throws BaseException{
        if (userId == null) {
            throw new BaseException(BaseResponseStatus.JPATOP5SCORE);
        }
        ArrayList<EachScoreInfo> top5ScoreList = new ArrayList<>();
        Optional<StudentEntity> studentEntity = studentRepository.findById(userId);
        if (!studentEntity.isPresent()) {
            // 데이터를 찾지 못한 경우 RuntimeException 처리
            throw new BaseException(BaseResponseStatus.JPATOP5SCORE);
        }
        List<StudentAnswerEntity> studentAnswerList = studentEntity.get().getStudentAnswerList();
        if (studentAnswerList.isEmpty()) {
            // 데이터가 없는 경우 RuntimeException 처리
            throw new BaseException(BaseResponseStatus.ENTITYGRAPHTOP5SCORE);
        }
        studentAnswerList.sort((studentAnswerEntity1,studentAnswerEntity2) -> studentAnswerEntity2.getStudentScore() - studentAnswerEntity1.getStudentScore());
        Stream<StudentAnswerEntity> top5StudentAnswer = studentAnswerList.stream().limit(5);
        top5StudentAnswer.forEach(studentAnswerEntity -> {
            EachScoreInfo eachScoreInfo = EachScoreInfo.builder()
                    .score(studentAnswerEntity.getStudentScore())
                    .rank(studentAnswerEntity.getStudentRank())
                    .examNumber(studentAnswerEntity.getExamNumber())
                    .build();
            top5ScoreList.add(eachScoreInfo);
        });
        return top5ScoreList;
    }

    /**
     * 오답률 계산
     * 소수점 1자리수까지 계산
     */
    private float calculatePercent(float count, float total) {
        return (float) (Math.round(((count / total) * 100) * 10) / 10.0);
    }

    /**
     * 오답문제 해쉬테이블 많이틀린것 순서대로 내림차순정리
     * key : 문제번호, value : 틀린사람수
     */
    private List<Map.Entry<String, Integer>> tableDescByValues(HashMap<String, Integer> wrongRateTable) {
        ArrayList<Map.Entry<String, Integer>> entryList = new ArrayList<>(wrongRateTable.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return entryList;
    }

    /**
     * 오답문제 해쉬테이블 만들기
     * 전체문제아님 오답들만 들어가있음
     * key : 문제번호, value : 틀린사람수
     */
    private HashMap<String,Integer> makeWrongRateTable(List<StudentAnswerEntity> studentAnswerList) {
        HashMap<String, Integer> wrongTable = new HashMap<>();
        for (StudentAnswerEntity studentAnswerEntity : studentAnswerList) {
            String incorrectAnswerLine = studentAnswerEntity.getIncorrectAnswer();
            //regex 확인필요
            String[] incorrectArray = incorrectAnswerLine.trim().split(",");
            for (String problemNum : incorrectArray) {
                wrongTable.put(problemNum, wrongTable.getOrDefault(problemNum, 0) + 1);
            }
        }
        return wrongTable;
    }

}


