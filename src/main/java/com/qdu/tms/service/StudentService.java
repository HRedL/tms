package com.qdu.tms.service;

import com.qdu.tms.Bean.Student;
import com.qdu.tms.Bean.User;
import com.qdu.tms.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    public List<Student> geAllStudents() {
        return studentMapper.getAllStudents();
    }


    public List<Student> getStudents(Student student){
        return studentMapper.getStudents(student);
    }

    public Student getStudent(Student student){
        return studentMapper.getStudent(student);
    }


    /**
     * 验证该user是否在数据库中已经存在
     * @return 存在即返回true
     */
    public Boolean validateStudent(Student student) {
        return studentMapper.getStudentBySno(student.getSno())!=null;
    }

    public Boolean addStudent(Student student) {

        return studentMapper.addStudent(student);
    }

    public Boolean updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    public void delStudents(Integer[] ids) {
        studentMapper.delStudents(ids);
    }


}
