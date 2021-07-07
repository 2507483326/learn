package com.epat.beanDefinition;

import com.alibaba.fastjson.JSONObject;
import com.epat.BeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/5 21:49
 */
public class BeanDefinitionLoader{

    public static List<DefinitionLoader> definitionLoaderList = new ArrayList<>();



    static {
        definitionLoaderList.add(new BeanLoader());
        definitionLoaderList.add(new ComponentScanLoader());
    }

    public static void loadBeanDefinitions(BeanFactory beanFactory, JSONObject json)  throws Exception {
        for (DefinitionLoader definitionLoader : definitionLoaderList) {
            definitionLoader.loadBeanDefinitions(beanFactory, json);
        }
    }

}
