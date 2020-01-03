package com.sonic.IO;

import java.io.Serializable;

class Employee implements Serializable {
	private transient String name; // transient 抹去关键信息，该数据不序列化（Employee{name='null', age=18}）
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Employee() {
	}

	public Employee(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

}