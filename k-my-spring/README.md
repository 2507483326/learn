# k-my-spring

该项目为对spring相关功能实现的学习项目，目前以实现以下功能

| 功能                          |
| ----------------------------- |
| beanFacotry工厂的创建         |
| beanDefinition定义            |
| bean循环依赖问题              |
| @aspect和@around注解实现      |
| @configuration和@bean注解实现 |

### 1. 简单使用

1. 定义一个需要被beanFactory管理的对象

```
public class User {
	
	private String name;
	
	private void say () {
		System.out.println("我的名字是" + name);
	}

}
```

2. 在resources目录下，定义一个application.json文件，并将User类注册到beanFacotry工厂中

```
{
  "bean": [
  	{
  		"id": "user",
  		"class": "com.epat.User",
  		"property": [
  			{
  				"key": "name",
  				"value": "epat"
  			}
  		]
  	}
  ]
}
```

3. 定义一个测试类，并在main方法中，初始化beanFactory，并从中获取我们需要的bean

```
public static void main(String[] args) throws Exception {
	Application application = new Application("application.json");
	User user = (User) application.getBean("user");
	user.say();
}
```

### 2. json文件的定义

#### 2.1 bean

|      |      |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |

