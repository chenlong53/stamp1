package com.detection.demo.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 26387 on 2019/4/9.
 */
public class Log4jListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String logPath = System.getenv("LOG_PATH");
        String hostName = System.getenv("HOSTNAME");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(date);

        if (logPath != null && hostName != null) {
            System.setProperty("LOG_PATH", logPath);
            System.setProperty("HOSTNAME", hostName);
        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.getProperties().remove("LOG_PATH");
        System.getProperties().remove("HOSTNAME");
    }
}
