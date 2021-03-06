package com.zeng.ezsh.wy.utils;
import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoggerManagerUtil {
	 // 初始化LogManager  
    static {  
        // 读取配置文件  
        ClassLoader cl = LogManager.class.getClassLoader();  
        InputStream inputStream = null;  
        if (cl != null) {  
            inputStream = cl.getResourceAsStream("log4j.properties");  
        } else {  
            inputStream = ClassLoader.getSystemResourceAsStream("log4j.properties");  
        }  
        java.util.logging.LogManager logManager = java.util.logging.LogManager.getLogManager();  
        try {  
            // 重新初始化日志属性并重新读取日志配置。  
            logManager.readConfiguration(inputStream);  
        } catch (SecurityException e) {  
            System.err.println(e);  
        } catch (IOException e) {  
            System.err.println(e);  
        }  
    }  
  
    /** 
     * 获取日志对象 
     * @param clazz 
     * @return 
     */  
    public static Logger getLogger(Class<?> clazz) {  
        Logger logger = Logger  
                .getLogger(clazz.getName());  
        return logger;  
    }   
}
