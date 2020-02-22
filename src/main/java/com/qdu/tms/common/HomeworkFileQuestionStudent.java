package com.qdu.tms.common;

import com.qdu.tms.Bean.FileQuestion;
import com.qdu.tms.Bean.Homework;
import com.qdu.tms.Bean.HomeworkQuestion;
import com.qdu.tms.Bean.Student;

public class HomeworkFileQuestionStudent {

    private Integer id;
    private Homework homework;
    private FileQuestion fileQuestion;
    private Student student;
    private HomeworkQuestion homeworkQuestion;
    private Integer score;
    private String answer;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public FileQuestion getFileQuestion() {
        return fileQuestion;
    }

    public void setFileQuestion(FileQuestion fileQuestion) {
        this.fileQuestion = fileQuestion;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public HomeworkQuestion getHomeworkQuestion() {
        return homeworkQuestion;
    }

    public void setHomeworkQuestion(HomeworkQuestion homeworkQuestion) {
        this.homeworkQuestion = homeworkQuestion;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
