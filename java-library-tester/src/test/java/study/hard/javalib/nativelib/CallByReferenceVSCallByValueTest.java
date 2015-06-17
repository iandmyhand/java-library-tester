package study.hard.javalib.nativelib;

import static org.junit.Assert.*;

import org.junit.Test;

import study.hard.javalib.entity.User;

public class CallByReferenceVSCallByValueTest {

	private void foo(User user) {
		System.out.println("A user in the foo: " + user.getName());
		assertEquals("James", user.getName());

		user = new User("Tomas", 31);
		System.out.println("A User in the foo: " + user.getName());
		assertEquals("Tomas", user.getName());
	}

	private void bar(User user) {
		System.out.println("A user in the bar: " + user.getName());
		user.setName("Tomas");
		System.out.println("A User in the bar: " + user.getName());
	}

	@Test
	public void test1() {
		User user = new User("James", 29);

		System.out.println("user: " + user.getName());
		foo(user);
		System.out.println("user: " + user.getName());
	}

	@Test
	public void test2() {
		User user = new User("James", 29);

		System.out.println("user: " + user.getName());
		bar(user);
		System.out.println("user: " + user.getName());
	}

}
