package com.qdu.tms.controller;

import com.qdu.tms.Bean.Student;
import com.qdu.tms.Bean.User;
import com.qdu.tms.common.AjaxJson;
import com.qdu.tms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    /**
     * 查询所有学生
     */
    @GetMapping("/students")
    public AjaxJson getAllStudents() {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<Student> students = studentService.geAllStudents();

            ajaxJson.getBody().put("students",students);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询学生信息成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询学生信息失败");
        }
        return ajaxJson;
    }

    /**
     * 根据id查询学生
     */
    @GetMapping("/getStudentById")
    public AjaxJson getStudentById(Integer id) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            Student student = studentService.getStudentById(id);

            ajaxJson.getBody().put("student",student);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询学生信息成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询学生信息失败");
        }
        return ajaxJson;
    }

    /**
     * 根据type查看用户
     */
    @GetMapping("/criteriaQueryStudents")
    public AjaxJson getStudents(Student student) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            List<Student> students = studentService.getStudents(student);
            ajaxJson.getBody().put("students",students);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询学生信息成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询学生信息失败");
        }
        return ajaxJson;
    }




    @PostMapping("")
    public AjaxJson addStudent(Student student) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            Boolean flag = studentService.validateStudent(student);
            if(!flag){
                studentService.addStudent(student);
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

    @PutMapping("")
    public AjaxJson updateStudent(Student student) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            studentService.updateStudent(student);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("添加学生信息成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加学生信息失败");
        }
        return ajaxJson;
    }

    @DeleteMapping("delStudents")
    public AjaxJson delStudents(@RequestParam(value = "ids[]")Integer[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            studentService.delStudents(ids);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("删除学生信息成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("删除学生信息失败");
        }
        return ajaxJson;
    }


}
