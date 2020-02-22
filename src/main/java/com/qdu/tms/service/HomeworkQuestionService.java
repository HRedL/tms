package com.qdu.tms.service;

import com.qdu.tms.Bean.*;
import com.qdu.tms.common.HomeworkFileQuestion;
import com.qdu.tms.common.HomeworkQuestionUtil;
import com.qdu.tms.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeworkQuestionService {


    @Autowired
    SingleQuestionMapper singleQuestionMapper;

    @Autowired
    FileQuestionMapper fileQuestionMapper;

    @Autowired
    HomeworkMapper homeworkMapper;

    @Autowired
    HomeworkQuestionMapper homeworkQuestionMapper;

    public HomeworkQuestionUtil getHomeworkQuestionByHid(Integer hid) {
        HomeworkQuestionUtil homeworkQuestionUtil=new HomeworkQuestionUtil();

        List<SingleQuestion> singleQuestions = singleQuestionMapper.getSingleQuestionsByHid(hid);
        List<FileQuestion> fileQuestions = fileQuestionMapper.getFileQuestionsByHid(hid);

        Homework homework = homeworkMapper.getHomeworkById(hid);

        homeworkQuestionUtil.setHomework(homework);
        homeworkQuestionUtil.setSingleQuestions(singleQuestions);
        homeworkQuestionUtil.setFileQuestions(fileQuestions);

        return homeworkQuestionUtil;
    }

    public HomeworkQuestion getHomeworkQuestion(HomeworkQuestion homeworkQuestion) {

        return homeworkQuestionMapper.getHomeworkQuestion(homeworkQuestion);
    }

    public void addHomeworkSingleQuestion(HomeworkQuestion homeworkQuestion) {
        homeworkQuestionMapper.addHomeworkSingleQuestion(homeworkQuestion);
    }

    public void addHomeworkFileQuestion(HomeworkQuestion homeworkQuestion) {
        Homework homework = homeworkMapper.getHomeworkById(homeworkQuestion.getHid());
        FileQuestion fileQuestion = fileQuestionMapper.getFileQuestionById(homeworkQuestion.getQid());
        homeworkQuestion.setPath("/"+homework.getCourse().getTeacher().getId()+"/"
                +homework.getCourse().getId()+"/"+homework.getId()+"/"+fileQuestion.getId());
        homeworkQuestionMapper.addHomeworkFileQuestion(homeworkQuestion);
    }

    public void delHomeworkQuestion1(HomeworkQuestion homeworkQuestion) {
        homeworkQuestionMapper.delHomeworkQuestion1(homeworkQuestion);
    }

    public List<HomeworkFileQuestion> getHomeworkFileQuestionsByTid(Integer tid) {

        return homeworkQuestionMapper.getHomeworkFileQuestionsByTid(tid);
    }
}
