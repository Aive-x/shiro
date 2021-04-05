package com.springboot.shiro.service.Impl;

import com.springboot.shiro.common.ErrorCodeMessage;
import com.springboot.shiro.common.MarsRuntimeException;
import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import com.springboot.shiro.service.StudentEpidemicInfoService;
import com.springboot.shiro.service.SystemService;
import com.springboot.shiro.service.UserService;
import com.springboot.shiro.util.FileUtil;
import com.springboot.shiro.util.UUidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/3/11 3:38 下午
 */
@Service
@Slf4j
public class SystemServiceImpl implements SystemService {

    @Autowired
    protected StudentEpidemicInfoService studentEpidemicInfoService;
    @Autowired
    private UserService userService;

    @Override
    public String getLog() throws Exception {

        String temp = "";
        //todo 修改路径
        InputStreamReader inputStreamReader = new InputStreamReader(
            new FileInputStream(new File("/var/log/messages")), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String content = "";
        while ((temp = bufferedReader.readLine()) != null) {
            if (temp.contains("java")) {
                content = content + temp + "\n";
            }
        }
        return content;
    }

    @Override
    public List<StudentEpidemicInformation> getExamine() throws Exception {
        return studentEpidemicInfoService.getStudentEpidemicInformation(true);
    }

    @Override
    public void addUserByExcel(MultipartFile multipartFile, HttpServletRequest request) throws Exception {
        try {
            if (multipartFile == null) {
                //判断文件大小
                throw new MarsRuntimeException(ErrorCodeMessage.FILE_NOT_EXIST);
            }

            // 构造临时路径来存储上传的文件
            // 这个路径相对当前应用的目录
            // Constant.UPLOAD_DIRECTORY是你自己存放文件的文件夹
            String uploadPath = request.getServletContext().getRealPath("/")
                    + File.separator + "/";

            //如果目录不存在则创建
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String fileName = multipartFile.getOriginalFilename();
            String originalFileName = fileName
                    .substring(0, fileName.lastIndexOf("."));
            //获取文件名后缀
            String suffix = fileName
                    .substring(fileName.lastIndexOf("."));
            String newFileName = originalFileName
                    + "_" + UUidUtil.get16UUID().toString() + suffix;

            File file = new File(uploadPath, newFileName);
            try {
                multipartFile.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<String[]> fileData = new ArrayList<>();
            if (suffix.equals(".xls")) {
                fileData.addAll(FileUtil.readXlsFile(file.getAbsolutePath()));
            } else if (suffix.equals(".xlsx")) {
                fileData.addAll(FileUtil.readXlsxFile(file.getAbsolutePath()));
            } else {
                throw new MarsRuntimeException(ErrorCodeMessage.FILE_NOT_CORRECT);
            }
            userService.addUser(fileData);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户更新失败, {}", e);
        }
    }
}
