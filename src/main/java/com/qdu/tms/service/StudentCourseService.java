package com.qdu.tms.service;

import com.qdu.tms.Bean.Course;
import com.qdu.tms.Bean.StudentCourse;
import com.qdu.tms.mapper.StudentCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCourseService {

    @Autowired
    StudentCourseMapper studentCourseMapper;


    /**
     * 根据学生id查询课程
     */
    public List<Course> getCoursesBySid(Integer id) {
        return studentCourseMapper.getCoursesBySid(id);
    }

    /**
     * 根据学生id和查询条件查询课程
     */
    public List<Course> criteriaGetCoursesBySid(Course course, Integer sid) {
        return studentCourseMapper.criteriaGetCoursesBySid(course,sid);
    }
}
