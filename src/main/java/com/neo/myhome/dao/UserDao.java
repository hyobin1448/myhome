package com.neo.myhome.dao;

import com.neo.myhome.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    public User searchUser(String username);

}
