package practice.hard.lambdaj;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.*;

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
			User user = new User("Tester" + (i % 2), 20 + (i % 3));
			userList.add(user);
		}

		System.out.println("==================================================");
		System.out.println("User list: " + userList);
		System.out.println("==================================================");
	}

	@Test
	public void testOrderingComparison() {
		String name = "Tester1";
		List<User> selectedList = select(
			userList,
			allOf(
				having(
					on(User.class).getName(),
					equalTo(name)),
				having(
					on(User.class).getAge(),
					allOf(
						greaterThanOrEqualTo(21),
						lessThanOrEqualTo(23)
					)
				)
			)
			);

		System.out.println("==================================================");
		System.out.println("Selected user list: " + selectedList);
		System.out.println("==================================================");
	}
}
