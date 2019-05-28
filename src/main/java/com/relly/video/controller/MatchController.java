package com.relly.video.controller;

import com.relly.video.common.JsonResult;
import com.relly.video.entity.MatchHistoryEntity;
import com.relly.video.handler.MessageEventHandler;
import com.relly.video.service.MatchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Resource
    private MatchService matchService;


    @RequestMapping("/user")
    public JsonResult matchUser(@RequestParam Integer userId){
        matchService.matchUser(userId);
        return new JsonResult();
    }

    @RequestMapping("/getHistoryList")
    public JsonResult getHistoryList(@RequestParam Integer size,@RequestParam Integer current,@RequestParam Integer userId){
        List<MatchHistoryEntity> list = matchService.getMatchHistoryList(size,current,userId);
        return new JsonResult(list);
    }

    @RequestMapping("/senNewsMsg")
    public JsonResult senNewsMsg(@RequestParam String clientId,@RequestParam String msg){
        MessageEventHandler.sendnNewMsg(UUID.fromString(clientId),msg);
        return new JsonResult();
    }
}
