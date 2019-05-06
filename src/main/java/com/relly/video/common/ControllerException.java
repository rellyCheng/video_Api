package com.relly.video.common;

/**
 * @author rellyCheng
 * @date 2019/5/6
 */
public class ControllerException extends RuntimeException {

    private String errorCode;

    private String message;

    public ControllerException(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
