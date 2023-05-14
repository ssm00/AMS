package com.ams.amsbackend.service;

import com.ams.amsbackend.controller.dto.EachStudentInfo;
import com.ams.amsbackend.controller.dto.EachWrongRateInfo;
import com.ams.amsbackend.domain.ExamAnswerEntity;
import com.ams.amsbackend.domain.StudentAnswerEntity;
import com.ams.amsbackend.repository.ExamAnswerRepository;
import com.ams.amsbackend.repository.StudentAnswerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class RankingService {

    private final StudentAnswerRepository studentAnswerRepository;
    private final ExamAnswerRepository examAnswerRepository;

    public List<EachStudentInfo> findTop5Student(Integer examNumber, String examSubject) {
        List<EachStudentInfo> top5studentList = new ArrayList<>();
        List<StudentAnswerEntity> top5ScoreStudentAnswer = studentAnswerRepository.findTop5ByExamNumberAndExamSubjectOrderByStudentScoreDesc(examNumber,examSubject);
        for (StudentAnswerEntity studentAnswerEntity : top5ScoreStudentAnswer) {
            Integer studentScore = studentAnswerEntity.getStudentScore();
            Integer studentRank = studentAnswerEntity.getStudentRank();
            String studentName = studentAnswerEntity.getStudentEntity().getName();
            EachStudentInfo eachStudentInfo = EachStudentInfo.builder()
                    .studentScore(studentScore)
                    .studentRank(studentRank)
                    .name(studentName)
                    .build();
            top5studentList.add(eachStudentInfo);
        }
        return top5studentList;
    }

    public List<EachWrongRateInfo> findTop5WrongRate(Integer examNumber, String examSubject) {
        ArrayList<EachWrongRateInfo> top5WrongRateList = new ArrayList<>();
        List<StudentAnswerEntity> studentAnswerList = studentAnswerRepository.findAllByExamNumberAndExamSubject(examNumber, examSubject);
        ExamAnswerEntity examAnswerEntity = examAnswerRepository.findAllByExamNumberAndSubject(examNumber, examSubject);

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

    private float calculatePercent(float count, float total) {
        return (float) (Math.round(((count / total) * 100) * 10) / 10.0);
    }

    /**
     * 오답률 해쉬테이블 많이틀린것 순서대로 내림차순정리
     * key : 문제번호, value : 틀린사람수
     */
    private List<Map.Entry<String, Integer>> tableDescByValues(HashMap<String, Integer> wrongRateTable) {
        ArrayList<Map.Entry<String, Integer>> entryList = new ArrayList<>(wrongRateTable.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return entryList;
    }

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


