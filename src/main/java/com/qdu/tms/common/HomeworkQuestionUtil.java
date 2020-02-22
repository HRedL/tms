package com.qdu.tms.common;

import com.qdu.tms.Bean.FileQuestion;
import com.qdu.tms.Bean.Homework;
import com.qdu.tms.Bean.SingleQuestion;

import java.util.List;

public class HomeworkQuestionUtil {

    private Homework homework;
    private List<SingleQuestion> singleQuestions;
    private List<FileQuestion> fileQuestions;

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public List<SingleQuestion> getSingleQuestions() {
        return singleQuestions;
    }

    public void setSingleQuestions(List<SingleQuestion> singleQuestions) {
        this.singleQuestions = singleQuestions;
    }

    public List<FileQuestion> getFileQuestions() {
        return fileQuestions;
    }

    public void setFileQuestions(List<FileQuestion> fileQuestions) {
        this.fileQuestions = fileQuestions;
    }
}
