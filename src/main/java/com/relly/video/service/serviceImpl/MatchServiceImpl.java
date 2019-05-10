package com.relly.video.service.serviceImpl;


import com.relly.video.common.RedisUtil;
import com.relly.video.service.MatchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    @Resource
    private RedisUtil redisUtil;

    private List<Integer> matchUserIdList;

    @Override
    public void matchUser(Integer userId) {
        if (redisUtil.get("matchUserIdList")!=null){
            matchUserIdList = (List<Integer>) redisUtil.get("matchUserIdList");
        }
        if (matchUserIdList.contains(userId)){
            return;
        }
        System.out.println("没插入之前有："+matchUserIdList);
        matchUserIdList.add(userId);
        redisUtil.lSet("matchUserIdList",matchUserIdList);
        System.out.println("插入之后有："+matchUserIdList);
    }
}
