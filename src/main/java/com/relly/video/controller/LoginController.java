package com.relly.video.controller;

import com.relly.video.common.*;
import com.relly.video.dto.LoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.Random;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/home/")
public class LoginController {

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("getCode")
    public JsonResult getCode( String phoneNumber){
        //校验手机号
        checkPhone(phoneNumber);
        //生成一个6位0~9之间的随机字符串
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            buffer.append(random.nextInt(10));
        }
        try {
            System.out.println(buffer.toString());
            if(!SMScode.sendCode(phoneNumber, buffer.toString())) {
                throw new ServiceException("验证码发送失败！");
            }
            redisUtil.set(phoneNumber,buffer.toString());
            redisUtil.expire(phoneNumber,300);
            return new JsonResult(buffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("短信发送失败");
        }
    }

    @PostMapping("login")
    public JsonResult login(@RequestBody LoginDTO loginDTO){
        //校验手机号
        checkPhone(loginDTO.getPhoneNumber());
        if (redisUtil.getExpire(loginDTO.getPhoneNumber())==-2){
            throw new ServiceException("验证码已过期！");
        }
        //从redis中拿出验证码
        String cacheCode = (String) redisUtil.get(loginDTO.getPhoneNumber());
        if(!cacheCode.equalsIgnoreCase(loginDTO.getCode())) {
           throw new ServiceException("验证码错误！");
        }
       return new JsonResult();
    }

    /**
     * 校验手机号
     * @param phone
     */
    public static void checkPhone(String phone){

        if(phone.trim().equals("") || phone == null) {
            System.out.println("手机号码为空！");
            throw new ServiceException("手机号码不能为空！");
        }

        if(!Pattern.matches("^1[3|4|5|7|8]\\d{9}$", phone)) {
            throw new ServiceException("手机号码格式错误！");
        }

    }
}
