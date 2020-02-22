package com.qdu.tms.controller;

//import com.qdu.tms.Bean.QuestionStudent;
import com.qdu.tms.Bean.*;
import com.qdu.tms.common.AjaxJson;
import com.qdu.tms.service.HomeworkService;
import com.qdu.tms.service.SingleQuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("singleQuestion")
public class SingleQuestionController {

    @Autowired
    SingleQuestionService singleQuestionService;

    @Autowired
    HomeworkService homeworkService;

    @GetMapping("/getSingleQuestionsByHid")
    public AjaxJson getSingleQuestionsByHid(Integer hid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<SingleQuestion> singleQuestions = singleQuestionService.getSingleQuestionsByHid(hid);
            Homework homework = homeworkService.getHomeworkById(hid);

            ajaxJson.getBody().put("singleQuestions",singleQuestions);
            ajaxJson.getBody().put("homework",homework);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询单选题成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询单选题失败");
        }
        return ajaxJson;
    }



    @GetMapping("/getSingleQuestionsByHidAndSid")
    public AjaxJson getSingleQuestionsByHidAndSid(Integer hid,Integer sid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<HomeworkQuestionStudent> homeworkQuestionStudents = singleQuestionService.getSingleQuestionsByHidAndSid(hid,sid);

            ajaxJson.getBody().put("homeworkQuestionStudents",homeworkQuestionStudents);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询单选题成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询单选题失败");
        }
        return ajaxJson;
    }

    @PostMapping("/addQuestionStudent")
    public AjaxJson addSingleQuestionStudent(@RequestParam("sid") Integer sid,@RequestParam("hid") Integer hid,
                                             @RequestParam(value = "qids[]",required = false) List<Integer> qids,
                                             @RequestParam(value = "answers[]",required =false) List<String> answers) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            if(qids==null){
                qids=new ArrayList<>();
            }

            if(answers==null){
                answers=new ArrayList<>();
            }

            singleQuestionService.addQuestionStudent(sid,hid,qids,answers);

            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("单选题保存成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("单选题保存失败");
        }
        return ajaxJson;
    }

    @PostMapping("/submitQuestionStudent")
    public AjaxJson submitQuestionStudent(@RequestParam("sid") Integer sid,@RequestParam("hid") Integer hid,
                                          @RequestParam(value = "qids[]",required = false) List<Integer> qids,
                                          @RequestParam(value = "answers[]",required = false) List<String> answers) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            if(qids==null){
                qids=new ArrayList<>();
            }
            if(answers==null){
                answers=new ArrayList<>();
            }
            singleQuestionService.submitQuestionStudent(sid,hid,qids,answers);

            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("单选题保存成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("单选题保存失败");
        }
        return ajaxJson;
    }

    @GetMapping("/getSingleQuestionsByCid")
    public AjaxJson getSingleQuestionsByCid(Integer cid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<SingleQuestion> singleQuestions = singleQuestionService.getSingleQuestionsByCid(cid);

            ajaxJson.getBody().put("singleQuestions",singleQuestions);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询单选题成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询单选题失败");
        }
        return ajaxJson;
    }

    @PostMapping("")
    public AjaxJson addSingleQuestion(SingleQuestion singleQuestion, @Param("cid")Integer cid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            Course course=new Course();
            course.setId(cid);
            singleQuestion.setCourse(course);

            Boolean flag = singleQuestionService.validateSingleQuestion(singleQuestion);
            if(!flag){
                singleQuestionService.addSingleQuestion(singleQuestion);
                ajaxJson.setSuccess(true);
                ajaxJson.setMsg("添加用户成功");
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("该题目已经存在");
            }
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加用户失败");
        }
        return ajaxJson;
    }


    @GetMapping("/{id}")
    public AjaxJson getSingleQuestionById(@PathVariable("id")Integer id) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            SingleQuestion singleQuestion = singleQuestionService.getSingleQuestionById(id);
            ajaxJson.getBody().put("singleQuestion",singleQuestion);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询单选题成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询单选题失败");
        }
        return ajaxJson;
    }

    @PutMapping("")
    public AjaxJson updateSingleQuestion(SingleQuestion singleQuestion) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            singleQuestionService.updateSingleQuestion(singleQuestion);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("修改题目成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("修改题目失败");
        }
        return ajaxJson;
    }

    @DeleteMapping("delSingleQuestions")
    public AjaxJson delSingleQuestions(@RequestParam(value = "ids[]")Integer[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            singleQuestionService.delSingleQuestions(ids);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("删除用户成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("删除用户失败");
        }
        return ajaxJson;
    }


    @GetMapping("/getSingleQuestionsByHid2")
    public AjaxJson getSingleQuestionsByHid2(Integer hid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<SingleQuestion> singleQuestions = singleQuestionService.getSingleQuestionsByHid2(hid);

            ajaxJson.getBody().put("singleQuestions",singleQuestions);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询单选题成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询单选题失败");
        }
        return ajaxJson;
    }


}
