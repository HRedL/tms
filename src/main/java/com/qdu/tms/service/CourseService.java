package com.qdu.tms.service;

import com.qdu.tms.Bean.Course;
import com.qdu.tms.Bean.User;
import com.qdu.tms.mapper.CourseMapper;
import com.qdu.tms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseMapper courseMapper;


    public Course getCourseById(Integer id) {

        return courseMapper.getCourseById(id);
    }

    public List<Course> getAllCourses(){
        return courseMapper.getAllCourses();
    }


    public List<Course> getCourses(Course course){
        return courseMapper.getCourses(course);
    }

    public Course getCourse(Course course){
        return courseMapper.getCourse(course);
    }


    public List<Course> getCoursesByTid(Integer tid) {
        return courseMapper.getCoursesByTid(tid);
    }

    public List<Course> criteriaGetCoursesByTid(Course course, Integer tid) {
        return courseMapper.criteriaGetCoursesByTid(course,tid);
    }

    public Boolean validateCourse(Course course) {

        return courseMapper.getCourseByCno(course.getCno())!=null;
    }

    public void addCourse(Course course) {
        courseMapper.addCourse(course);

    }

    public void updateCourse(Course course) {

        courseMapper.updateCourse(course);
    }

    public void delCourses(Integer[] ids) {
        courseMapper.delCourses(ids);
    }
}
