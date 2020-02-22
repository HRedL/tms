package com.qdu.tms.mapper;

import com.qdu.tms.Bean.Homework;
import com.qdu.tms.Bean.HomeworkStudent;
import com.qdu.tms.Bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeworkStudentMapper {



    List<HomeworkStudent> getHomeworksByType(@Param("sid") Integer sid, @Param("type") Integer type);

    void changeHomeworkStudentType(@Param("hid") Integer hid,@Param("sid")Integer sid,@Param("type")String type);

    void delHomeworkStudentsByHid(Integer hid);

    void addHomeworkStudents(List<HomeworkStudent> homeworkStudents);

//    List<Student> getStudentsByHidAndType(@Param("hid") Integer hid,@Param("type") Integer type);

    List<Student> getFinishedStudents(Integer hid);

    List<Student> getUnfinishedStudents(Integer hid);
}
