package com.qdu.tms.service;

import com.qdu.tms.Bean.Homework;
import com.qdu.tms.Bean.HomeworkQuestion;
import com.qdu.tms.Bean.HomeworkStudent;
import com.qdu.tms.Bean.Student;
import com.qdu.tms.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeworkService {


    @Autowired
    HomeworkMapper homeworkMapper;

    @Autowired
    HomeworkStudentMapper homeworkStudentMapper;

    @Autowired
    HomeworkQuestionMapper homeworkQuestionMapper;

    @Autowired
    HomeworkQuestionStudentMapper homeworkQuestionStudentMapper;

    @Autowired
    StudentCourseMapper studentCourseMapper;


    public List<Homework> getHomeworksByCondition1(Integer tid) {

        return homeworkMapper.getHomeworksByCondition1(tid);
    }

    public List<Homework> getHomeworksByCondition2(Integer tid) {

        return homeworkMapper.getHomeworksByCondition2(tid);
    }

    public Homework getHomeworkById(Integer id) {
        return homeworkMapper.getHomeworkById(id);
    }

    public List<Homework> getHomeworksByTid(Integer tid) {
        return homeworkMapper.getHomeworksByTid(tid);
    }

    @Transactional
    public void addHomework(Homework homework) {
        homeworkMapper.addHomework(homework);
        Homework homework1 = homeworkMapper.getHomework(homework);
        List<HomeworkStudent> homeworkStudents= new ArrayList<>();
        List<Student> students = studentCourseMapper.getStudentsByCid(homework1.getCourse().getId());
        for(Student student:students){
            HomeworkStudent homeworkStudent = new HomeworkStudent();
            homeworkStudent.setHomework(homework1);
            homeworkStudent.setStudent(student);
            homeworkStudent.setType("1");

            homeworkStudents.add(homeworkStudent);
        }

        homeworkStudentMapper.addHomeworkStudents(homeworkStudents);


    }

    public void updateHomework(Homework homework) {
        homeworkMapper.updateHomework(homework);
    }

    public void delHomeworks(Integer[] ids) {
        for(Integer id:ids){
            homeworkQuestionMapper.delHomeworkQuestionsByHid(id);
            homeworkStudentMapper.delHomeworkStudentsByHid(id);

            homeworkQuestionStudentMapper.delHomeworkQuestionStudentsByHid(id);
        }
        homeworkMapper.delHomeworkByIds(ids);

    }
}
