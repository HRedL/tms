package com.qdu.tms.mapper;

import com.qdu.tms.Bean.Homework;
import com.qdu.tms.Bean.HomeworkStudent;
import com.qdu.tms.Bean.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HomeworkMapper {



    Homework getHomeworkById(Integer id);

    List<Homework> getAllHomeworks();


    List<Homework> getHomeworks(Homework homework);

    Homework getHomework(Homework homework);

    List<Homework> getHomeworksByCondition1(@Param("tid") Integer tid);

    List<Homework> getHomeworksByCondition2(@Param("tid")Integer tid);


    void increaseHomework(Integer id);

    List<Homework> getHomeworksByTid(Integer tid);

    void addHomework(Homework homework);

    void updateHomework(Homework homework);

    void delHomeworkByIds(Integer[] ids);
}
