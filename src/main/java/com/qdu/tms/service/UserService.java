package com.qdu.tms.service;

import com.qdu.tms.Bean.User;
import com.qdu.tms.mapper.UserMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    public User getUserById(Integer id) {

        return userMapper.getUserById(id);
    }

    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }


    public List<User> getUsers(User user){
        return userMapper.getUsers(user);
    }

    public User getUser(User user){
        return userMapper.getUser(user);
    }


    /**
     * 验证该user是否在数据库中已经存在
     * @return 存在即返回true
     */
    public Boolean validateUser(User user) {
        return userMapper.getUser(user)!=null;
    }

    public Boolean addUser(User user) {

        return userMapper.addUser(user);
    }

    public Boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public void delUsers(Integer[] ids) {
        userMapper.delUsers(ids);
    }
}
