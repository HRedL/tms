package com.qdu.tms.mapper;

import com.qdu.tms.Bean.HomeworkQuestion;
import com.qdu.tms.common.HomeworkFileQuestion;

import java.util.List;

public interface HomeworkQuestionMapper {


    HomeworkQuestion getHomeworkQuestionById(Integer id);

    HomeworkQuestion getHomeworkQuestion(HomeworkQuestion homeworkQuestion);

    void addHomeworkSingleQuestion(HomeworkQuestion homeworkQuestion);

    void addHomeworkFileQuestion(HomeworkQuestion homeworkQuestion);

    void delHomeworkQuestion1(HomeworkQuestion homeworkQuestion);

    List<HomeworkFileQuestion> getHomeworkFileQuestionsByTid(Integer tid);

    void delHomeworkQuestionsByHid(Integer hid);
}
