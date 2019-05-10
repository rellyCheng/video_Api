package com.relly.video.service;

import com.relly.video.dto.LoginDTO;
import com.relly.video.entity.UserEntity;

public interface LoginService {
    UserEntity addUser(LoginDTO loginDTO);
}
