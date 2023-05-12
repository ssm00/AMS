package com.ams.amsbackend.controller;

import com.ams.amsbackend.controller.dto.PostExamInfoReq;
import com.ams.amsbackend.controller.dto.PostTopFiveStudentInfoRes;
import com.ams.amsbackend.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/ranking/")
@RestController
public class RankingController {

    @PostMapping("top-score")
    public BaseResponse<PostTopFiveStudentInfoRes> topScore(@RequestBody PostExamInfoReq rankingRequestDto) {
        return null;
    }

    @PostMapping("wrong-rate")
    public BaseResponse<?> wrong_rate() {
        return null;
    }

    @GetMapping("user-score")
    public BaseResponse<?> user_score() {
        return null;
    }
}
