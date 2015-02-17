package study.hard.lambdaj;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import study.hard.commons.User;
import ch.lambdaj.group.Group;

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

		println("Sample list:", userList);

		assertTrue(10 == userList.size());
	}

	@Test
	public void testSelectWithOneCondition() {
		String name = "Tester1";
		List<User> selectedList = select
			(
				userList,
				having(
					on(User.class).getName(),
					equalTo(name)
				)
			);

		println("test to select with one condition:", selectedList);

		assertTrue(5 == selectedList.size());
	}

	@Test
	public void testOrderingComparison() {
		String name = "Tester1";
		List<User> selectedList = select
			(
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

		println("Test ordering comparison:", selectedList);

		assertTrue(3 == selectedList.size());
	}

	@Test
	public void testGrouping() {
		Group<User> groupAgeOfUser = group
			(
				userList,
				by(on(User.class).getAge())
			);
		Set<String> groupAgeKeys = groupAgeOfUser.keySet();
		println("group keys:", groupAgeKeys);
		assertTrue(3 == groupAgeKeys.size());

		for (String ageKey : groupAgeKeys) {
			println("age key:", ageKey);
			println("size of group by age key[" + ageKey + "]:", groupAgeOfUser.find(ageKey).size());
			for (User user : groupAgeOfUser.find(ageKey)) {
				println("user:", user);
			}
		}
	}

	@Test
	public void testSelectDistinct() {
		List<User> distinctedUserList = new ArrayList<User>(new HashSet<User>(userList));
		println("distinctedUserList: ", distinctedUserList);

		assertTrue(6 == distinctedUserList.size());
	}

	private void println(String title, Object object) {
		System.out.println("==================================================");
		System.out.println(title);
		System.out.println("--------------------------------------------------");
		System.out.println("result: " + object);
		System.out.println("==================================================");
	}
}
