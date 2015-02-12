package practice.hard.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SeomGi, Han(iandmyhand@gmail.com)
 */
public class User extends BaseObject {

	private static final long serialVersionUID = 7306465203093336101L;

	private String name;
	private Integer age;
	private List<String> messages = new ArrayList<String>() {
		private static final long serialVersionUID = -5619483093529013560L;
		{
			add("msg 1");
			add("msg 2");
			add("msg 3");
		}
	};

	public User() {

	}

	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

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

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
