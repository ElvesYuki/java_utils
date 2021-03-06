package com.elvesyuki.test;

import com.alibaba.excel.EasyExcel;
import com.elvesyuki.javautils.web.WebApplication;
import com.elvesyuki.javautils.web.object.ExamData;
import com.elvesyuki.javautils.web.listener.ExamExcelListener;
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
public class otherUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(otherUtilsTest.class);

    @Test
    public void test01() {
        logger.info("test01");
    }

    @Test
    public void testLocalTime() {

        LocalTime localTime = LocalTime.now();
//        Instant.now()

        logger.info(String.valueOf(localTime.getNano()));
    }


    @Test
    public void testpp() {
        String fileName = "D:\\坪山区_PS_坪山外国语学校文源校区_008 (1).xlsx";
        EasyExcel.read(fileName, ExamData.class, new ExamExcelListener()).sheet().doRead();
    }


}
