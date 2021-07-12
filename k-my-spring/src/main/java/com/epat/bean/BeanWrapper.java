package com.epat.bean;

import lombok.Data;

/**
 * @author 李涛
 * @date : 2021/7/8 19:04
 */
@Data
public class BeanWrapper {

    private Object bean;

    private Boolean isInstantiate = false;

}
