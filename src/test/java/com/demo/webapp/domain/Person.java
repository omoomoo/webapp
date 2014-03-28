package com.demo.webapp.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Person {
	private long id;
	@NotNull(message = "名字不能为空！", groups = { PersonAdd.class })
	@Length(min = 6, max = 32, message = "名字为6-32位的字符串！", groups = { PersonAdd.class, PersonUpdate.class })
	private String name;
	private String sex;
	@Max(value = 100, groups = { PersonAdd.class })
	private int age;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
