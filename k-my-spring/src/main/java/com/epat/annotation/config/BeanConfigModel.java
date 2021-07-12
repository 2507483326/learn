package com.epat.annotation.config;

import lombok.Data;

import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/8 19:15
 */
@Data
public class BeanConfigModel {

    private String id;

    private String clazz;

    private Boolean isSingle;

    private List<PropertyConfigModel> propertyConfigModelList;

}
