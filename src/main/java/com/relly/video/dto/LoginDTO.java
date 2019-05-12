package com.relly.video.dto;


/**
 * @Author Relly
 * @CreteTime 2019-05-09 22:45
 * @Description
 */
public class LoginDTO {
    private String phoneNumber;

    private String code;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
