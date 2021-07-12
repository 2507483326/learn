package com.epat.beanDefinition.loader;

import com.epat.bean.BeanFactory;
import com.epat.annotation.config.ConfigModel;

/**
 * @author 李涛
 * @date : 2021/7/5 21:43
 */
public interface DefinitionLoader {

    public void loadBeanDefinitions (BeanFactory beanFactory, ConfigModel configModel)  throws Exception;

}
