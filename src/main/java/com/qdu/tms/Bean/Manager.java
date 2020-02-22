package com.qdu.tms.Bean;

/**
 * 管理员实体
 */
public class Manager {
    private Integer id;
    private String mno; //管理员号
    private String name; //管理员名
    private String dept; //管理员所在系

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}