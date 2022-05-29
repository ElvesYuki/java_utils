package com.elvesyuki.test;

import com.alibaba.excel.EasyExcel;
import com.elvesyuki.javautils.web.WebApplication;
import com.elvesyuki.javautils.web.listener.LuoHuListener;
import com.elvesyuki.javautils.web.object.LuoHuStudentTwo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

/**
 * @author LuoHuan
 * @date 2021/7/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class shenzhenUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(shenzhenUtilsTest.class);

    @Test
    public void testLuoHuStudent() {

         // E:\excel\luohu\场次名单（26日）.xlsx

        String fileName = "E:\\excel\\luohu\\理化.xlsx";
        // String fileName = "E:\\excel\\luohu\\生物.xlsx";

        EasyExcel.read(fileName, LuoHuStudentTwo.class, new LuoHuListener()).sheet().doRead();



    }













    @Test
    public void testLocalTime() {

        LocalTime localTime = LocalTime.now();
//        Instant.now()

        logger.info(String.valueOf(localTime.getNano()));
    }




}
