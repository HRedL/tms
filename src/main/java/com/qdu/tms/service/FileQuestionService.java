package com.qdu.tms.service;

import com.qdu.tms.Bean.*;
import com.qdu.tms.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileQuestionService {

    @Autowired
    FileQuestionMapper fileQuestionMapper;


    @Autowired
    HomeworkStudentMapper homeworkStudentMapper;

    @Autowired
    HomeworkMapper homeworkMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    HomeworkQuestionMapper homeworkQuestionMapper;

    @Autowired
    HomeworkQuestionStudentMapper homeworkQuestionStudentMapper;

    public List<FileQuestion> getFileQuestionsByHid(Integer hid) {

        return fileQuestionMapper.getFileQuestionsByHid(hid);
    }

    public List<FileQuestion> getFileQuestionsByHid2(Integer hid) {


         Homework homework = homeworkMapper.getHomeworkById(hid);

         List<FileQuestion> exceptFileQuestions = fileQuestionMapper.getFileQuestionsByHid(hid);

         List<FileQuestion> allFileQuestions = fileQuestionMapper.getFileQuestionsByCid(homework.getCourse().getId());

        List<FileQuestion> fileQuestions = new ArrayList<>();
         if(exceptFileQuestions.size()!=0){
             List<Integer> exceptIds = new ArrayList<>();
             for(FileQuestion fileQuestion:exceptFileQuestions){
                 exceptIds.add(fileQuestion.getId());
             }
             for(FileQuestion fileQuestion:allFileQuestions){
                 if(!exceptIds.contains(fileQuestion.getId())){
                     fileQuestions.add(fileQuestion);
                 }
             }
         }else{
             fileQuestions=allFileQuestions;
         }

         return fileQuestions;
    }

    public void uploadFileQuestion(MultipartFile file, Integer hqid, Integer sid) throws Exception {

        HomeworkQuestion homeworkQuestion = homeworkQuestionMapper.getHomeworkQuestionById(hqid);

        FileQuestion fileQuestion= fileQuestionMapper.getFileQuestionById(homeworkQuestion.getQid());

//        QuestionStudent questionStudent=questionStudentMapper.getFileQuestionByHidAndQidAndSid(fileQuestion.getHomework().getId(),
//                fid,sid);

        HomeworkQuestionStudent homeworkQuestionStudent1 = new HomeworkQuestionStudent();

        HomeworkQuestion homeworkQuestion1 = new HomeworkQuestion();
        homeworkQuestion1.setId(hqid);

        homeworkQuestionStudent1.setHomeworkQuestion(homeworkQuestion1);
        homeworkQuestionStudent1.setSid(sid);
        HomeworkQuestionStudent homeworkQuestionStudent = homeworkQuestionStudentMapper.getHomeworkQuestionStudent(homeworkQuestionStudent1);

        Student student =studentMapper.getStudentById(sid);

        String relativePath = homeworkQuestion.getPath()+"/"+sid;
        String realPath = "C:/Users/Lenovo/Desktop/upload";
//            String realPath = ConfigUtil.class.getResource("/").getPath();

        File folder = new File(realPath + relativePath);
        if (!folder.exists()) {
            boolean flag = folder.mkdirs();

        }

        String end = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        String fileName =student.getName()+student.getSno()+end;


        if(homeworkQuestionStudent==null){
            homeworkQuestionStudent =new HomeworkQuestionStudent();

            HomeworkQuestion homeworkQuestion2  = new HomeworkQuestion();
            homeworkQuestion2.setId(hqid);

            homeworkQuestionStudent.setHomeworkQuestion(homeworkQuestion2);
            homeworkQuestionStudent.setSid(sid);
            homeworkQuestionStudent.setAnswer(fileName);
            homeworkQuestionStudentMapper.addHomeworkQuestionStudent(homeworkQuestionStudent);
        }

        InputStream input = file.getInputStream();
        // 处理上传文件内容
        //目标文件

        File destFile = new File(folder, fileName);
        if (destFile.exists()) {
            destFile.delete();
        }
        appendFile(input, destFile);
    }



    private void appendFile(InputStream in, File destFile) {
        int BUFFER_SIZE = 1024;
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(destFile), BUFFER_SIZE);
            in = new BufferedInputStream(in, BUFFER_SIZE);

            int len = 0;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void addFileQuestion(FileQuestion fileQuestion) {

        fileQuestionMapper.addFileQuestion(fileQuestion);
    }

    public FileQuestion getFileQuestionById(Integer id) {

        return fileQuestionMapper.getFileQuestionById(id);
    }

    public void updateFileQuestion(FileQuestion fileQuestion) {
        fileQuestionMapper.updateFileQuestion(fileQuestion);
    }

    public void delFileQuestions(Integer[] ids) {
        fileQuestionMapper.delFileQuestions(ids);
    }

    public Boolean validateFileQuestion(FileQuestion fileQuestion) {

        return fileQuestionMapper.getFileQuestion(fileQuestion)!=null;
    }

    public List<FileQuestion> getFileQuestionsByCid(Integer cid) {
        return fileQuestionMapper.getFileQuestionsByCid(cid);
    }



    public void uploadAnswer(MultipartFile file, Integer id) throws Exception {
        FileQuestion fileQuestion = fileQuestionMapper.getFileQuestionById(id);

        String relativePath = "/"+fileQuestion.getCourse().getTeacher().getId()+"/"
                +fileQuestion.getCourse().getId()+"/answer/"+fileQuestion.getId();
        String realPath = "C:/Users/Lenovo/Desktop/upload";
//            String realPath = ConfigUtil.class.getResource("/").getPath();

        File folder = new File(realPath + relativePath);
        if (!folder.exists()) {
            boolean flag = folder.mkdirs();

        }

        String end = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        String fileName = fileQuestion.getFno()+"答案"+end;


        fileQuestion.setAnswer(fileName);
        fileQuestionMapper.updateFileQuestion(fileQuestion);

        InputStream input = file.getInputStream();
        // 处理上传文件内容
        //目标文件
        File destFile = new File(folder, fileName);
        if (destFile.exists()) {
            destFile.delete();
        }
        appendFile(input, destFile);

    }

    public void uploadDetail(MultipartFile file, Integer id) throws Exception {

        FileQuestion fileQuestion = fileQuestionMapper.getFileQuestionById(id);

        String relativePath = "/"+fileQuestion.getCourse().getTeacher().getId()+"/"
                +fileQuestion.getCourse().getId()+"/detail/"+fileQuestion.getId();
        String realPath = "C:/Users/Lenovo/Desktop/upload";
//            String realPath = ConfigUtil.class.getResource("/").getPath();

        File folder = new File(realPath + relativePath);
        if (!folder.exists()) {
            boolean flag = folder.mkdirs();

        }

        String end = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        String fileName = fileQuestion.getFno()+"附件"+end;


        fileQuestion.setDetail(fileName);
        fileQuestionMapper.updateFileQuestion(fileQuestion);

        InputStream input = file.getInputStream();
        // 处理上传文件内容
        //目标文件
        File destFile = new File(folder, fileName);
        if (destFile.exists()) {
            destFile.delete();
        }
        appendFile(input, destFile);

    }

    public void downloadFileQuestions(HttpServletResponse response, Integer hqid) {

        //names         文件名集合
        // paths         文件路径集合
        //directoryPath //临时存放--服务器上--zip文件的目录

        HomeworkQuestionStudent homeworkQuestionStudent = new HomeworkQuestionStudent();
        HomeworkQuestion homeworkQuestion = homeworkQuestionMapper.getHomeworkQuestionById(hqid);
        homeworkQuestionStudent.setHomeworkQuestion(homeworkQuestion);
        List<HomeworkQuestionStudent> homeworkQuestionStudents = homeworkQuestionStudentMapper.getHomeworkQuestionStudents(homeworkQuestionStudent);
        Homework homework = homeworkMapper.getHomeworkById(homeworkQuestion.getHid());

        List<String> names = new ArrayList<>();
        List<String> paths = new ArrayList<>();

        for(HomeworkQuestionStudent homeworkQuestionStudent1:homeworkQuestionStudents){
            paths.add("C:/Users/Lenovo/Desktop/upload"+homeworkQuestionStudent1.getHomeworkQuestion().getPath()+"/"
                    +homeworkQuestionStudent1.getSid()+"/"+homeworkQuestionStudent1.getAnswer());
            names.add(homeworkQuestionStudent1.getAnswer());
        }

        String directoryPath = "C:/Users/Lenovo/Desktop/upload/zip";

        String zipFileNameEn = "作业";

        File directoryFile = new File(directoryPath);

        if(!directoryFile.isDirectory() && !directoryFile.exists()){
            directoryFile.mkdirs();
        }

        //设置最终输出zip文件的目录+文件名
        SimpleDateFormat formatter  = new SimpleDateFormat("yyyyMMddHHmmss");
        String zipFileName = zipFileNameEn+ formatter.format(new Date())+".zip";
        String strZipPath = directoryPath+"/"+zipFileName;

        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;
        File zipFile = new File(strZipPath);
        try{
            //构造最终压缩包的输出流
            zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i = 0; i<paths.size() ;i++){
                //解码获取真实路径与文件名
                String realFileName = java.net.URLDecoder.decode(names.get(i),"UTF-8");
                String realFilePath = java.net.URLDecoder.decode(paths.get(i),"UTF-8");
                File file = new File(realFilePath);
                if(file.exists())
                {
                    zipSource = new FileInputStream(file);//将需要压缩的文件格式化为输入流
                    /**
                     * 压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样这里的name就是文件名,
                     * 文件名和之前的重复就会导致文件被覆盖
                     */
                    ZipEntry zipEntry = new ZipEntry(realFileName);//在压缩目录中文件的名字
                    zipStream.putNextEntry(zipEntry);//定位该压缩条目位置，开始写入文件到压缩包中
                    bufferStream = new BufferedInputStream(zipSource, 1024 * 10);
                    int read = 0;
                    byte[] buf = new byte[1024 * 10];
                    while((read = bufferStream.read(buf, 0, 1024 * 10)) != -1)
                    {
                        zipStream.write(buf, 0, read);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if(null != bufferStream) {
                    bufferStream.close();
                }

                if(null != zipStream){
                    zipStream.flush();
                    zipStream.close();
                }
                if(null != zipSource){
                    zipSource.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //判断当前压缩文件是否生成存在：true-把该压缩文件通过流输出给客户端后删除该压缩文件
        if(zipFile.exists()){
            //发送给客户端
            downImgClient(response,zipFileName,strZipPath);
            //删除本地存储的文件
            zipFile.delete();
        }

    }

    /**
     * 文件导出下载到----客户端
     * @param response
     * @param filename
     * @param path
     */
    public void downImgClient(HttpServletResponse response, String filename, String path ){
        if (filename != null) {
            FileInputStream inputStream = null;
            BufferedInputStream bs = null;
            ServletOutputStream servletOutputStream = null;
            try {
                response.setHeader("Content-Type","application/octet-stream");
                response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.addHeader("charset", "utf-8");
                response.addHeader("Pragma", "no-cache");
                String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
                response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);
                File file = new File(path);
                inputStream = new FileInputStream(file);
                bs =new BufferedInputStream(inputStream);
                servletOutputStream = response.getOutputStream();
                writeBytes(bs, servletOutputStream);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (servletOutputStream != null) {
                        servletOutputStream.close();
                        //servletOutputStream = null;
                    }
                    if (bs!=null){
                        bs.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                        //inputStream = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    //writeBytes()构造方法
    private void writeBytes(InputStream in, OutputStream out) throws IOException {
        byte[] buffer= new byte[1024];
        int length = -1;
        while ((length = in.read(buffer))!=-1){
            out.write(buffer,0,length);

        }

        in.close();
        out.close();
    }


//    public void downImg(HttpServletResponse response, String filename, String path ){
//
//    }


    public void downloadDetail(HttpServletResponse response, Integer id) {

        FileQuestion fileQuestion = fileQuestionMapper.getFileQuestionById(id);

        String filename = fileQuestion.getDetail();

        String realPath = "C:/Users/Lenovo/Desktop/upload";
        String relativePath = "/"+fileQuestion.getCourse().getTeacher().getId()+"/"
                +fileQuestion.getCourse().getId()+"/detail/"+fileQuestion.getId()+"/";


        String path = realPath + relativePath +filename;

//        if (filename != null) {
//            FileInputStream is = null;
//            BufferedInputStream bs = null;
//            OutputStream os = null;
//            try {
//                File file = new File(path);
//                if (file.exists()) {
//                    is = new FileInputStream(file);
//                    bs =new BufferedInputStream(is);
//                    os = response.getOutputStream();
//                    byte[] buffer = new byte[1024];
//                    int len = 0;
//                    while((len = bs.read(buffer)) != -1){
//                        os.write(buffer,0,len);
//                    }
//                }else{
//                    String error = "下载的文件资源不存在";
//                    System.out.println(error);
//                }
//            }catch(IOException ex){
//                ex.printStackTrace();
//            }finally {
//                try{
//                    if(is != null){
//                        is.close();
//                    }
//                    if( bs != null ){
//                        bs.close();
//                    }
//                    if( os != null){
//                        os.flush();
//                        os.close();
//                    }
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
            downImgClient(response,filename,path);
//        }

    }

    public void downloadAnswer(HttpServletResponse response, Integer id) {
        FileQuestion fileQuestion = fileQuestionMapper.getFileQuestionById(id);

        String filename = fileQuestion.getAnswer();

        String realPath = "C:/Users/Lenovo/Desktop/upload";
        String relativePath = "/"+fileQuestion.getCourse().getTeacher().getId()+"/"
                +fileQuestion.getCourse().getId()+"/answer/"+fileQuestion.getId()+"/";

        String path = realPath + relativePath +filename;

        downImgClient(response,filename,path);
    }
}



//    @Transactional
//    public void addQuestionStudent(Integer sid, Integer hid,List<Integer> qids, List<String> answers) {
//
//        //修改该学生的作业状态，改为“2”，即未提交
//        homeworkStudentMapper.changeHomeworkStudentType(hid,sid,"2");
//
//        List<QuestionStudent> questionStudentsData = questionStudentMapper.getSingleQuestionByHidAndSid(sid,hid);
//        List<Integer> qidsData=new ArrayList<>();
//        for(QuestionStudent questionStudent:questionStudentsData){
//            qidsData.add(questionStudent.getQid());
//        }
//
//        List<QuestionStudent> questionStudentsPage = new ArrayList<>();
//        for(int i=0;i<qids.size();i++){
//            QuestionStudent questionStudent=new QuestionStudent();
//            questionStudent.setHid(hid);
//            questionStudent.setSid(sid);
//            questionStudent.setQid(qids.get(i));
//            questionStudent.setAnswer(answers.get(i));
//            // 设置为单选题
//            questionStudent.setType("1");
//            questionStudentsPage.add(questionStudent);
//        }
////        Map<Integer,QuestionStudent> questionStudentMapData=getMapWithQid(questionStudentsData);
//
////        Map<Integer,QuestionStudent> questionStudentMapPage=getMapWithQid(questionStudentsPage);
//
//        List<QuestionStudent> questionStudentsAddList = new ArrayList<>();
//        List<QuestionStudent> questionStudentsUpdateList = new ArrayList<>();
//        for(QuestionStudent questionStudent:questionStudentsPage){
//            if(qidsData.contains(questionStudent.getQid())){
//                questionStudentsUpdateList.add(questionStudent);
//            }else{
//                questionStudentsAddList.add(questionStudent);
//            }
//        }
//
//        if(questionStudentsAddList.size()!=0){
//            questionStudentMapper.addSingleQuestionStudent(questionStudentsAddList);
//        }
//
//
//
//        if(questionStudentsUpdateList.size()!=0){
//            questionStudentMapper.updateSingleQuestionStudent(questionStudentsUpdateList);
//        }
//    }
//
//
//
//    public List<QuestionStudent> getSingleQuestionsByHidAndSid(Integer hid, Integer sid) {
//
//        return questionStudentMapper.getSingleQuestionByHidAndSid(sid,hid);
//    }
//
//
//    @Transactional
//    public void submitQuestionStudent(Integer sid, Integer hid, List<Integer> qids, List<String> answers) {
//        //修改该学生的作业状态，改为“3”，即已完成
//        homeworkStudentMapper.changeHomeworkStudentType(hid,sid,"3");
//
//        //设置已完成作业人数+1
//        homeworkMapper.increaseHomework(hid);
//
//
//        List<QuestionStudent> questionStudentsData = questionStudentMapper.getSingleQuestionByHidAndSid(sid,hid);
//        List<Integer> qidsData=new ArrayList<>();
//        for(QuestionStudent questionStudent:questionStudentsData){
//            qidsData.add(questionStudent.getQid());
//        }
//
//        //将页面传来的数据拼接称为questionStudentsPage实体
//        List<QuestionStudent> questionStudentsPage = new ArrayList<>();
//        for(int i=0;i<qids.size();i++){
//            QuestionStudent questionStudent=new QuestionStudent();
//            questionStudent.setHid(hid);
//            questionStudent.setSid(sid);
//            questionStudent.setQid(qids.get(i));
//            questionStudent.setAnswer(answers.get(i));
//            // 设置为单选题
//            questionStudent.setType("1");
//            questionStudentsPage.add(questionStudent);
//        }
//
//        //添加数据
//        List<QuestionStudent> questionStudentsAddList = new ArrayList<>();
//        //修改数据
//        List<QuestionStudent> questionStudentsUpdateList = new ArrayList<>();
//
//        //区分页面上的数据：分为添加数据和修改数据
//        for(QuestionStudent questionStudent:questionStudentsPage){
//            if(qidsData.contains(questionStudent.getQid())){
//                questionStudentsUpdateList.add(questionStudent);
//            }else{
//                questionStudentsAddList.add(questionStudent);
//            }
//        }
//
//
//        if(questionStudentsAddList.size()!=0){
//            questionStudentMapper.addSingleQuestionStudent(questionStudentsAddList);
//        }
//
//
//
//        if(questionStudentsUpdateList.size()!=0){
//            questionStudentMapper.updateSingleQuestionStudent(questionStudentsUpdateList);
//        }
//
//        setSingleQuestionGrade(sid,hid);
//
//
//    }
//
//    private void setSingleQuestionGrade(Integer sid, Integer hid) {
//
//        List<QuestionStudent> questionStudents = questionStudentMapper.getSingleQuestionByHidAndSid(sid,hid);
//
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
//
//    }
//
////    private Map<Integer,QuestionStudent> getMapWithQid(List<QuestionStudent> questionStudents){
////        Map<Integer,QuestionStudent> map=new HashMap<>();
////        for(QuestionStudent questionStudent:questionStudents){
////            map.put(questionStudent.getQid(),questionStudent);
////        }
////        return map;
////    }
////

