package com.lxz.myBlog.controller;


import com.google.gson.Gson;
import com.lxz.myBlog.Mapper.userMapper;
import com.lxz.myBlog.Service.userService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2017/7/20.
 */
@RestController
public class MainController {
    @Autowired
    private userService userService;

    @RequestMapping("/hello")
    public String index(){
        Gson gs = new Gson();
        try {
            File xlsFile = new File("C://测试蓝牙导入.xls");
            WritableWorkbook writeBook = Workbook.createWorkbook(xlsFile);
            WritableSheet firstSheet = writeBook.createSheet("测试蓝牙导入", 1);

            firstSheet.addCell(new Label(0, 0,  "蓝牙名（即镭标）"));
            firstSheet.addCell(new Label(1, 0,  "蓝牙Mac地址"));
            firstSheet.addCell(new Label(2, 0,  "蓝牙合法性 1为合法 2为非法"));
            firstSheet.addCell(new Label(3, 0,  "蓝牙绑定设备类型 "));
            firstSheet.addCell(new Label(4, 0,  "蓝牙质保时间"));

            String strH,strL;
            int hNum,lNum;
            int nameStarNumLen = 100; //长度
            int nameStarNum = 1; //名字起始
            int nowNum = 1;  //mac起始地址
            String type = "动感单车";


            for (int i=0;i<nameStarNumLen;i++) {
                firstSheet.addCell(new Label(0, i+1,  "QiDong"+String.format("%06d", nameStarNum+i)));    //蓝牙名

                hNum = (nowNum+i)/256;
                lNum = (nowNum+i)%256;

                if (hNum < 16) {
                    strH = "0"+Integer.toHexString(hNum).toUpperCase();
                }
                else {
                    strH = Integer.toHexString(hNum);
                }

                if (lNum < 16) {
                    strL = "0"+Integer.toHexString(lNum).toUpperCase();
                }
                else {
                    strL = Integer.toHexString(lNum).toUpperCase();
                }
                firstSheet.addCell(new Label(1, i+1,  "00:DB:00:00:"+strH+":"+strL));
                firstSheet.addCell(new Label(2, i+1,  "1"));
                firstSheet.addCell(new Label(3, i+1,  type));
                firstSheet.addCell(new Label(4, i+1,  "3"));
            }
            // 4、打开流，开始写文件
            writeBook.write();
            writeBook.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return gs.toJson(e.getMessage());
        }


        return gs.toJson("123");
    }

}
