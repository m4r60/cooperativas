package com.proyecto.cooperativa.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Log4jPropertiesConf {
    private static Logger logger = LoggerFactory.getLogger(Log4jPropertiesConf.class);
    public void testLoggerLevels(){
        logger.debug("Log de debug.");
        logger.info("Log de info.");
        logger.warn("Log de warn.");
        logger.error("Log de error.");
    }
}
