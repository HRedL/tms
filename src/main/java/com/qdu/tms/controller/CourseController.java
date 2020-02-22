package com.qdu.tms.controller;


import com.qdu.tms.Bean.Course;
import com.qdu.tms.Bean.Student;
import com.qdu.tms.Bean.Teacher;
import com.qdu.tms.common.AjaxJson;
import com.qdu.tms.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 查询所有课程
     */
    @GetMapping("/courses")
    public AjaxJson getAllCourses() {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<Course> courses = courseService.getAllCourses();

            ajaxJson.getBody().put("courses",courses);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询课程成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询课程失败");
        }
        return ajaxJson;
    }

    /**
     * 根据id查询课程
     */
    @GetMapping("/course/{id}")
    public AjaxJson getCourseById(@PathVariable("id") Integer id) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            Course course = courseService.getCourseById(id);

            ajaxJson.getBody().put("course",course);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询课程成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询课程失败");
        }
        return ajaxJson;
    }

//    /**
//     * 条件查询单个课程
//     */
//    @GetMapping("criteriaQueryCourse")
//    public AjaxJson criteriaQueryCourse(Course course) {
//        AjaxJson ajaxJson = new AjaxJson();
//        try {
//            Course courseData = courseService.getCourse(course);
//
//            ajaxJson.getBody().put("course",courseData);
//            ajaxJson.setSuccess(true);
//            ajaxJson.setMsg("查询课程成功");
//        } catch (Exception e) {
//            ajaxJson.setSuccess(false);
//            ajaxJson.setMsg("查询课程失败");
//        }
//        return ajaxJson;
//    }

    /**
     * 条件查询多个课程
     */
    @GetMapping("criteriaQueryCourses")
    public AjaxJson criteriaQueryCourses(Course course) {
        logger.warn(course.getCno());

        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<Course> courses = courseService.getCourses(course);

            ajaxJson.getBody().put("courses", courses);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询课程成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询课程失败");
        }
        return ajaxJson;
    }
    /**
     * 根据tid查询课程
     */
    @GetMapping("/course/getCoursesByTid")
    public AjaxJson getCoursesByTid(Integer tid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<Course> courses = courseService.getCoursesByTid(tid);

            ajaxJson.getBody().put("courses",courses);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询课程成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询课程失败");
        }
        return ajaxJson;
    }


    /**
     * 根据学生tid查询课程
     */
    @GetMapping("/criteriaGetCoursesByTid")
    public AjaxJson criteriaGetCoursesByTid(Course course,Integer tid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<Course> courses = courseService.criteriaGetCoursesByTid(course,tid);

            ajaxJson.getBody().put("courses",courses);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询课程成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询课程失败");
        }
        return ajaxJson;
    }


    @PostMapping("/course")
    public AjaxJson addCourse(Course course,Integer tid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            Teacher teacher =new Teacher();
            teacher.setId(tid);
            course.setTeacher(teacher);

            Boolean flag = courseService.validateCourse(course);
            if(!flag){
                courseService.addCourse(course);
                ajaxJson.setSuccess(true);
                ajaxJson.setMsg("添加学生信息成功");
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("该学生已经存在");
            }


        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加学生信息失败");
        }
        return ajaxJson;
    }

    @PutMapping("/course")
    public AjaxJson updateCourse(Course course) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            courseService.updateCourse(course);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("添加学生信息成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加学生信息失败");
        }
        return ajaxJson;
    }

    @DeleteMapping("/course/delCourses")
    public AjaxJson delCourses(@RequestParam(value = "ids[]")Integer[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            courseService.delCourses(ids);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("删除学生信息成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("删除学生信息失败");
        }
        return ajaxJson;
    }





}
