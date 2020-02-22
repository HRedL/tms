package com.qdu.tms.controller;

import com.qdu.tms.Bean.Student;
import com.qdu.tms.Bean.Teacher;
import com.qdu.tms.common.AjaxJson;
import com.qdu.tms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("teacher")
@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    /**
     * 根据type查看用户
     */
    @GetMapping("/criteriaQueryTeachers")
    public AjaxJson getStudents(Teacher teacher) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            List<Teacher> teachers = teacherService.getTeachers(teacher);
            ajaxJson.getBody().put("teachers",teachers);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询学生信息成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询学生信息失败");
        }
        return ajaxJson;
    }

    @GetMapping("/teachers")
    public AjaxJson getAllTeachers() {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<Teacher> teachers = teacherService.getAllTeachers();

            ajaxJson.getBody().put("teachers",teachers);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询学生信息成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询学生信息失败");
        }
        return ajaxJson;
    }


    @PostMapping("")
    public AjaxJson addStudent(Teacher teacher) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            Boolean flag = teacherService.validateTeacher(teacher);
            if(!flag){
                teacherService.addTeacher(teacher);
                ajaxJson.setSuccess(true);
                ajaxJson.setMsg("添加教师信息成功");
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("该教师已经存在");
            }


        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加教师信息失败");
        }
        return ajaxJson;
    }

    @PutMapping("")
    public AjaxJson updateTeacher(Teacher teacher) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            teacherService.updateTeacher(teacher);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("添加教师信息成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加教师信息失败");
        }
        return ajaxJson;
    }

    @DeleteMapping("delTeachers")
    public AjaxJson delTeachers(@RequestParam(value = "ids[]")Integer[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            teacherService.delTeachers(ids);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("删除教师信息成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("删除教师信息失败");
        }
        return ajaxJson;
    }

    /**
     * 根据id查询学生
     */
    @GetMapping("/{id}")
    public AjaxJson getTeacherById(@PathVariable("id") Integer id) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            Teacher teacher = teacherService.getTeacherById(id);

            ajaxJson.getBody().put("teacher",teacher);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询教师信息成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询教师信息失败");
        }
        return ajaxJson;
    }





}
