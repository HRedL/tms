package com.qdu.tms.service;

import com.qdu.tms.Bean.*;
import com.qdu.tms.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SingleQuestionService {

    @Autowired
    SingleQuestionMapper singleQuestionMapper;


    @Autowired
    HomeworkQuestionStudentMapper homeworkQuestionStudentMapper;

    @Autowired
    HomeworkStudentMapper homeworkStudentMapper;

    @Autowired
    HomeworkMapper homeworkMapper;

    @Autowired
    HomeworkQuestionMapper homeworkQuestionMapper;

    public List<SingleQuestion> getSingleQuestionsByHid(Integer hid) {

        return singleQuestionMapper.getSingleQuestionsByHid(hid);
    }


    @Transactional
    public void addQuestionStudent(Integer sid, Integer hid,List<Integer> qids, List<String> answers) {

        //sid:学生id
        //hid:作业id
        //qids:问题ids
        //answers:答案

        //修改该学生的作业状态，改为“2”，即未提交
        homeworkStudentMapper.changeHomeworkStudentType(hid,sid,"2");
        if(qids.size()!=0){

            List<HomeworkQuestionStudent> homeworkQuestionStudentsData = homeworkQuestionStudentMapper.getQuestionByHidAndSid(hid,sid);


            List<Integer> hqidsData=new ArrayList<>();

            for(HomeworkQuestionStudent homeworkQuestionStudent:homeworkQuestionStudentsData){
                hqidsData.add(homeworkQuestionStudent.getHomeworkQuestion().getId());
            }

            List<HomeworkQuestionStudent> homeworkQuestionStudentsPage = new ArrayList<>();
            for(int i=0;i<qids.size();i++){
                HomeworkQuestionStudent homeworkQuestionStudent=new HomeworkQuestionStudent();

                HomeworkQuestion homeworkQuestion = new HomeworkQuestion();

                homeworkQuestion.setHid(hid);
                homeworkQuestion.setType(1);
                homeworkQuestion.setQid(qids.get(i));
                HomeworkQuestion homeworkQuestionData = homeworkQuestionMapper.getHomeworkQuestion(homeworkQuestion);

                homeworkQuestionStudent.setSid(sid);
                homeworkQuestionStudent.setAnswer(answers.get(i));


                homeworkQuestionStudent.setHomeworkQuestion(homeworkQuestionData);
                homeworkQuestionStudentsPage.add(homeworkQuestionStudent);
            }

            List<HomeworkQuestionStudent> homeworkQuestionStudentsAddList = new ArrayList<>();
            List<HomeworkQuestionStudent> homeworkQuestionStudentsUpdateList = new ArrayList<>();

            for(HomeworkQuestionStudent homeworkQuestionStudent:homeworkQuestionStudentsPage){
                if(hqidsData.contains(homeworkQuestionStudent.getHomeworkQuestion().getId())){
                    homeworkQuestionStudentsUpdateList.add(homeworkQuestionStudent);
                }else{
                    homeworkQuestionStudentsAddList.add(homeworkQuestionStudent);
                }
            }

            if(homeworkQuestionStudentsAddList.size()!=0){
                homeworkQuestionStudentMapper.addHomeworkQuestionStudents(homeworkQuestionStudentsAddList);
            }

            if(homeworkQuestionStudentsUpdateList.size()!=0){
                homeworkQuestionStudentMapper.updateHomeworkQuestionStudents(homeworkQuestionStudentsUpdateList);
            }
        }
    }



    public List<HomeworkQuestionStudent> getSingleQuestionsByHidAndSid(Integer hid, Integer sid) {

        return homeworkQuestionStudentMapper.getSingleQuestionByHidAndSid(sid,hid);
    }


    @Transactional
    public void submitQuestionStudent(Integer sid, Integer hid, List<Integer> qids, List<String> answers) {
        //修改该学生的作业状态，改为“3”，即已完成
        homeworkStudentMapper.changeHomeworkStudentType(hid,sid,"3");

        //设置已完成作业人数+1
        homeworkMapper.increaseHomework(hid);

        if(qids.size()!=0){

            List<HomeworkQuestionStudent> homeworkQuestionStudentsData = homeworkQuestionStudentMapper.getQuestionByHidAndSid(hid,sid);


            List<Integer> hqidsData=new ArrayList<>();

            for(HomeworkQuestionStudent homeworkQuestionStudent:homeworkQuestionStudentsData){
                hqidsData.add(homeworkQuestionStudent.getHomeworkQuestion().getId());
            }

            List<HomeworkQuestionStudent> homeworkQuestionStudentsPage = new ArrayList<>();
            for(int i=0;i<qids.size();i++){
                HomeworkQuestionStudent homeworkQuestionStudent=new HomeworkQuestionStudent();

                HomeworkQuestion homeworkQuestion = new HomeworkQuestion();

                homeworkQuestion.setHid(hid);
                homeworkQuestion.setType(1);
                homeworkQuestion.setQid(qids.get(i));
                HomeworkQuestion homeworkQuestionData = homeworkQuestionMapper.getHomeworkQuestion(homeworkQuestion);

                homeworkQuestionStudent.setSid(sid);
                homeworkQuestionStudent.setAnswer(answers.get(i));
                homeworkQuestionStudent.setHomeworkQuestion(homeworkQuestionData);
                homeworkQuestionStudentsPage.add(homeworkQuestionStudent);
            }

            List<HomeworkQuestionStudent> homeworkQuestionStudentsAddList = new ArrayList<>();
            List<HomeworkQuestionStudent> homeworkQuestionStudentsUpdateList = new ArrayList<>();

            for(HomeworkQuestionStudent homeworkQuestionStudent:homeworkQuestionStudentsPage){
                if(hqidsData.contains(homeworkQuestionStudent.getHomeworkQuestion().getId())){
                    homeworkQuestionStudentsUpdateList.add(homeworkQuestionStudent);
                }else{
                    homeworkQuestionStudentsAddList.add(homeworkQuestionStudent);
                }
            }

            if(homeworkQuestionStudentsAddList.size()!=0){
                homeworkQuestionStudentMapper.addHomeworkQuestionStudents(homeworkQuestionStudentsAddList);
            }

            if(homeworkQuestionStudentsUpdateList.size()!=0){
                homeworkQuestionStudentMapper.updateHomeworkQuestionStudents(homeworkQuestionStudentsUpdateList);
            }

            setSingleQuestionGrade(sid,hid);

        }
    }

//    private void setSingleQuestionGrade1(Integer sid, Integer hid) {
//
//        List<QuestionStudent> questionStudents = questionStudentMapper.getSingleQuestionByHidAndSid(sid,hid);
//
//        List<SingleQuestion> singleQuestions=singleQuestionMapper.getSingleQuestionsByHid(hid);
//        for(SingleQuestion singleQuestion:singleQuestions){
//            for(QuestionStudent questionStudent:questionStudents){
//                if(singleQuestion.getId().equals(questionStudent.getQid())){
//                    if(singleQuestion.getAnswer().equals(questionStudent.getAnswer())){
//                        questionStudent.setScore(singleQuestion.getScore());
//                    }else{
//                        questionStudent.setScore(0);
//                    }
//                }
//            }
//        }
//
//        questionStudentMapper.updateScore(questionStudents);

//    }


    private void setSingleQuestionGrade(Integer sid, Integer hid) {

        List<HomeworkQuestionStudent> homeworkQuestionStudents = homeworkQuestionStudentMapper.getQuestionByHidAndSid(hid,sid);

        List<SingleQuestion> singleQuestions=singleQuestionMapper.getSingleQuestionsByHid(hid);
        for(SingleQuestion singleQuestion:singleQuestions){
            for(HomeworkQuestionStudent homeworkQuestionStudent:homeworkQuestionStudents){
                if(singleQuestion.getId().equals(homeworkQuestionStudent.getHomeworkQuestion().getQid())){
                    if(singleQuestion.getAnswer().equals(homeworkQuestionStudent.getAnswer())){
                        homeworkQuestionStudent.setScore(singleQuestion.getScore());
                    }else{
                        homeworkQuestionStudent.setScore(0);
                    }
                }
            }
        }

        homeworkQuestionStudentMapper.updateScore(homeworkQuestionStudents);

    }

    public List<SingleQuestion> getSingleQuestionsByCid(Integer cid) {

        return singleQuestionMapper.getSingleQuestionsByCid(cid);
    }

    public Boolean validateSingleQuestion(SingleQuestion singleQuestion) {

        return singleQuestionMapper.getSingleQuestion(singleQuestion)!=null;
    }

    public void addSingleQuestion(SingleQuestion singleQuestion) {

        singleQuestionMapper.addSingleQuestion(singleQuestion);
    }

    public SingleQuestion getSingleQuestionById(Integer id) {

        return singleQuestionMapper.getSingleQuestionById(id);
    }

    public void updateSingleQuestion(SingleQuestion singleQuestion) {
        singleQuestionMapper.updateSingleQuestion(singleQuestion);
    }

    public void delSingleQuestions(Integer[] ids) {
        singleQuestionMapper.delSingleQuestions(ids);
    }


//    private Map<Integer,QuestionStudent> getMapWithQid(List<QuestionStudent> questionStudents){
//        Map<Integer,QuestionStudent> map=new HashMap<>();
//        for(QuestionStudent questionStudent:questionStudents){
//            map.put(questionStudent.getQid(),questionStudent);
//        }
//        return map;
//    }
//

    public List<SingleQuestion> getSingleQuestionsByHid2(Integer hid) {

        Homework homework = homeworkMapper.getHomeworkById(hid);

        List<SingleQuestion> exceptSingleQuestions = singleQuestionMapper.getSingleQuestionsByHid(hid);

        List<SingleQuestion> allSingleQuestions = singleQuestionMapper.getSingleQuestionsByCid(homework.getCourse().getId());

        List<SingleQuestion> singleQuestions = new ArrayList<>();
        if(exceptSingleQuestions.size()!=0){
            List<Integer> exceptIds = new ArrayList<>();
            for(SingleQuestion singleQuestion:exceptSingleQuestions){
                exceptIds.add(singleQuestion.getId());
            }
            for(SingleQuestion singleQuestion:allSingleQuestions){
                if(!exceptIds.contains(singleQuestion.getId())){
                    singleQuestions.add(singleQuestion);
                }
            }
        }else{
            singleQuestions = allSingleQuestions;
        }

        return singleQuestions;
    }

}
