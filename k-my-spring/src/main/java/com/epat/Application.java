package com.epat;

import com.epat.bean.BeanFactory;
import com.epat.annotation.config.ConfigModel;
import com.epat.annotation.config.JsonConfigLoader;
import com.epat.bean.BeanInstantiate;
import com.epat.beanDefinition.loader.BeanDefinitionLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author 李涛
 * @date : 2021/7/4 19:07
 */
public class Application {
    public static Logger logger =  LogManager.getLogger(Application.class);

    private String location;

    private BeanFactory beanFactory;

    public Application (String location) throws Exception {
        this.location = location;
        init();
    }

    /**
     * 完成beanFactory工厂的初始化工作
     * @throws Exception
     */
    public void init () throws Exception{
        beanFactory = BeanFactory.getInstance();
        JsonConfigLoader jsonConfigLoader = new JsonConfigLoader();
        ConfigModel configModel = jsonConfigLoader.loadConfig(location);
        BeanDefinitionLoader.loadBeanDefinitions(beanFactory, configModel);
        BeanInstantiate.createBean(beanFactory);
        logger.info(beanFactory);
    }

    /**
     * 获取bean根据名称
     * @param name
     * @return
     * @throws Exception
     */
    public Object getBean (String name) throws Exception {
        return beanFactory.getBean(name);
    }


    /**
     * 获取bean根据类型
     * @param type
     * @return
     * @throws Exception
     */
    public Object getBean (Class type) throws Exception {
        return beanFactory.getBean(type, null);
    }
}
