package study.hard.javalib.entity;

import java.util.Date;

/**
 * A User has complex key.
 * @author iandmyhand@gmail.com
 */
public class UserHasComplexKey extends User {
	private static final long serialVersionUID = -7672577740372275065L;

	public UserHasComplexKey() {
	}

	public UserHasComplexKey(String name, int age, Date registYmdt) {
		super(name, age, registYmdt);
	}

	public String getComplexKey() {
		return this.getAge() + this.getName();
	}
}
