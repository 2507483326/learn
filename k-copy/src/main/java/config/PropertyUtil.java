package config;

import handler.ChannelFileHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * @author 李涛
 * @date : 2021/6/16 16:51
 */
public class PropertyUtil {
    public static Logger logger = LogManager.getLogger(PropertyUtil.class);

    public static Properties getConfig(String name){
        Properties props=null;
        try{
            props = new Properties();
            InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream(name);
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            props.load(bf);
            in.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return props;
    }

    public static String getPropValue(Properties prop,String key){
        if(key == null || "".equals(key.trim())){
            return null;
        }
        String value = prop.getProperty(key);
        if(value == null){
            return null;
        }
        value = value.trim();
        //判断是否是环境变量配置属性,例如 server.env=${serverEnv:local}
        if(value.startsWith("${") && value.endsWith("}") && value.contains(":")){
            int indexOfColon = value.indexOf(":");
            String envName = value.substring(2,indexOfColon);
            //获取系统环境变量 envName 的内容，如果没有找到，则返回defaultValue
            String envValue = System.getenv(envName);
            if(envValue == null){
                //配置的默认值
                return value.substring(indexOfColon+1,value.length()-1);
            }
            return envValue;
        }
        return value;
    }

    public static Object getPropByClass (Properties prop, Class clazz)  {
        try {
            Object o = clazz.newInstance();
            for (Field declaredField : clazz.getDeclaredFields()) {
                String value = getPropValue(prop, declaredField.getName());
                PropertyDescriptor pd = new PropertyDescriptor(declaredField.getName(), clazz);
                if (value != null) {
                    pd.getWriteMethod().invoke(o, value);
                }
            }
            return o;
        } catch (Exception e) {
            logger.error("实例化对象失败", e);
            return null;
        }
    }

}
