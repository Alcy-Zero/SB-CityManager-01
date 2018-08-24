package com.hand.demo.api.service.impl;

import com.hand.demo.api.service.UserService;
import com.hand.demo.infra.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Integer> login(Map<String ,Object> map){
        return userMapper.login(map);
    }

}
