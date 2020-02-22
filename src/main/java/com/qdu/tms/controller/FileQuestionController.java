package com.qdu.tms.controller;


import com.qdu.tms.Bean.Course;
import com.qdu.tms.Bean.FileQuestion;
import com.qdu.tms.Bean.Homework;
import com.qdu.tms.Bean.SingleQuestion;
import com.qdu.tms.common.AjaxJson;
import com.qdu.tms.service.FileQuestionService;
import com.qdu.tms.service.HomeworkService;
import org.apache.commons.fileupload.FileItem;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@RequestMapping("fileQuestion")
@RestController
public class FileQuestionController {

    @Autowired
    FileQuestionService fileQuestionService;

    @Autowired
    HomeworkService homeworkService;


//    @RequestMapping("/localUpLoadProgram31231")
//    @ResponseBody
//    public void localUpLoadProgram(@RequestParam("file") CommonsMultipartFile[] file,
//                                   HttpServletResponse response, HttpServletRequest request) throws Exception {
//
//        //判断是否为多文件上传
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//
//        //如果是多文件上传
//        if (isMultipart) {
//
//            String fileName = "";
//            Integer chunk = 0, chunks = 0;
//
//            //检查文件目录，不存在则创建
//            String relativePath = "";
//
//            String realPath = "C:/Users/Lenovo/Desktop/upload";
////            String realPath = ConfigUtil.class.getResource("/").getPath();
//
//            File folder = new File(realPath + relativePath);
//
//
//            if (!folder.exists()) {
//                folder.mkdirs();
//            }
//
//            DiskFileItemFactory diskFactory = new DiskFileItemFactory();
//            // threshold 极限、临界值，即硬盘缓存 1M
//            diskFactory.setSizeThreshold(4 * 1024);
//
//            ServletFileUpload upload = new ServletFileUpload(diskFactory);
//            // 设置允许上传的最大文件大小（单位MB）
//            upload.setSizeMax(1024 * 1048576);
//            upload.setHeaderEncoding("UTF-8");
//            List<FileItem> fileList = new ArrayList<FileItem>();
//
//            fileList.add(file[0].getFileItem());
//
//            Iterator<FileItem> it = fileList.iterator();
//            FileItem item = it.next();
//
//
//            String name = item.getFieldName();
//
//
//            InputStream input = item.getInputStream();
//
//
//            fileName = item.getName();
//            chunk = Integer.parseInt(request.getParameter("chunk"));
//            chunks = Integer.parseInt(request.getParameter("chunks"));
//            // 处理上传文件内容
//            if (!item.isFormField()) {
//                //目标文件
//                File destFile = new File(folder, fileName);
//                //文件已存在删除旧文件（上传了同名的文件）
//                if (chunk == 0 && destFile.exists()) {
//                    destFile.delete();
//                    destFile = new File(folder, fileName);
//                }
//                //合成文件
//                appendFile2(input, destFile);
//                if (chunk == chunks - 1) {
//                    System.out.println("上传完成");
//                } else {
//                    System.out.println("还剩[" + (chunks - 1 - chunk) + "]个块文件");
//                }
//            }
//        }
//    }
//

