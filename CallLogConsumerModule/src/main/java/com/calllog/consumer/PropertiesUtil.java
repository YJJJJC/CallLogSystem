package com.calllog.consumer;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author:yjc
 * @Date: 2019/7/2 14:11
 * @Description:
 */
public class PropertiesUtil {

    public static Properties props;
    static {
        try {
            //类加载器加载外部属性文件
            InputStream in = ClassLoader.getSystemResourceAsStream("kafka.properties");
            props = new Properties();
            props.load(in);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getProp(String key){
        return props.getProperty(key);
    }

    public static String getString(String key){
        return props.getProperty(key);
    }

    public static int getInt(String key){
        return Integer.parseInt(props.getProperty(key));
    }
}
