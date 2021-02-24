package com.detection.demo.util;


import org.apache.log4j.*;

import java.io.File;

/**
 * Created by 26387 on 2019/4/10.
 */
public class LogUtil {
    public static Logger setFileName(Class className, String path, String fileName){
        Logger logger = Logger.getLogger(className);
        logger.removeAllAppenders();
        logger.setLevel(Level.INFO);
        logger.setAdditivity(true);
        FileAppender appender = new RollingFileAppender();
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "[%d] %p %t %c -%m%n";
        layout.setConversionPattern(conversionPattern);
        appender.setLayout(layout);
        appender.setFile(path+ File.separatorChar+fileName);
        appender.setEncoding("UTF-8");
        appender.setAppend(true);
        appender.activateOptions();
        logger.addAppender(appender);
        return logger;
    }
}
