package com.qdu.tms.controller;

import com.qdu.tms.Bean.*;
import com.qdu.tms.common.AjaxJson;
import com.qdu.tms.service.ManagerService;
import com.qdu.tms.service.StudentService;
import com.qdu.tms.service.TeacherService;
import com.qdu.tms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    ManagerService managerService;

    @PostMapping("/login")
    public AjaxJson handleLogin(User user) {
        String url="";
        AjaxJson ajaxJson = new AjaxJson();
        try {
            User userData = userService.getUser(user);
            if(userData!=null){

                switch(user.getType()){
                    case 1:
                        url = "page/students/index.html";
                        Student student = studentService.getStudentById(userData.getRid());
                        ajaxJson.getBody().put("object",student);
                        break;
                    case 2 :
                        url = "page/teacher/index.html";
                        Teacher teacher = teacherService.getTeacherById(userData.getRid());
                        ajaxJson.getBody().put("object",teacher);
                        break;
                    case 3 :
                        url = "page/manager/index.html";
                        Manager manager = managerService.getManagerById(userData.getRid());
                        ajaxJson.getBody().put("object",manager);
                        break;
                }

                ajaxJson.getBody().put("url",url);
                ajaxJson.setSuccess(true);
                ajaxJson.setMsg("用户身份验证成功");
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("用户账号或者密码输出错误");
                ajaxJson.setMsg("-2");
            }
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("程序出错");
            ajaxJson.setErrorCode("-1");
        }
        return ajaxJson;
    }

    /**
     * 查询所有用户
     */
    @GetMapping("/users")
    public AjaxJson getAllUsers() {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<User> users = userService.getAllUsers();

            ajaxJson.getBody().put("users",users);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询用户成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询用户失败");
        }
        return ajaxJson;
    }

    /**
     * 根据id查询用户
     */
    @GetMapping("/getUserById")
    public AjaxJson getUserById(Integer id) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            User user = userService.getUserById(id);

            ajaxJson.getBody().put("user",user);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询用户成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询用户失败");
        }
        return ajaxJson;
    }

    /**
     * 根据type查看用户
     */
    @GetMapping("/getUsersByType")
    public AjaxJson getUsersByType(String type) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            User user = new User();
            switch (type) {
                case "student":
                    user.setType(1);
                    break;
                case "teacher":
                    user.setType(2);
                    break;
                case "manager":
                    user.setType(3);
                    break;
            }
            List<User> users = userService.getUsers(user);


            ajaxJson.getBody().put("users",users);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询用户成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询用户失败");
        }
        return ajaxJson;
    }

    /**
     * 根据type查询student、teacher、manager实体
     */
    @GetMapping("/getObjectsByType")
    public AjaxJson getObjectsByType(Integer type) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            switch (type) {
                case 1:
                    List<Student> students = studentService.geAllStudents();
                    ajaxJson.getBody().put("objects",students);
                    break;
                case 2:
                    List<Teacher> teachers = teacherService.getAllTeachers();
                    ajaxJson.getBody().put("objects",teachers);
                    break;
                case 3:
                    List<Manager> managers = managerService.getAllManagers();
                    ajaxJson.getBody().put("objects",managers);
                    break;
            }


            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询用户成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询用户失败");
        }
        return ajaxJson;
    }


    @PutMapping("")
    public AjaxJson updateUser(User user) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            userService.updateUser(user);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("添加用户成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加用户失败");
        }
        return ajaxJson;
    }

    @DeleteMapping("delUsers")
    public AjaxJson delUsers(@RequestParam(value = "ids[]")Integer[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            userService.delUsers(ids);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("删除用户成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("删除用户失败");
        }
        return ajaxJson;
    }



    @PostMapping("")
    public AjaxJson addUser(User user) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            switch (user.getType()) {
                case 1:
                    user.setAccount("s" + studentService.getStudentById(user.getRid()).getSno());
                    break;
                case 2:
                    user.setAccount("t" + teacherService.getTeacherById(user.getRid()).getTno());
                    break;
                case 3:
                    user.setAccount("m" + managerService.getManagerById(user.getRid()).getMno());
                    break;
            }
            //存在即返回true
            User user1 =new User();
            user1.setAccount(user.getAccount());
            Boolean flag = userService.validateUser(user1);
            if(!flag){
                userService.addUser(user);
                ajaxJson.setSuccess(true);
                ajaxJson.setMsg("添加用户成功");
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("该用户已经存在");
            }


        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加用户失败");
        }
        return ajaxJson;
    }


}
