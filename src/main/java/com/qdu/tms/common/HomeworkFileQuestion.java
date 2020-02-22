package com.qdu.tms.common;

import com.qdu.tms.Bean.FileQuestion;
import com.qdu.tms.Bean.Homework;

public class HomeworkFileQuestion {

    private Integer id;
    private Homework homework;
    private FileQuestion fileQuestion;


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


}
