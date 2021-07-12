package com.epat.beanDefinition.loader;

import com.epat.Application;
import com.epat.bean.BeanFactory;
import com.epat.annotation.config.ConfigModel;
import com.epat.beanDefinition.loader.annotation.BeanDefinitionAnnotationLoader;
import com.epat.parse.ClassParse;
import com.epat.parse.ClassWrapper;
import com.epat.tool.ClazzTool;
import com.epat.tool.FileTool;
import com.epat.tool.StringTool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/5 21:48
 */
public class ComponentScanLoader implements DefinitionLoader{
    public static Logger logger =  LogManager.getLogger(Application.class);

    private static File basePath = new File(ComponentScanLoader.class.getClassLoader().getResource("").getPath());

    @Override
    public void loadBeanDefinitions(BeanFactory beanFactory, ConfigModel configModel)  throws Exception {
        List<ClassWrapper> classWrappers = new ArrayList<>();
        String scanPackage = configModel.getComponentScan();
        if (StringTool.isEmpty(scanPackage)) {
            return;
        }
        scanPackage = scanPackage.replace(".", File.separator);
        File file = new File(basePath.getPath() + File.separator + scanPackage);
        if (!file.exists() && !file.isDirectory()) {
            throw new Exception("包扫描路径错误");
        }
        List<File> fileList = FileTool.getChildFile(file, f -> {
            if (f.isDirectory() || f.getPath().endsWith("class")) {
                return true;
            }
            return false;
        });
        for (File clazzFile : fileList) {
            loadBeanDefinitions(classWrappers, clazzFile);
        }
        BeanDefinitionAnnotationLoader.load(beanFactory, classWrappers);
        logger.info(fileList);
    }

    public void loadBeanDefinitions(List<ClassWrapper> classWrappers, File file) throws Exception {
        String classPath = file.getPath().replace(basePath.getPath() + File.separator, "").replace(".class", "");
        String fullClassName = ClazzTool.getFullClassName(classPath);
        Class clazz = Class.forName(fullClassName);
        ClassWrapper classWrapper = ClassParse.parse(clazz);
        classWrappers.add(classWrapper);
    }


}
