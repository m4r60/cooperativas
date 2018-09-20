package com.proyecto.cooperativa.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Log4jTest {

    @Autowired
    Log4jPropertiesConf log4jPropertiesConf;

    @Test
    public void Log4jPropertiesConfTest(){
        log4jPropertiesConf.testLoggerLevels();
    }
}
