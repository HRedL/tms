package com.qdu.tms.mapper;

import com.qdu.tms.Bean.Manager;
import com.qdu.tms.Bean.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ManagerMapper {


    @Select("SELECT * FROM MANAGER WHERE ID=#{id}")
    Manager getManagerById(Integer id);

    @Select("SELECT * FROM MANAGER")
    List<Manager> getAllManagers();


    List<Manager> getManagers(Manager manager);

    Manager getManager(Manager manager);
}
