package com.qdu.tms.controller;

import com.qdu.tms.Bean.Course;
import com.qdu.tms.Bean.Homework;
import com.qdu.tms.Bean.HomeworkStudent;
import com.qdu.tms.Bean.Student;
import com.qdu.tms.common.AjaxJson;
import com.qdu.tms.service.HomeworkStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeworkStudentController {

    @Autowired
    HomeworkStudentService homeworkStudentService;

    /**
     * 根据type查询作业
     */
    @GetMapping("/getHomeworksByType")
    public AjaxJson getHomeworksByType(Integer sid, Integer type) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<HomeworkStudent> homeworkStudents = homeworkStudentService.getHomeworksByType(sid, type);

            ajaxJson.getBody().put("homeworkStudents",homeworkStudents);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询课程成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询课程失败");
        }
        return ajaxJson;
    }


    @GetMapping("/homeworkStudent/getFinishedDetail")
    public AjaxJson getFinishedDetail(Integer hid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<Student> finishedStudents = homeworkStudentService.getFinishedStudents(hid);
            List<Student> unfinishedStudents = homeworkStudentService.getUnfinishedStudents(hid);

            ajaxJson.getBody().put("finishedStudents",finishedStudents);
            ajaxJson.getBody().put("unfinishedStudents",unfinishedStudents);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询课程成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询课程失败");
        }
        return ajaxJson;
    }


}
