package com.springboot.shiro.service.Impl;

import com.springboot.shiro.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author xutianhong
 * @Date 2021/3/11 3:38 下午
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Override
    public String getLog() throws Exception {

        String temp = "";
        InputStreamReader inputStreamReader = new InputStreamReader(
            new FileInputStream(new File("/Users/aive/Desktop/messages")), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder content = new StringBuilder();
        while ((temp = bufferedReader.readLine()) != null) {
            if (temp.contains("java")) {
                content.append(temp).append("\n");
            }
        }
        return content.toString();
    }
}
