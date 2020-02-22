package com.qdu.tms.mapper;

import com.qdu.tms.Bean.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface StudentMapper {

    @Select("SELECT * FROM STUDENT WHERE ID=#{id}")
    Student getStudentById(Integer id);

    @Select("SELECT * FROM STUDENT")
    List<Student> getAllStudents();


    List<Student> getStudents(Student student);

    Student getStudent(Student student);


    Boolean addStudent(Student student);


    Boolean updateStudent(Student student);


    void delStudents(Integer[] ids);

    Student getStudentBySno(@Param("sno") String sno);
}
