package com.qdu.tms.mapper;

import com.qdu.tms.Bean.Course;
import com.qdu.tms.Bean.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseMapper {

    Course getCourseById(Integer id);


    List<Course> getAllCourses();


    List<Course> getCourses(Course course);

    Course getCourse(Course course);


    List<Course> getCoursesByTid(Integer tid);

    List<Course> criteriaGetCoursesByTid(@Param("course") Course course, @Param("tid") Integer tid);

    Course getCourseByCno(@Param("cno") String cno);

    void addCourse(Course course);

    void updateCourse(Course course);

    void delCourses(Integer[] ids);
}
