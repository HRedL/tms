package com.qdu.tms.Bean;

/**
 * 考试成绩实体
 */
public class ExamGrade {
    private Integer id; //
    private Student student; //学生
    private Exam exam; //考试
    private String examResult; //考试成绩

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }
}
