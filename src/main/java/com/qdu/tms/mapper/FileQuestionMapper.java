package com.qdu.tms.mapper;

import com.qdu.tms.Bean.FileQuestion;
import com.qdu.tms.Bean.SingleQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileQuestionMapper {



    List<FileQuestion> getFileQuestionsByHid(Integer hid);

    FileQuestion getFileQuestionById(Integer id);



    void addFileQuestion(FileQuestion fileQuestion);

    void updateFileQuestion(FileQuestion fileQuestion);

    void delFileQuestions(Integer[] ids);

    //Boolean getFileQuestion(FileQuestion fileQuestion);

    List<FileQuestion> getFileQuestionsByCid(@Param("cid") Integer cid);

    FileQuestion getFileQuestion(FileQuestion fileQuestion);
}
