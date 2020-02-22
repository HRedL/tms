package com.qdu.tms.controller;

import com.qdu.tms.Bean.*;
import com.qdu.tms.common.AjaxJson;
import com.qdu.tms.common.HomeworkQuestionUtil;
import com.qdu.tms.service.FileQuestionService;
import com.qdu.tms.service.HomeworkQuestionService;
import com.qdu.tms.service.HomeworkService;
import com.qdu.tms.service.SingleQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class HomeworkController {

    @Autowired
    HomeworkService homeworkService;

    @Autowired
    SingleQuestionService singleQuestionService;

    @Autowired
    FileQuestionService fileQuestionService;

    @Autowired
    HomeworkQuestionService homeworkQuestionService;

    /**
     * 根据已完成（tnum = snum）、未完成查询作业（tnum！=snum）
     */
    @GetMapping("/getHomeworksByCondition")
    public AjaxJson getHomeworksByType(Integer tid, Integer type) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            if(type==1){
                List<Homework> homeworks = homeworkService.getHomeworksByCondition1(tid);
                ajaxJson.getBody().put("homeworks",homeworks);
            }else if(type==2){
                List<Homework> homeworks = homeworkService.getHomeworksByCondition2(tid);
                ajaxJson.getBody().put("homeworks",homeworks);
            }
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询课程成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询课程失败");
        }
        return ajaxJson;
    }


    @GetMapping("/homework/getQuestionsByHid")
    public AjaxJson getSingleQuestionsByHid(Integer hid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            HomeworkQuestionUtil homeworkQuestion = homeworkQuestionService.getHomeworkQuestionByHid(hid);

//            List<SingleQuestion> singleQuestions = singleQuestionService.getSingleQuestionsByHid(hid);
//
//            List<FileQuestion> fileQuestions = fileQuestionService.getFileQuestionsByHid(hid);
//            ajaxJson.getBody().put("singleQuestions",singleQuestions);
//            ajaxJson.getBody().put("fileQuestions",fileQuestions);
            ajaxJson.getBody().put("homeworkQuestion",homeworkQuestion);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询题目成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询单选题失败");
        }
        return ajaxJson;
    }


//

    @GetMapping("/homework/getHomeworksByTid")
    public AjaxJson getHomeworksByTid(Integer tid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            List<Homework> homeworks = homeworkService.getHomeworksByTid(tid);

            ajaxJson.getBody().put("homeworks",homeworks);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询作业成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询作业失败");
        }
        return ajaxJson;
    }

    @GetMapping("/homework/{id}")
    public AjaxJson getHomeworkById(@PathVariable("id") Integer id) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            Homework homework =homeworkService.getHomeworkById(id);
            ajaxJson.getBody().put("homework",homework);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询作业成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询作业失败");
        }
        return ajaxJson;
    }


    @PostMapping("/homework")
    public AjaxJson addHomework(Homework homework,Integer cid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            Course course = new Course();
            course.setId(cid);
            homework.setCourse(course);

            Date date = new Date();
            String str = "yyyy/MM/dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(str);
            homework.setStartTime(sdf.format(date));

            homeworkService.addHomework(homework);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("添加作业成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加作业失败");
        }
        return ajaxJson;
    }


    @PutMapping("/homework")
    public AjaxJson updateHomework(Homework homework,Integer cid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            Course course = new Course();
            course.setId(cid);
            homework.setCourse(course);

            homeworkService.updateHomework(homework);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("添加作业成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加作业失败");
        }
        return ajaxJson;
    }

    @DeleteMapping("/homework/delHomeworks")
    public AjaxJson delSingleQuestions(@RequestParam(value = "ids[]")Integer[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            homeworkService.delHomeworks(ids);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("删除用户成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("删除用户失败");
        }
        return ajaxJson;
    }

}
