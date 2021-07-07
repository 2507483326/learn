package com.epat.beanDefinition;

import com.alibaba.fastjson.JSONObject;
import com.epat.BeanFactory;

/**
 * @author 李涛
 * @date : 2021/7/5 21:43
 */
public interface DefinitionLoader {

    public void loadBeanDefinitions (BeanFactory beanFactory, JSONObject json)  throws Exception;

}
