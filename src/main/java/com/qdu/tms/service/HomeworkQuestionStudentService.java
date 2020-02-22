package com.qdu.tms.service;

import com.qdu.tms.Bean.*;
import com.qdu.tms.common.HomeworkFileQuestionStudent;
import com.qdu.tms.mapper.FileQuestionMapper;
import com.qdu.tms.mapper.HomeworkMapper;
import com.qdu.tms.mapper.HomeworkQuestionStudentMapper;
import com.qdu.tms.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeworkQuestionStudentService {

    @Autowired
    HomeworkQuestionStudentMapper homeworkQuestionStudentMapper;

    @Autowired
    HomeworkMapper homeworkMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    FileQuestionMapper fileQuestionMapper;

    public List<HomeworkFileQuestionStudent> getHomeworkFilQuestionStudentByTid(Integer tid) {
        List<HomeworkQuestionStudent> homeworkQuestionStudentsData = homeworkQuestionStudentMapper.getHomeworkQuestionStudentsByTid(tid);

        List<HomeworkQuestionStudent> homeworkQuestionStudents = new ArrayList<>();
        for(HomeworkQuestionStudent homeworkQuestionStudent:homeworkQuestionStudentsData){
            if(homeworkQuestionStudent.getHomeworkQuestion().getType()==2){
                homeworkQuestionStudents.add(homeworkQuestionStudent);
            }
        }

        List<HomeworkFileQuestionStudent> homeworkFileQuestionStudents = new ArrayList<>();
        for(HomeworkQuestionStudent homeworkQuestionStudent:homeworkQuestionStudents){
            HomeworkFileQuestionStudent homeworkFileQuestionStudent = new HomeworkFileQuestionStudent();
            Homework homework = homeworkMapper.getHomeworkById(homeworkQuestionStudent.getHomeworkQuestion().getHid());
            FileQuestion fileQuestion = fileQuestionMapper.getFileQuestionById(homeworkQuestionStudent.getHomeworkQuestion().getQid());
            Student student = studentMapper.getStudentById(homeworkQuestionStudent.getSid());


            homeworkFileQuestionStudent.setId(homeworkQuestionStudent.getId());
            homeworkFileQuestionStudent.setHomework(homework);
            homeworkFileQuestionStudent.setFileQuestion(fileQuestion);
            homeworkFileQuestionStudent.setStudent(student);
            homeworkFileQuestionStudent.setScore(homeworkQuestionStudent.getScore());
            homeworkFileQuestionStudent.setAnswer(homeworkQuestionStudent.getAnswer());


            homeworkFileQuestionStudents.add(homeworkFileQuestionStudent);

        }

        return homeworkFileQuestionStudents;

    }

    public HomeworkFileQuestionStudent getHomeworkFilQuestionStudent(Integer id) {
        HomeworkQuestionStudent homeworkQuestionStudent = homeworkQuestionStudentMapper.getHomeworkQuestionStudentById(id);

        FileQuestion fileQuestion = fileQuestionMapper.getFileQuestionById(homeworkQuestionStudent.getHomeworkQuestion().getQid());

        HomeworkFileQuestionStudent homeworkFileQuestionStudent = new HomeworkFileQuestionStudent();
        homeworkFileQuestionStudent.setScore(homeworkQuestionStudent.getScore());
        homeworkFileQuestionStudent.setId(homeworkQuestionStudent.getId());
        homeworkFileQuestionStudent.setFileQuestion(fileQuestion);

        return homeworkFileQuestionStudent;
    }

    public void updateHomeworkQuestionStudent(HomeworkQuestionStudent homeworkQuestionStudent) {

        homeworkQuestionStudentMapper.updateHomeworkQuestionStudent(homeworkQuestionStudent);
    }
}
