package com.qdu.tms.controller;

import com.qdu.tms.Bean.Course;
import com.qdu.tms.common.AjaxJson;
import com.qdu.tms.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentCourseController {

    @Autowired
    StudentCourseService studentCourseService;

    /**
     * 根据学生sid查询课程
     */
    @GetMapping("/getCoursesBySid")
    public AjaxJson getCoursesBySid(Integer sid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<Course> courses = studentCourseService.getCoursesBySid(sid);

            ajaxJson.getBody().put("courses",courses);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询课程成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询课程失败");
        }
        return ajaxJson;
    }

    @GetMapping("/criteriaGetCoursesBySid")
    public AjaxJson criteriaGetCoursesBySid(Course course,Integer sid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<Course> courses = studentCourseService.criteriaGetCoursesBySid(course,sid);

            ajaxJson.getBody().put("courses",courses);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询课程成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询课程失败");
        }
        return ajaxJson;
    }


}
