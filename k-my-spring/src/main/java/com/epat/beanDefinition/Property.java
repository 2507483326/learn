package com.epat.beanDefinition;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/8 20:18
 */
@Data
public class Property {

    private String key;

    private Object value;

    private String ref;

    private String type;

}
