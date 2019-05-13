package com.relly.video.service;


import com.relly.video.entity.MatchHistoryEntity;

import java.util.List;

public interface MatchService {

    void matchUser(Integer userId);

    List<MatchHistoryEntity> getMatchHistoryList(Integer size, Integer current,Integer userId);
}
