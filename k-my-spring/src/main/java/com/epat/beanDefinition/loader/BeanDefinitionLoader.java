package com.epat.beanDefinition.loader;

import com.epat.bean.BeanFactory;
import com.epat.annotation.config.ConfigModel;

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

    public static void loadBeanDefinitions(BeanFactory beanFactory, ConfigModel configModel)  throws Exception {
        for (DefinitionLoader definitionLoader : definitionLoaderList) {
            definitionLoader.loadBeanDefinitions(beanFactory, configModel);
        }
    }

}
