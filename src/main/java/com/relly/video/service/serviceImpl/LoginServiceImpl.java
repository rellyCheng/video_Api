package com.relly.video.service.serviceImpl;

import com.relly.video.dao.UserMapper;
import com.relly.video.dto.LoginDTO;
import com.relly.video.entity.UserEntity;
import com.relly.video.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void addUser(LoginDTO loginDTO) {
        UserEntity userEntity = userMapper.selectByUserName(loginDTO.getPhoneNumber());
        if (userEntity==null){
            Date date = new Date();
            userEntity = UserEntity.builder()
                    .userName(loginDTO.getPhoneNumber())
                    .lastLoginTime(date)
                    .createTime(date)
                    .password("123123")
                    .isDelete(0)
                    .build();
            userMapper.insert(userEntity);
        }
    }
}
