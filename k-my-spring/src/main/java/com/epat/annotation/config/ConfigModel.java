package com.epat.annotation.config;

import lombok.Data;

import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/8 19:14
 */
@Data
public class ConfigModel {

    private List<BeanConfigModel> beanConfigModelList;

    private String componentScan;

}
