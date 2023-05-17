package com.ams.amsbackend.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetTopFiveScoreRes {
    /**
     * 개인 성적 상위 5개 회차정보, 점수, 등수
     */
    private List<EachScoreInfo> scoreInfoList;

}
