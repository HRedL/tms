package com.qdu.tms.mapper;

import com.qdu.tms.Bean.HomeworkQuestion;
import com.qdu.tms.Bean.HomeworkQuestionStudent;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeworkQuestionStudentMapper {

    void addHomeworkQuestionStudents(List<HomeworkQuestionStudent> homeworkQuestionStudents);

    void addHomeworkQuestionStudent(HomeworkQuestionStudent homeworkQuestionStudent);

    void updateHomeworkQuestionStudents(List<HomeworkQuestionStudent> homeworkQuestionStudents);

  //  HomeworkQuestionStudent getQuestionByHQidAndSid(@Param("hqid")Integer hqid,@Param("sid")Integer sid);


//    List<QuestionStudent> getSingleQuestionByHidAndSid(@Param("sid") Integer sid, @Param("hid") Integer hid);

    void updateScore(List<HomeworkQuestionStudent> homeworkQuestionStudents);

    List<HomeworkQuestionStudent> getQuestionByHidAndSid(@Param("hid") Integer hid, @Param("sid") Integer sid);


    List<HomeworkQuestionStudent> getSingleQuestionByHidAndSid(@Param("hid") Integer hid, @Param("sid") Integer sid);


    HomeworkQuestionStudent getHomeworkQuestionStudent(HomeworkQuestionStudent homeworkQuestionStudent);


    List<HomeworkQuestionStudent> getHomeworkQuestionStudents(HomeworkQuestionStudent homeworkQuestionStudent);

    void delHomeworkQuestionStudentsByHid(Integer hid);

    List<HomeworkQuestionStudent> getHomeworkQuestionStudentsByTid(Integer tid);

    HomeworkQuestionStudent getHomeworkQuestionStudentById(Integer id);

    void updateHomeworkQuestionStudent(HomeworkQuestionStudent homeworkQuestionStudent);

//    QuestionStudent getFileQuestionByHidAndQidAndSid(@Param("hid") Integer hid, @Param("qid") Integer qid, @Param("sid") Integer sid);

}
