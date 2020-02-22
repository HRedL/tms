package com.qdu.tms.common;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qdu.tms.Bean.Course;

import java.util.List;

/**
 * 将对象装换成 前端需要的json数据
 */
public class JsonUtil {

    public static JSONArray courseJson(List<Course> courses){


        JSONArray array = new JSONArray();

        for(Course course:courses){
            JSONObject object = new JSONObject();
            object.put("cno",course.getCno());
            object.put("name",course.getName());
            object.put("detail",course.getDetail());
            object.put("tnum",course.getTnum());
            object.put("teacher_name",course.getTeacher().getName());
            array.add(object);
        }
        return array;

    }





}
