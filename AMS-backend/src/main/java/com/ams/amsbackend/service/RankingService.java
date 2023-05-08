package com.ams.amsbackend.service;

import com.ams.amsbackend.repository.StudentAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final StudentAnswerRepository studentAnswerRepository;

    public void service() {

    }

}


