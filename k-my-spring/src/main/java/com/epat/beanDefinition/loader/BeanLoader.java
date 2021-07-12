package com.epat.beanDefinition.loader;

import com.epat.bean.BeanFactory;
import com.epat.annotation.config.BeanConfigModel;
import com.epat.annotation.config.ConfigModel;
import com.epat.beanDefinition.BeanDefinition;
import com.epat.tool.ClazzTool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李涛
 * @date : 2021/7/5 21:36
 */
public class BeanLoader implements DefinitionLoader {

    @Override
    public void loadBeanDefinitions(BeanFactory beanFactory, ConfigModel configModel) throws Exception {
        List<BeanConfigModel> beanConfigModelList = configModel.getBeanConfigModelList();
        List<BeanDefinition> beanDefinitionList = new ArrayList<>();
        if (beanConfigModelList == null) {
            return;
        }
        for (BeanConfigModel beanConfigModel : beanConfigModelList) {
            BeanDefinition beanDefinition = parseBeanDefinition(beanConfigModel);
            beanDefinitionList.add(beanDefinition);
        }
        beanFactory.addBeanDefinition(beanDefinitionList);
    }

    public BeanDefinition parseBeanDefinition (BeanConfigModel beanConfigModel) {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setId(ClazzTool.getId(beanConfigModel.getId(), (String) beanConfigModel.getClazz()));
        beanDefinition.setClazz(beanConfigModel.getClazz());
        beanDefinition.setIsSingle(beanConfigModel.getIsSingle() == null ? true : beanConfigModel.getIsSingle());
        beanDefinition.getPropertyList().addAll(beanConfigModel.getPropertyConfigModelList().stream().collect(Collectors.toList()));
        return beanDefinition;
    }
}
