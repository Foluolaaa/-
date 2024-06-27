package com.example.demo.Service.Imp;

import com.example.demo.Service.UserService;
import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  userMapper;
    @Override
    public User findById(Integer id){
        return userMapper.findById(id);
    };
}
