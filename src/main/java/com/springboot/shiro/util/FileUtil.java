package com.springboot.shiro.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/4/5 5:16 下午
 */
public class FileUtil {
    public static List<String[]> readXlsFile(String filePath) {
        HSSFWorkbook workbook = null;
        List<String[]> list = new LinkedList<String[]>();
        try {
            workbook = new HSSFWorkbook(new FileInputStream(filePath));
            HSSFSheet sheet = workbook.getSheetAt(0);
            int rowNumber = sheet.getLastRowNum();
            for (int i = 0; i < rowNumber + 1; i++) {
                HSSFRow row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                String[] cells = new String[lastCellNum];
                for (int j = 0; j < lastCellNum; j++) {
                    HSSFCell cell = row.getCell(j);
                    if (cell != null) {
                        cells[j] = cell.toString();
                    } else {
                        cells[j] = "";
                    }
                }
                list.add(cells);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //删除标题
        list.remove(0);
        return list;
    }

    public static List<String[]> readXlsxFile(String filePath) {
        XSSFWorkbook workbook = null;
        List<String[]> list = new LinkedList<String[]>();
        try {
            workbook = new XSSFWorkbook(new FileInputStream(filePath));
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowNumber = sheet.getLastRowNum();
            for (int i = 0; i < rowNumber + 1; i++) {
                XSSFRow row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                String[] cells = new String[lastCellNum + 1];
                for (int j = 0; j < lastCellNum; j++) {
                    XSSFCell cell = row.getCell(j);
                    cells[j] = cell.toString();
                }
                list.add(cells);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //删除标题
        //list.remove(0);
        return list;
    }
}
