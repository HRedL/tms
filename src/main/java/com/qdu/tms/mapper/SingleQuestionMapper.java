package com.qdu.tms.mapper;

import com.qdu.tms.Bean.SingleQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SingleQuestionMapper {



    List<SingleQuestion> getSingleQuestionsByHid(Integer hid);

    List<SingleQuestion> getSingleQuestionsByCid(@Param("cid") Integer cid);

    SingleQuestion getSingleQuestion(SingleQuestion singleQuestion);

    void addSingleQuestion(SingleQuestion singleQuestion);

    SingleQuestion getSingleQuestionById(Integer id);

    void updateSingleQuestion(SingleQuestion singleQuestion);

    void delSingleQuestions(Integer[] ids);
}
