package com.qdu.tms.controller;

import com.qdu.tms.Bean.FileQuestion;
import com.qdu.tms.Bean.Homework;
import com.qdu.tms.Bean.HomeworkQuestion;
import com.qdu.tms.common.AjaxJson;
import com.qdu.tms.common.HomeworkFileQuestion;
import com.qdu.tms.service.HomeworkQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("homeworkQuestion")
@RestController
public class HomeworkQuestionController {

    @Autowired
    HomeworkQuestionService homeworkQuestionService;


    @GetMapping("/getHomeworkQuestion")
    public AjaxJson getHomeworkQuestion(HomeworkQuestion homeworkQuestion) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            HomeworkQuestion homeworkQuestionData = homeworkQuestionService.getHomeworkQuestion(homeworkQuestion);
            ajaxJson.getBody().put("homeworkQuestion",homeworkQuestionData);

            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询单个作业成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询单个作业失败");
        }
        return ajaxJson;
    }

    @PostMapping("/addHomeworkSingleQuestion")
    public AjaxJson addHomeworkSingleQuestion(HomeworkQuestion homeworkQuestion) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            homeworkQuestionService.addHomeworkSingleQuestion(homeworkQuestion);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("添加题目成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加题目失败");
        }
        return ajaxJson;
    }

    @PostMapping("/addHomeworkFileQuestion")
    public AjaxJson addHomeworkFileQuestion(HomeworkQuestion homeworkQuestion) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            homeworkQuestionService.addHomeworkFileQuestion(homeworkQuestion);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("添加题目成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加题目失败");
        }
        return ajaxJson;
    }

    @DeleteMapping("/delHomeworkQuestion")
    public AjaxJson delHomeworkQuestion(HomeworkQuestion homeworkQuestion) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            homeworkQuestionService.delHomeworkQuestion1(homeworkQuestion);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("添加题目成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加题目失败");
        }
        return ajaxJson;
    }


    @GetMapping("/getFileQuestionsByTid")
    public AjaxJson getFileQuestionsByTid(Integer tid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<HomeworkFileQuestion> homeworkFileQuestions = homeworkQuestionService.getHomeworkFileQuestionsByTid(tid);

            ajaxJson.getBody().put("homeworkFileQuestions",homeworkFileQuestions);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询单选题成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询单选题失败");
        }
        return ajaxJson;
    }



//    @GetMapping("/getHomeworkQuestionByHId")
//    public AjaxJson getHomeworkQuestion(HomeworkQuestion homeworkQuestion) {
//        AjaxJson ajaxJson = new AjaxJson();
//        try {
//            HomeworkQuestion homeworkQuestionData = homeworkQuestionService.getHomeworkQuestion(homeworkQuestion);
//            ajaxJson.getBody().put("homeworkQuestion",homeworkQuestionData);
//
//            ajaxJson.setSuccess(true);
//            ajaxJson.setMsg("查询单个作业成功");
//        } catch (Exception e) {
//            ajaxJson.setSuccess(false);
//            ajaxJson.setMsg("查询单个作业失败");
//        }
//        return ajaxJson;
//    }



}
