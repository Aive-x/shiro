package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import com.springboot.shiro.service.StudentEpidemicInfoService;
import com.springboot.shiro.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/3/11 3:38 下午
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    protected StudentEpidemicInfoService studentEpidemicInfoService;

    @Override
    public String getLog() throws Exception {

        String temp = "";
        //todo 修改路径
        InputStreamReader inputStreamReader = new InputStreamReader(
            new FileInputStream(new File("/Users/aive/Desktop/messages")), StandardCharsets.UTF_8);
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
}
