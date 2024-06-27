package com.example.demo.Mapper;

import com.example.demo.Pojo.User;
import  org.apache.
@Mapper
public interface UserMapper {
    public interface UserMapper {
        @Select("select * from user where id = #{id}")
        public User findById(Integer id);
    }
}
