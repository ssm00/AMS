package com.ams.amsbackend.controller;

import com.ams.amsbackend.controller.dto.*;
import com.ams.amsbackend.domain.Subject;
import com.ams.amsbackend.service.RankingService;
import com.ams.amsbackend.util.BaseException;
import com.ams.amsbackend.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/ranking/")
@RestController
public class RankingController {

    private final RankingService rankingService;
    /**
     * ex) 2회차 시험 성적상위 1~5등
     */
    @PostMapping("top5-score")
    public BaseResponse<PostTopFiveStudentInfoRes> top5Score(@RequestBody PostExamInfoReq rankingRequestDto) {
        Integer examNumber = rankingRequestDto.getExamNumber();
        Subject examSubject = rankingRequestDto.getExamSubject();
        List<EachStudentInfo> top5Student = null;
        try {
            top5Student = rankingService.findTop5Student(examNumber,examSubject);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        PostTopFiveStudentInfoRes response = PostTopFiveStudentInfoRes.builder()
                .examNumber(examNumber)
                .examSubject(examSubject)
                .top5StudentList(top5Student)
                .build();
        return new BaseResponse(response);
    }

    /**
     * ex) 2회차 시험 오답률 1~5등
     */
    @PostMapping("wrong-rate")
    public BaseResponse<PostTopFiveWrongRateRes> wrong_rate(@RequestBody PostExamInfoReq rankingRequestDto) {
        Integer examNumber = rankingRequestDto.getExamNumber();
        Subject examSubject = rankingRequestDto.getExamSubject();
        List<EachWrongRateInfo> top5WrongRate = null;
        try {
            top5WrongRate = rankingService.findTop5WrongRate(examNumber, examSubject);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        PostTopFiveWrongRateRes response = PostTopFiveWrongRateRes.builder()
                .examNumber(examNumber)
                .examSubject(examSubject)
                .wrongRateInfoList(top5WrongRate)
                .build();
        return new BaseResponse(response);
    }

    /**
     * ex) 수민이의 시험 성적 상위 5개 데이터
     * 3회차 98점 1등
     * 2회차 90점 2등....
     */
    @GetMapping("user-score")
    public BaseResponse<GetTopFiveScoreRes> user_score(Long userId) {
        List<EachScoreInfo> top5ScoreList = null;
        try {
            top5ScoreList = rankingService.findTop5Score(userId);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        GetTopFiveScoreRes response = GetTopFiveScoreRes.builder()
                .scoreInfoList(top5ScoreList)
                .build();
        return new BaseResponse(response);
    }
}
