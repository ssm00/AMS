package com.ams.amsbackend.controller;

import com.ams.amsbackend.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/ranking/")
@RestController
public class RankingController {

    

    @PostMapping("top-score")
    public BaseResponse<?> topScore() {

    }

    @PostMapping("wrong-rate")
    public BaseResponse<?> wrong_rate() {

    }

    @GetMapping("user-score")
    public BaseResponse<?> user_score() {

    }
}
