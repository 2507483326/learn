package com.epat.beanDefinition;

import com.epat.Property;

import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/5 12:07
 */
public class BeanDefinition {

    private String id;

    private Object clazz;

    private Boolean isSingle;

    private List<Property> propertyList;

    private Boolean isCreate = false;

    private List<String> annotations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getClazz() {
        return clazz;
    }

    public void setClazz(Object clazz) {
        this.clazz = clazz;
    }

    public Boolean getSingle() {
        return isSingle;
    }

    public void setSingle(Boolean single) {
        isSingle = single;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public Boolean getCreate() {
        return isCreate;
    }

    public void setCreate(Boolean create) {
        isCreate = create;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "id='" + id + '\'' +
                ", clazz=" + clazz +
                ", isSingle=" + isSingle +
                ", propertyList=" + propertyList +
                ", isCreate=" + isCreate +
                ", annotations=" + annotations +
                '}';
    }
}
