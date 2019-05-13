package com.relly.video.common;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 全局异常处理
 * @author rellyCheng
 * @date 2019/5/6
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleViolationException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String requestURI = request.getRequestURI();
            responseWithJson(response, new JsonResult(1, e.getMessage()));

    }


    private void responseWithJson(HttpServletResponse response, JsonResult jsonResult) throws IOException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(jsonResult));
    }

}
