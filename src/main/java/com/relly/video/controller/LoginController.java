package com.relly.video.controller;

import com.relly.video.common.JsonResult;
import com.relly.video.common.SMScode;
import com.relly.video.common.ServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.util.Random;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/home/")
public class LoginController {

    @RequestMapping("getCode")
    public JsonResult getCode(@NotBlank String phoneNumber,HttpServletRequest request){

        //校验手机号
        checkPhone(phoneNumber);

        //生成一个6位0~9之间的随机字符串
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            buffer.append(random.nextInt(10));
        }

        try {
            if(!SMScode.sendCode(phoneNumber, buffer.toString())) {
                throw new ServiceException("验证码发送失败！");
            } else {
                //将验证码、手机号码和当前的系统时间存储到session中
                request.getSession().setAttribute("code", buffer.toString());
                request.getSession().setAttribute("number", phoneNumber );
                request.getSession().setAttribute("time", System.currentTimeMillis());
                return new JsonResult("短信发送成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("短信发送失败");
        }
    }

    @PostMapping("login")
    public JsonResult login(@NotBlank String phone, @NotBlank String code,HttpServletRequest request){

        //校验手机号
        checkPhone(phone);

        //从session中拿出数据
        HttpSession session = request.getSession();
        String code_session = (String)session.getAttribute("code");
        String number = (String)session.getAttribute("number");
        Long time = (Long)session.getAttribute("time");
        //清除session中的数据
        session.removeAttribute("code");
        session.removeAttribute("number");
        session.removeAttribute("time");


        //验证码登录时效10分钟
        if((System.currentTimeMillis() - time) / 1000 / 60 >= 0) {
           throw new ServiceException("验证码已过期！");
        }
        //发送验证码的手机号码和登录时得到手机号码必须一致
        if(!number.trim().equalsIgnoreCase(phone)) {
            throw new ServiceException("手机号码不一致！");
        }

        if(!code_session.trim().equalsIgnoreCase(code)) {
           throw new ServiceException("验证码不一致！");
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
