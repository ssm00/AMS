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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class RankingService {

    private final StudentRepository studentRepository;
    private final StudentAnswerRepository studentAnswerRepository;
    private final ExamAnswerRepository examAnswerRepository;

    public List<EachStudentInfo> findTop5Student(Integer examNumber, Subject examSubject, Integer grade) throws BaseException {
        validateParams(examNumber, examSubject, grade);
        List<EachStudentInfo> top5studentList = new ArrayList<>();
        List<StudentAnswerEntity> scoreDescStudentAnswerList = studentAnswerRepository.findAllScoreDesc(examNumber, examSubject);
        emptyCheck(scoreDescStudentAnswerList);
        for (StudentAnswerEntity studentAnswerEntity : scoreDescStudentAnswerList) {
            Integer studentGrade = studentAnswerEntity.getStudentEntity().getGrade();
            if (studentGrade != grade) {
                continue;
            }
            Integer studentScore = studentAnswerEntity.getStudentScore();
            Integer studentRank = studentAnswerEntity.getStudentRank();
            String studentName = studentAnswerEntity.getStudentEntity().getName();
            validateTopScoreData(studentScore, studentRank, studentName);
            EachStudentInfo eachStudentInfo = EachStudentInfo.builder()
                    .studentScore(studentScore)
                    .studentRank(studentRank)
                    .name(studentName)
                    .build();
            top5studentList.add(eachStudentInfo);
        }
        notEnough5(top5studentList);
        return top5studentList;
    }

    private void notEnough5(List<EachStudentInfo> top5studentList) {
        while (top5studentList.size() < 5) {
            EachStudentInfo eachStudentInfo = EachStudentInfo.builder()
                    .studentScore(-1)
                    .studentRank(-1)
                    .name("없음")
                    .build();
            top5studentList.add(eachStudentInfo);
        }
    }
    private void emptyCheck(List<StudentAnswerEntity> scoreDescStudentAnswerList) throws BaseException {
        if (scoreDescStudentAnswerList.isEmpty()) {
            // 데이터를 찾지 못한 경우 예외 처리
            throw new BaseException(BaseResponseStatus.JPA_TOP5_STUDENT_NULL);
        }
    }

    private void validateTopScoreData(Integer studentScore, Integer studentRank, String studentName) throws BaseException {
        if (studentScore == null || studentRank == null || studentName == null) {
            throw new BaseException(BaseResponseStatus.STUDENT_ANSWER_NULL);
        }
    }

    private void validateParams(Integer examNumber, Subject examSubject, Integer grade) throws BaseException {
        if (examNumber == null || examSubject == null || grade == null) {
            throw new BaseException(BaseResponseStatus.PARAM_TOP5_STUDENT_NULL);
        }
    }

    public List<EachWrongRateInfo> findTop5WrongRate(Integer examNumber, Subject examSubject, Integer grade) throws BaseException{
        validateParameter(examNumber, examSubject,grade);
        ArrayList<EachWrongRateInfo> top5WrongRateList = new ArrayList<>();

        List<StudentAnswerEntity> beforeGradeFilter = studentAnswerRepository.findAllByExamNumberAndExamSubject(examNumber, examSubject);
        //학년 filter 적용
        List<StudentAnswerEntity> studentAnswerList = beforeGradeFilter.stream().filter(studentAnswerEntity -> studentAnswerEntity.getStudentEntity().getGrade() == grade).collect(Collectors.toList());
        ExamAnswerEntity examAnswerEntity = examAnswerRepository.findAllByExamNumberAndSubjectAndExamGrade(examNumber, examSubject,grade);

        validateData(studentAnswerList, examAnswerEntity);

        HashMap<String, Integer> wrongRateTable = makeWrongRateTable(studentAnswerList);
        //오답 해쉬 테이블 내림차순 정리
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
            //builder
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

    private static void validateData(List<StudentAnswerEntity> studentAnswerList, ExamAnswerEntity examAnswerEntity) throws BaseException {
        if (studentAnswerList.isEmpty() || examAnswerEntity == null) {
            // 데이터를 찾지 못한 경우 RuntimeException 처리
            throw new BaseException(BaseResponseStatus.JPA_TOP5_WRONGRATE_NULL);
        }
    }

    private static void validateParameter(Integer examNumber, Subject examSubject, Integer grade) throws BaseException {
        if (examNumber == null || examSubject == null || grade==null) {
            throw new BaseException(BaseResponseStatus.PARAM_TOP5_WRONGRATE_NULL);
        }
    }

    public List<EachScoreInfo> findTop5Score(String logInId) throws BaseException{
        if (logInId == null) {
            throw new BaseException(BaseResponseStatus.JPA_TOP5_SCORE);
        }
        ArrayList<EachScoreInfo> top5ScoreList = new ArrayList<>();
        Optional<StudentEntity> studentEntity = studentRepository.findByLoginId(logInId);
        if (studentEntity.isEmpty()) {
            // 데이터를 찾지 못한 경우 RuntimeException 처리
            throw new BaseException(BaseResponseStatus.JPA_TOP5_SCORE);
        }
        List<StudentAnswerEntity> studentAnswerList = studentEntity.get().getStudentAnswerList();
        if (studentAnswerList.isEmpty()) {
            // 데이터가 없는 경우 RuntimeException 처리
            throw new BaseException(BaseResponseStatus.ENTITYGRAPH_TOP5_SCORE);
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
            //학생 틀린 문제 정보 가져오기
            String incorrectAnswerLine = studentAnswerEntity.getIncorrectAnswer();
            String[] incorrectArray = incorrectAnswerLine.trim().split(",");
            //해쉬 테이블 채우기
            for (String problemNum : incorrectArray) {
                wrongTable.put(problemNum, wrongTable.getOrDefault(problemNum, 0) + 1);
            }
        }
        return wrongTable;
    }

}


