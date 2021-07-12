package com.epat.annotation.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.epat.tool.FileTool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/8 19:08
 */
public class JsonConfigLoader implements ConfigLoader{

    private String basePath = JsonConfigLoader.class.getClassLoader().getResource("").getPath() + File.separator;

    private String path = "application.json";

    public ConfigModel loadConfig () throws Exception {
        File file = new File(basePath + path);
        return loadConfig(file);
    }

    public ConfigModel loadConfig (String path) throws Exception {
        File file = new File(basePath + path);
        return loadConfig(file);
    }

    @Override
    public ConfigModel loadConfig(File file) throws Exception {
        String config = FileTool.readFile(file);
        JSONObject json = JSON.parseObject(config);
        ConfigModel configModel = new ConfigModel();
        configModel.setBeanConfigModelList(parseBeanConfigModel(json));
        configModel.setComponentScan(parseComponentScan(json));
        return configModel;
    }

    public List<BeanConfigModel> parseBeanConfigModel (JSONObject jsonObject) {
        List<BeanConfigModel> beanConfigModelList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("bean");
        if (jsonArray == null || jsonArray.size() == 0) {
            return null;
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject beanJsonObject = jsonArray.getJSONObject(i);
            BeanConfigModel beanConfigModel = new BeanConfigModel();
            beanConfigModel.setId(beanJsonObject.getString("id"));
            beanConfigModel.setClazz(beanJsonObject.getString("class"));
            beanConfigModel.setIsSingle(beanJsonObject.getBoolean("isSingle"));
            beanConfigModel.setPropertyConfigModelList(parsePropertyConfigModel(beanJsonObject));
            beanConfigModelList.add(beanConfigModel);
        }
        return beanConfigModelList;
    }

    private List<PropertyConfigModel> parsePropertyConfigModel (JSONObject jsonObject) {
        JSONArray propertyArray = jsonObject.getJSONArray("property");
        List<PropertyConfigModel> properties = new ArrayList<>();
        if (propertyArray == null) {
            return null;
        }
        for (int j = 0; j < propertyArray.size(); j++) {
            JSONObject propertyJsonObject = propertyArray.getJSONObject(j);
            PropertyConfigModel property = new PropertyConfigModel();
            property.setKey(propertyJsonObject.getString("key"));
            property.setValue(propertyJsonObject.get("value"));
            property.setRef(propertyJsonObject.getString("ref"));
            property.setType(propertyJsonObject.getString("type"));
            properties.add(property);
        }
        return properties;
    }

    private String parseComponentScan (JSONObject jsonObject) {
        return jsonObject.getString("componentScan");
    }

}
