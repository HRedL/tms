package com.qdu.tms.mapper;

import com.qdu.tms.Bean.Course;
import com.qdu.tms.Bean.Student;
import com.qdu.tms.Bean.StudentCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentCourseMapper {


     List<Course> getCoursesBySid(Integer sid);

    List<Course> criteriaGetCoursesBySid(@Param("course") Course course, @Param("sid") Integer sid);

    List<Student> getStudentsByCid(Integer cid);
}