    @PostMapping("/uploadFileQuestion")
    public void uploadFileQuestion(@RequestParam("file") MultipartFile file,
                                   @RequestParam("hqid") Integer hqid, @RequestParam("sid") Integer sid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            fileQuestionService.uploadFileQuestion(file, hqid, sid);
            ajaxJson.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false);
        }
    }

    @PostMapping("/uploadAnswer")
    public void uploadAnswer(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            fileQuestionService.uploadAnswer(file, id);
//            fileQuestionService.uploadFileQuestion(file,hqid,sid);
            ajaxJson.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false);
        }
    }

    @PostMapping("/uploadDetail")
    public void uploadDetail(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            fileQuestionService.uploadDetail(file, id);
//            fileQuestionService.uploadFileQuestion(file,hqid,sid);
            ajaxJson.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false);
        }
    }



    @GetMapping("/getFileQuestionsByCid")
    public AjaxJson getFileQuestionsByCid(Integer cid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<FileQuestion> fileQuestions = fileQuestionService.getFileQuestionsByCid(cid);

            ajaxJson.getBody().put("fileQuestions", fileQuestions);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询文件上传题成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询文件上传题失败");
        }
        return ajaxJson;
    }


    @PostMapping("")
    public AjaxJson addFileQuestion(FileQuestion fileQuestion, @Param("cid") Integer cid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            Course course = new Course();
            course.setId(cid);
            fileQuestion.setCourse(course);

            Boolean flag = fileQuestionService.validateFileQuestion(fileQuestion);
            if (!flag) {
                fileQuestionService.addFileQuestion(fileQuestion);
                ajaxJson.setSuccess(true);
                ajaxJson.setMsg("添加用户成功");
            } else {
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("该题目已经存在");
            }
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加用户失败");
        }
        return ajaxJson;
    }


    @GetMapping("/{id}")
    public AjaxJson getFileQuestionById(@PathVariable("id") Integer id) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            FileQuestion fileQuestion = fileQuestionService.getFileQuestionById(id);
            ajaxJson.getBody().put("fileQuestion", fileQuestion);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询单选题成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询单选题失败");
        }
        return ajaxJson;
    }

    @PutMapping("")
    public AjaxJson updateSingleQuestion(FileQuestion fileQuestion) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            fileQuestionService.updateFileQuestion(fileQuestion);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("修改题目成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("修改题目失败");
        }
        return ajaxJson;
    }

    @DeleteMapping("delFileQuestions")
    public AjaxJson delSingleQuestions(@RequestParam(value = "ids[]") Integer[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        try {

            fileQuestionService.delFileQuestions(ids);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("删除题目成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("删除题目失败");
        }
        return ajaxJson;
    }

    @GetMapping("/getFileQuestionsByHid")
    public AjaxJson getFileQuestionsByHid(Integer hid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<FileQuestion> fileQuestions = fileQuestionService.getFileQuestionsByHid(hid);
            Homework homework = homeworkService.getHomeworkById(hid);

            ajaxJson.getBody().put("fileQuestions", fileQuestions);
            ajaxJson.getBody().put("homework", homework);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询单选题成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询单选题失败");
        }
        return ajaxJson;
    }


    @GetMapping("/getFileQuestionsByHid2")
    public AjaxJson getFileQuestionsByHid2(Integer hid) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            List<FileQuestion> fileQuestions = fileQuestionService.getFileQuestionsByHid2(hid);

            ajaxJson.getBody().put("fileQuestions", fileQuestions);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("查询单选题成功");
        } catch (Exception e) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("查询单选题失败");
        }
        return ajaxJson;
    }


    //downloadFileQuestions
//    @GetMapping("/downloadFileQuestions")
//    public AjaxJson downloadFileQuestions(Integer hid) {
//        AjaxJson ajaxJson = new AjaxJson();
//        try {
//            List<FileQuestion> fileQuestions = fileQuestionService.getFileQuestionsByHid2(hid);
//
//            ajaxJson.getBody().put("fileQuestions",fileQuestions);
//            ajaxJson.setSuccess(true);
//            ajaxJson.setMsg("查询单选题成功");
//        } catch (Exception e) {
//            ajaxJson.setSuccess(false);
//            ajaxJson.setMsg("查询单选题失败");
//        }
//        return ajaxJson;
//    }



    @GetMapping("/downloadFileQuestions")
    public void downloadFileQuestions(HttpServletResponse response, Integer hqid) {


        AjaxJson ajaxJson = new AjaxJson();
        try {

            fileQuestionService.downloadFileQuestions(response, hqid);
            ajaxJson.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false);
        }
    }

    @GetMapping("/downloadDetail")
    public void downloadDetail(HttpServletResponse response, Integer id) {


        AjaxJson ajaxJson = new AjaxJson();
        try {

            fileQuestionService.downloadDetail(response, id);
            ajaxJson.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false);
        }
    }

    @GetMapping("/downloadAnswer")
    public void downloadAnswer(HttpServletResponse response, Integer id) {


        AjaxJson ajaxJson = new AjaxJson();
        try {

            fileQuestionService.downloadAnswer(response, id);
            ajaxJson.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false);
        }
    }

}