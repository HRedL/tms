package com.qdu.tms.Bean;

public class HomeworkQuestionStudent {
    private Integer id;
    private HomeworkQuestion homeworkQuestion;
    private Integer sid;
    private Integer score;
    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public HomeworkQuestion getHomeworkQuestion() {
        return homeworkQuestion;
    }

    public void setHomeworkQuestion(HomeworkQuestion homeworkQuestion) {
        this.homeworkQuestion = homeworkQuestion;
    }
}
