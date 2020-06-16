package com.ljj.demo;

import org.apache.logging.slf4j.SLF4JLogger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)//加上这个就可以开启spring上下文，有依赖注入功能了
class DemoApplicationTests {
    //logger：日志记录器
     Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    void contextLoads() {

        //日志级别由低到高：trace<debug<info<warn<error
        //默认info以上生效，要调级别去配置文件
        logger.trace("这是trace信息...");
        logger.debug("这是debug信息...");
        logger.info("这是info信息...");
        logger.warn("这是warn信息...");
        logger.error("这是error信息...");

    }

}
