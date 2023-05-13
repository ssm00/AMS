package com.ams.amsbackend.controller;

import com.ams.amsbackend.controller.dto.EachStudentInfo;
import com.ams.amsbackend.controller.dto.PostExamInfoReq;
import com.ams.amsbackend.controller.dto.PostTopFiveStudentInfoRes;
import com.ams.amsbackend.controller.dto.PostTopFiveWrongRateRes;
import com.ams.amsbackend.service.RankingService;
import com.ams.amsbackend.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        String examSubject = rankingRequestDto.getExamSubject();
        List<EachStudentInfo> top5Student = rankingService.findTop5Student(examNumber,examSubject);
        PostTopFiveStudentInfoRes response = PostTopFiveStudentInfoRes.builder()
                .examNumber(examNumber)
                .examSubject(examSubject)
                .top5StudentList(top5Student)
                .build();
        return new BaseResponse(response);
    }

    @PostMapping("wrong-rate")
    public BaseResponse<PostTopFiveWrongRateRes> wrong_rate() {
        return null;
    }

    @GetMapping("user-score")
    public BaseResponse<?> user_score() {
        return null;
    }
}
