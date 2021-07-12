package com.epat.bean;

import com.epat.aspect.AspectModel;
import com.epat.beanDefinition.BeanDefinition;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李涛
 * @date : 2021/7/8 20:32
 */
@Data
public class BeanBucket {


    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private final Map<String, BeanWrapper> beanMap = new HashMap<>();

    private final Map<String, BeanWrapper> instantiationBeanMap = new HashMap<>();

    private final List<AspectModel> aspectList = new ArrayList<>();


}
