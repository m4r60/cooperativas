package com.proyecto.cooperativa.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Log4jPropertiesConf {
    private static final Logger log = LoggerFactory.getLogger(Log4jPropertiesConf.class);
    public void testLoggerLevels(){
        log.debug("Log de debug.");
        log.info("Log de info.");
        log.warn("Log de warn.");
        log.error("Log de error.");
    }
}
