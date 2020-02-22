package com.qdu.tms.service;

import com.qdu.tms.Bean.Homework;
import com.qdu.tms.Bean.HomeworkStudent;
import com.qdu.tms.Bean.Student;
import com.qdu.tms.mapper.HomeworkStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkStudentService {

    @Autowired
    HomeworkStudentMapper homeworkStudentMapper;

    public List<HomeworkStudent> getHomeworksByType(Integer sid, Integer type) {

        return homeworkStudentMapper.getHomeworksByType(sid, type);
    }

    public List<Student> getFinishedStudents(Integer hid) {

        return homeworkStudentMapper.getFinishedStudents(hid);
    }

    public List<Student> getUnfinishedStudents(Integer hid) {
        return homeworkStudentMapper.getUnfinishedStudents(hid);
    }
}
