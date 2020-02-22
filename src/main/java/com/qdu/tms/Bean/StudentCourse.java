package com.qdu.tms.Bean;

/**
 * 选课实体
 */
public class StudentCourse {
    private Integer id;
    private Student student; //学生
    private Course course; //课程

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
