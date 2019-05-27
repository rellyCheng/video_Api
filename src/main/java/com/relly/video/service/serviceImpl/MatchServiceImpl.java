package com.relly.video.service.serviceImpl;


import com.relly.video.common.PageResult;
import com.relly.video.common.RedisUtil;
import com.relly.video.dao.MatchHistoryMapper;
import com.relly.video.dao.UserMapper;
import com.relly.video.entity.MatchHistoryEntity;
import com.relly.video.entity.UserEntity;
import com.relly.video.handler.MessageEventHandler;
import com.relly.video.service.MatchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private MatchHistoryMapper matchHistoryMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public void matchUser(Integer userId) {
        List<Object> matchUserIdList = new ArrayList<>();
        //获取匹配池中的人数
        long lSize = redisUtil.lGetListSize("matchUserIdList");
        //获取匹配池中的所有人
        matchUserIdList = redisUtil.lGet("matchUserIdList",0,lSize);
        //如果当前userId 不存在匹配池中
        if (!matchUserIdList.contains(userId)){
            matchUserIdList.add(userId);
            redisUtil.lSet("matchUserIdList",userId);
        }

        System.out.println(matchUserIdList);
        System.out.println(redisUtil.lGet("matchUserIdList",0,lSize));

        //当匹配池中人数大于5时 随机抽取两位用户就行配对
        if (matchUserIdList.size()>1){
            Integer user1 =  (Integer)matchUserIdList.get(0);
            Integer user2 =  (Integer)matchUserIdList.get(1);
            System.out.println(user1+""+user2);
            //成功匹配到两位用户  从redis匹配池中移除这两个用户
            redisUtil.lRemove("matchUserIdList",1,user1);
            redisUtil.lRemove("matchUserIdList",1,user2);
            List<Integer> userList = new ArrayList<>();
            userList.add(user1);
            userList.add(user2);
            MessageEventHandler.sendnNewMatchEvent(userList);
            //插入匹配记录
            MatchHistoryEntity matchHistoryEntity = new MatchHistoryEntity();
            matchHistoryEntity.setUser1(user1);
            matchHistoryEntity.setUser2(user2);
            matchHistoryEntity.setStartTime(new Date());
            matchHistoryMapper.insert(matchHistoryEntity);
        }
    }

    @Override
    public List<MatchHistoryEntity> getMatchHistoryList(Integer size, Integer current,Integer userId) {
        int rowCount = matchHistoryMapper.getListCount(userId);
        PageResult<MatchHistoryEntity> pageResult = new PageResult<>(current, size, rowCount);
        List<MatchHistoryEntity> list = matchHistoryMapper.getMatchHistoryList(pageResult,userId);
        UserEntity userEntity;
        for (MatchHistoryEntity m : list) {
            m.setCurrentUserId(userId);
            if (m.getUser1() == userId){
                userEntity = userMapper.selectByPrimaryKey(m.getUser2());
                m.setOtherUserId(m.getUser2());
                m.setOtherUserPhone(userEntity.getUserName());
            }else {
                userEntity = userMapper.selectByPrimaryKey(m.getUser1());
                m.setOtherUserId(m.getUser1());
                m.setOtherUserPhone(userEntity.getUserName());
            }
        }
        return list;
    }
}
