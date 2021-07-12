package com.epat;

import com.epat.bean.BeanFactory;
import com.epat.annotation.config.ConfigModel;
import com.epat.annotation.config.JsonConfigLoader;
import com.epat.bean.BeanInstantiate;
import com.epat.beanDefinition.loader.BeanDefinitionLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author 李涛
 * @date : 2021/7/4 19:07
 */
public class Application {
    public static Logger logger =  LogManager.getLogger(Application.class);

    private String location;

    private BeanFactory beanFactory;

    public Application (String location) throws Exception {
        this.location = location;
        init();
    }

    public void init () throws Exception{
        beanFactory = BeanFactory.getInstance();
        JsonConfigLoader jsonConfigLoader = new JsonConfigLoader();
        ConfigModel configModel = jsonConfigLoader.loadConfig(location);
        BeanDefinitionLoader.loadBeanDefinitions(beanFactory, configModel);
        BeanInstantiate.createBean(beanFactory);
        logger.info(beanFactory);
    }

    public Object getBean (String name) throws Exception {
        return beanFactory.getBean(name);
    }

    /*public void loadBeanDefinitions() {
        File file = new File(Application.class.getClassLoader().getResource("").getPath() + File.separator + location);
        if (!file.exists()) {
            throw new RuntimeException("配置文件不存在");
        }
        Charset charset = Charset.forName("UTF-8");
        String config = "";
        try (FileInputStream fileInputStream = new FileInputStream(file);
             FileChannel fileChannel = fileInputStream.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                CharBuffer charBuffer = charset.decode(byteBuffer);
                config += charBuffer.toString();
                byteBuffer.clear();
            }
            loadBeanDefinitions(config);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            logger.error("配置文件读取出错", e);
            throw new RuntimeException("配置文件读取出错");
        }
    }*/

    /*public void loadBeanDefinitions(String config)  {
        try {
            JSONObject json = JSON.parseObject(config);
            BeanDefinitionLoader.loadBeanDefinitions(beanFactory, json);
        } catch (Exception e) {
            throw new RuntimeException("加载BeanDefinitions发生错误", e);
        }
    }

    public void createBean () {
        for (String name : beanFactory.getBeanDefinitionNames()) {
            try {
                Object bean = doCreateBean(name);
            } catch (Exception e) {
                logger.error("实例化对象失败", e);
            }
        }
    }

    public Object doCreateBean (String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        Object bean = null;
        bean = beanFactory.getBeanByBeanMap(name);
        if (bean != null) {
            return bean;
        }
        logger.info(name + ":" + beanFactory.getBeanDefinition(name));
        if (beanFactory.getBeanDefinition(name) != null && beanFactory.getBeanDefinition(name).getCreate() != null && beanFactory.getBeanDefinition(name).getCreate()) {
            bean = beanFactory.getBeanByInstantiationBeanMap(name);
            if (bean != null) {
                return bean;
            }
        }
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
        if (beanDefinition == null) {
            return null;
        }
        beanDefinition.setCreate(true);
        if (beanDefinition.getClazz() instanceof String) {
            Class clazz = Class.forName((String) beanDefinition.getClazz());
            beanDefinition.setClazz(clazz);
        }
        Class clazz = (Class) beanDefinition.getClazz();
        bean = clazz.newInstance();
        if (beanFactory.getAspectList().size() > 0) {
            AspectFactory aspectFactory = new AspectFactory(beanFactory.getAspectList());
            bean = aspectFactory.instantiate(bean);
        }

        beanFactory.addBeanToInstantiationBeanMap(name, bean);
        paddingValue(beanDefinition, bean);
        beanDefinition.setCreate(false);
        beanFactory.addBeanToBeanMap(name, bean);
        beanFactory.removeToInstantiationBeanMap(name);
        return bean;
    }

    public void paddingValue (BeanDefinition beanDefinition, Object bean) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        if (beanDefinition.getPropertyList() == null || beanDefinition.getPropertyList().size() == 0) {
            return;
        }
        for (Property property : beanDefinition.getPropertyList()) {
            Method method = findMethod(property.getKey(), (Class) beanDefinition.getClazz());
            Object value = null;
            if (property.getRef() != null) {
                value = findBean(property.getType(), property.getRef());
            } else {
                value = property.getValue();
            }
            if (method != null) {
                method.setAccessible(true);
                method.invoke(bean, value);
            } else {
                Field field = ((Class) beanDefinition.getClazz()).getDeclaredField(property.getKey());
                field.setAccessible(true);
                field.set(bean, value);
            }
        }
    }

    public Object getBean(String name) {
        return beanFactory.getBeanByBeanMap(name);
    }

    public Method findMethod (String name, Class clazz) {
        List<Method> methods = new ArrayList<>();
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if (declaredMethod.getName().startsWith("set")) {
                String baseName = declaredMethod.getName().substring(3).toLowerCase();
                if (baseName.equals(name)) {
                    methods.add(declaredMethod);
                }
            }
        }
        if (methods.size() > 0) {
            return methods.get(0);
        }
        return null;
    }

    public Object findBean (String type, String name) throws NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object bean = doCreateBean(name);
        if (bean != null) {
            return bean;
        }
        if (type == null) {
            throw new RuntimeException("找不到对应的Bean");
        }
        bean = doCreateBean(beanFactory.findBeanNameByType(type));
        return bean;
    }*/

}
