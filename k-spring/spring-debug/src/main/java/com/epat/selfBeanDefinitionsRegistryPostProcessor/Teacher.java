package com.epat.selfBeanDefinitionsRegistryPostProcessor;

/**
 * @author 李涛
 * @date : 2021/6/28 9:57
 */
public class Teacher {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher() {
		System.out.println("Teacher实例化");
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"name='" + name + '\'' +
				'}';
	}
}
