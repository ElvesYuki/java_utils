package com.elvesyuki.test;

import com.elvesyuki.javautils.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
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




}
