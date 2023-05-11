package com.ams.amsbackend.repository;

import com.ams.amsbackend.domain.Role;
import com.ams.amsbackend.domain.SchoolType;
import com.ams.amsbackend.domain.StudentAnswerEntity;
import com.ams.amsbackend.domain.StudentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class StudentAnswerRepositoryTest {

    @Autowired
    StudentAnswerRepository studentAnswerRepository;

    @Test
    public void 상위5개출력() {
        StudentEntity studentEntity1 = new StudentEntity();
        StudentEntity studentEntity2 = new StudentEntity();
        StudentEntity studentEntity3 = new StudentEntity();
        StudentEntity studentEntity4 = new StudentEntity();
        StudentEntity studentEntity5 = new StudentEntity();
        StudentEntity studentEntity6 = new StudentEntity();
        StudentEntity studentEntity7 = new StudentEntity();
        StudentEntity studentEntity8 = new StudentEntity();
        StudentEntity studentEntity9 = new StudentEntity();
        ReflectionTestUtils.setField(studentEntity1,"name","studentName1");
        ReflectionTestUtils.setField(studentEntity2,"name","studentName2");
        ReflectionTestUtils.setField(studentEntity3,"name","studentName3");
        ReflectionTestUtils.setField(studentEntity4,"name","studentName4");
        ReflectionTestUtils.setField(studentEntity5,"name","studentName5");
        ReflectionTestUtils.setField(studentEntity6,"name","studentName6");
        ReflectionTestUtils.setField(studentEntity7,"name","studentName7");
        ReflectionTestUtils.setField(studentEntity8,"name","studentName8");
        ReflectionTestUtils.setField(studentEntity9,"name","studentName9");


        StudentAnswerEntity studentAnswerEntity1 = new StudentAnswerEntity();
        StudentAnswerEntity studentAnswerEntity2 = new StudentAnswerEntity();
        StudentAnswerEntity studentAnswerEntity3 = new StudentAnswerEntity();
        StudentAnswerEntity studentAnswerEntity4 = new StudentAnswerEntity();
        StudentAnswerEntity studentAnswerEntity5 = new StudentAnswerEntity();
        StudentAnswerEntity studentAnswerEntity6 = new StudentAnswerEntity();
        StudentAnswerEntity studentAnswerEntity7 = new StudentAnswerEntity();
        StudentAnswerEntity studentAnswerEntity8 = new StudentAnswerEntity();
        StudentAnswerEntity studentAnswerEntity9 = new StudentAnswerEntity();

        studentAnswerEntity1.setStudentEntity(studentEntity1);
        studentAnswerRepository.save(studentAnswerEntity1);
//
//        //강제set 이게맞나..?
//        ReflectionTestUtils.setField(studentAnswerEntity1,"studentScore",100);
//        ReflectionTestUtils.setField(studentAnswerEntity2,"studentScore",20);
//        ReflectionTestUtils.setField(studentAnswerEntity3,"studentScore",12);
//        ReflectionTestUtils.setField(studentAnswerEntity4,"studentScore",12);
//        ReflectionTestUtils.setField(studentAnswerEntity5,"studentScore",90);
//        ReflectionTestUtils.setField(studentAnswerEntity6,"studentScore",90);
//        ReflectionTestUtils.setField(studentAnswerEntity7,"studentScore",50);
//        ReflectionTestUtils.setField(studentAnswerEntity8,"studentScore",60);
//        ReflectionTestUtils.setField(studentAnswerEntity9,"studentScore",90);
//        ReflectionTestUtils.setField(studentAnswerEntity1,"studentEntity",studentEntity1);
//        ReflectionTestUtils.setField(studentAnswerEntity2,"studentEntity",studentEntity2);
//        ReflectionTestUtils.setField(studentAnswerEntity3,"studentEntity",studentEntity3);
//        ReflectionTestUtils.setField(studentAnswerEntity4,"studentEntity",studentEntity4);
//        ReflectionTestUtils.setField(studentAnswerEntity5,"studentEntity",studentEntity5);
//        ReflectionTestUtils.setField(studentAnswerEntity6,"studentEntity",studentEntity6);
//        ReflectionTestUtils.setField(studentAnswerEntity7,"studentEntity",studentEntity7);
//        ReflectionTestUtils.setField(studentAnswerEntity8,"studentEntity",studentEntity8);
//        ReflectionTestUtils.setField(studentAnswerEntity9,"studentEntity",studentEntity9);
//        ReflectionTestUtils.setField(studentAnswerEntity1,"examNumber",1);
//        ReflectionTestUtils.setField(studentAnswerEntity2,"examNumber",1);
//        ReflectionTestUtils.setField(studentAnswerEntity3,"examNumber",1);
//        ReflectionTestUtils.setField(studentAnswerEntity4,"examNumber",1);
//        ReflectionTestUtils.setField(studentAnswerEntity5,"examNumber",1);
//        ReflectionTestUtils.setField(studentAnswerEntity6,"examNumber",1);
//        ReflectionTestUtils.setField(studentAnswerEntity7,"examNumber",1);
//        ReflectionTestUtils.setField(studentAnswerEntity8,"examNumber",1);
//        ReflectionTestUtils.setField(studentAnswerEntity9,"examNumber",1);
//
//        studentAnswerRepository.save(studentAnswerEntity1);
//        studentAnswerRepository.save(studentAnswerEntity2);
//        studentAnswerRepository.save(studentAnswerEntity3);
//        studentAnswerRepository.save(studentAnswerEntity4);
//        studentAnswerRepository.save(studentAnswerEntity5);
//        studentAnswerRepository.save(studentAnswerEntity6);
//        studentAnswerRepository.save(studentAnswerEntity7);
//        studentAnswerRepository.save(studentAnswerEntity8);
//        studentAnswerRepository.save(studentAnswerEntity9);

//        List<StudentAnswerEntity> result = studentAnswerRepository.findTop5ByExamNumberOrderByStudentScoreDesc(1);
//        for (StudentAnswerEntity a : result) {
//            System.out.println("a.getStudentScore() = " + a.getStudentScore());
//        }
    }

}