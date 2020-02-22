package com.qdu.tms.mapper;

import com.qdu.tms.Bean.Student;
import com.qdu.tms.Bean.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {


    @Select("SELECT * FROM teacher where id = #{id}")
    Teacher getTeacherById(Integer id);

    @Select("SELECT * FROM teacher")
    List<Teacher> getAllTeachers();


    List<Teacher> getTeachers(Teacher teacher);

    Teacher getTeacher(Teacher teacher);

    Teacher getTeacherByTno(@Param("tno") String tno);

    void addTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    void delTeachers(Integer[] ids);
}
