package com.qdu.tms.mapper;

import com.qdu.tms.Bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {


    @Select("SELECT * FROM USER WHERE ID=#{id}")
    User getUserById(Integer id);

    @Select("SELECT * FROM USER")
    List<User> getAllUsers();


    List<User> getUsers(User user);

    User getUser(User user);


    Boolean addUser(User user);


    Boolean updateUser(User user);

    void delUsers(Integer[] ids);
}
