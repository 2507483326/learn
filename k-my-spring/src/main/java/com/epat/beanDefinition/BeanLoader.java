package com.epat.beanDefinition;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.epat.BeanFactory;
import com.epat.Property;
import com.epat.tool.StringTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李涛
 * @date : 2021/7/5 21:36
 */
public class BeanLoader implements DefinitionLoader {

    @Override
    public void loadBeanDefinitions (BeanFactory beanFactory, JSONObject json)  throws Exception {
        JSONArray jsonArray = json.getJSONArray("bean");
        if (jsonArray == null || jsonArray.size() == 0) {
            return;
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            BeanDefinition beanDefinition = new BeanDefinition();
            String id = getId(jsonObject.getString("id"), jsonObject.getString("class"));
            beanDefinition.setId(id);
            beanDefinition.setClazz(jsonObject.getString("class"));
            beanDefinition.setSingle(jsonObject.getBoolean("isSingle") != null ? jsonObject.getBoolean("isSingle") : true);
            JSONArray propertyArray = jsonObject.getJSONArray("property");
            if (propertyArray != null && propertyArray.size() > 0) {
                List<Property> properties = new ArrayList<>();
                for (int j = 0; j < propertyArray.size(); j++) {
                    JSONObject propertyJsonObject = propertyArray.getJSONObject(j);
                    Property property = new Property();
                    property.setKey(propertyJsonObject.getString("key"));
                    property.setValue(propertyJsonObject.get("value"));
                    property.setRef(propertyJsonObject.getString("ref"));
                    property.setType(propertyJsonObject.getString("type"));
                    properties.add(property);
                }
                beanDefinition.setPropertyList(properties);
            }
            beanFactory.addBeanDefinition(beanDefinition);
        }
    }

    public String getId (String id, String clazz) {
        if (StringTool.isEmpty(id)) {
            int index = clazz.lastIndexOf(".");
            String result = clazz.substring(index + 1);
            return StringTool.toLowCaseFirstChar(result);
        }
        return id;
    }

}
