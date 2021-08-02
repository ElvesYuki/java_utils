package com.elvesyuki.test;

import com.elvesyuki.javautils.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/8/2 下午12:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class RabbitmqTest {

    private static final Logger logger = LoggerFactory.getLogger(RabbitmqTest.class);

    @Test
    public void test01() {
        logger.info("test01");
    }

}
