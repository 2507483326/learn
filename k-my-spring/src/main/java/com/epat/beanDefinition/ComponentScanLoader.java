package com.epat.beanDefinition;

import com.alibaba.fastjson.JSONObject;
import com.epat.Application;
import com.epat.BeanFactory;
import com.epat.Property;
import com.epat.annotation.*;
import com.epat.tool.FileTool;
import com.epat.tool.StringTool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/5 21:48
 */
public class ComponentScanLoader implements DefinitionLoader{
    public static Logger logger =  LogManager.getLogger(Application.class);

    @Override
    public void loadBeanDefinitions(BeanFactory beanFactory, JSONObject json)  throws Exception {
        String scanPackage = json.getString("componentScan");
        if (scanPackage == null || "".equals(scanPackage)) {
            return;
        }
        scanPackage = scanPackage.replace(".", File.separator);
        File file = new File(ComponentScanLoader.class.getClassLoader().getResource("").getPath() + scanPackage);
        if (!file.exists() && !file.isDirectory()) {
            throw new RuntimeException("scan package path is not true");
        }
        List<File> fileList = FileTool.getChildFile(file, f -> {
            if (f.isDirectory() || f.getPath().endsWith("class")) {
                return true;
            }
            return false;
        });
        for (File clazzFile : fileList) {
            loadBeanDefinitions(beanFactory, clazzFile);
        }

        logger.info(fileList);
    }

    public void loadBeanDefinitions(BeanFactory beanFactory, File file) throws Exception {
        try (FileInputStream in = new FileInputStream(file)) {
            ClassReader classReader = new ClassReader(in);
            classReader.accept(new ComponentClassVisitor(beanFactory), ClassReader.SKIP_DEBUG);
        } catch (Exception e) {
            logger.error("加载类失败", e);
            throw e;
        }
    }

    private class ComponentClassVisitor extends ClassVisitor {

        private BeanFactory beanFactory;

        private BeanDefinition beanDefinition;

        private Boolean needParse;


        public ComponentClassVisitor(BeanFactory beanFactory) {
            super(Opcodes.ASM7);
            this.beanFactory = beanFactory;
            beanDefinition = new BeanDefinition();
            needParse = false;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            String className = name.replace("/", ".");
            beanDefinition.setClazz(className);
            int lastIndex = name.lastIndexOf("/");
            String id = name.substring(lastIndex + 1);
            beanDefinition.setId(StringTool.toLowCaseFirstChar(id));
        }


        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            System.out.println("descriptor" + descriptor);
            if (beanDefinition.getAnnotations() == null) {
                beanDefinition.setAnnotations(new ArrayList<>());
            }
            try {
                String clazzName = StringTool.getPackageClass(descriptor);
                Class annotationClazz = Class.forName(clazzName);
                if (annotationClazz.getName().equals(Service.class.getName())) {
                    needParse = true;
                    beanDefinition.getAnnotations().add(annotationClazz.getName());
                }
                if (annotationClazz.getName().equals(Configuration.class.getName())) {
                    needParse = true;
                    beanDefinition.getAnnotations().add(annotationClazz.getName());
                }
                if (annotationClazz.getName().equals(Component.class.getName())) {
                    needParse = true;
                    beanDefinition.getAnnotations().add(annotationClazz.getName());
                }
                if (annotationClazz.getName().equals(Aspect.class.getName())) {
                    beanFactory.getAspectList().add(beanDefinition.getClazz());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
            if (beanDefinition.getPropertyList() == null) {
                beanDefinition.setPropertyList(new ArrayList<>());
            }
            return new ComponentFieldVisitor(beanDefinition, name, descriptor);
        }


        @Override
        public void visitEnd() {
            if (needParse == true) {
                beanFactory.addBeanDefinition(beanDefinition);
            }
        }
    }

    private class ComponentFieldVisitor extends FieldVisitor {

        private String name;

        private String fieldDescriptor;

        private BeanDefinition beanDefinition;

        public ComponentFieldVisitor(BeanDefinition beanDefinition, String name, String fieldDescriptor) {
            super(Opcodes.ASM7);
            this.beanDefinition = beanDefinition;
            this.name = name;
            this.fieldDescriptor = fieldDescriptor;
        }

        @Override
        public AnnotationVisitor visitAnnotation(String annotationDescriptor, boolean visible) {
            try {
                String clazzName = StringTool.getPackageClass(annotationDescriptor);
                Class annotationClazz = Class.forName(clazzName);
                if (annotationClazz.getName().equals(Autowired.class.getName())) {
                    Property property = new Property();
                    property.setKey(name);
                    property.setRef(name);
                    property.setType(StringTool.getPackageClass(fieldDescriptor));
                    beanDefinition.getPropertyList().add(property);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        public void visitEnd() {
            super.visitEnd();
        }
    }


}
