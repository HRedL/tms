package com.qdu.tms.controller;

import com.qdu.tms.Bean.Course;
import com.qdu.tms.Bean.Homework;
import com.qdu.tms.Bean.HomeworkQuestionStudent;
import com.qdu.tms.common.AjaxJson;
import com.qdu.tms.common.HomeworkFileQuestionStudent;
import com.qdu.tms.common.HomeworkQuestionUtil;
import com.qdu.tms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("homeworkQuestionStudent")
@RestController
public class HomeworkQuestionStudentController {

    @Autowired
    HomeworkQuestionStudentService homeworkQuestionStudentService;

    @GetMapping("/getHomeworkFileQuestionStudentsByTid")
    public AjaxJson getHomeworkFilQuestionStudentByTid(Integer tid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            List<HomeworkFileQuestionStudent> homeworkFileQuestionStudents=
                    homeworkQuestionStudentService.getHomeworkFilQuestionStudentByTid(tid);
            ajaxJson.getBody().put("homeworkFileQuestionStudents",homeworkFileQuestionStudents);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询失败");
        }
        return ajaxJson;
    }

    @GetMapping("/getHomeworkFileQuestionStudent")
    public AjaxJson getHomeworkFilQuestionStudent(Integer id) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            HomeworkFileQuestionStudent homeworkFileQuestionStudent=
                    homeworkQuestionStudentService.getHomeworkFilQuestionStudent(id);
            ajaxJson.getBody().put("homeworkFileQuestionStudent",homeworkFileQuestionStudent);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询失败");
        }
        return ajaxJson;
    }

    @PutMapping("")
    public AjaxJson updateFileQuestionScore(HomeworkQuestionStudent homeworkQuestionStudent) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            homeworkQuestionStudentService.updateHomeworkQuestionStudent(homeworkQuestionStudent);

            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询失败");
        }
        return ajaxJson;
    }



}
