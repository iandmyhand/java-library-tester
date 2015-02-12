package practice.hard.lambdaj;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import practice.hard.commons.User;

import com.google.common.collect.Lists;

/**
 * @author SeomGi, Han(iandmyhand@gmail.com)
 */
public class LambdajTester {

	private List<User> userList;

	@Before
	public void setUp() {
		userList = Lists.newArrayList();

		// Add the 10 users.
		for (int i = 1; i <= 10; i++) {
			User user = new User("Tester" + i, 20 + (i % 3));
			userList.add(user);
		}

		System.out.println("==================================================");
		System.out.println("User list: " + userList);
		System.out.println("==================================================");
	}

	@Test
	public void test() {
		//test
	}
}
