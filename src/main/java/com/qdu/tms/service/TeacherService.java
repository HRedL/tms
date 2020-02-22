package com.qdu.tms.service;

import com.qdu.tms.Bean.Teacher;
import com.qdu.tms.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    public Teacher getTeacherById(Integer id) {

        return teacherMapper.getTeacherById(id);
    }

    public List<Teacher> getAllTeachers() {
        return teacherMapper.getAllTeachers();
    }

    public Boolean validateTeacher(Teacher teacher) {

        return teacherMapper.getTeacherByTno(teacher.getTno())!=null;
    }

    public void addTeacher(Teacher teacher) {
        teacherMapper.addTeacher(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
    }

    public void delTeachers(Integer[] ids) {

        teacherMapper.delTeachers(ids);
    }

    public List<Teacher> getTeachers(Teacher teacher) {

        return teacherMapper.getTeachers(teacher);
    }
}
