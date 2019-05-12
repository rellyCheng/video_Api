package com.relly.video.controller;

import com.relly.video.common.JsonResult;
import com.relly.video.handler.MessageEventHandler;
import com.relly.video.service.MatchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @RequestMapping("/sendMatch")
    public JsonResult sendMatch(String a){
        MessageEventHandler.sendnNewMatchEvent(a);
        return new JsonResult();
    }
}
