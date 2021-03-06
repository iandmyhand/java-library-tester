package study.hard.javalib.lambdaj;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import study.hard.javalib.commons.enumeration.SortDirection;
import study.hard.javalib.entity.User;
import study.hard.javalib.entity.UserHasComplexKey;
import ch.lambdaj.collection.LambdaCollections;
import ch.lambdaj.collection.LambdaGroup;
import ch.lambdaj.function.compare.ArgumentComparator;
import ch.lambdaj.group.Group;

import com.google.common.collect.Lists;

/**
 * @author SeomGi, Han(iandmyhand@gmail.com)
 */
public class LambdajTest {

	private List<User> userList;
	private Date now = new Date();

	@Before
	public void setUp() {
		userList = Lists.newArrayList();
		int userCount = 10;

		// Add the 10 users.
		for (int i = 1; i <= userCount; i++) {
			Date registDay = DateUtils.addDays(now, -(i % 3));
			User user = new User("Tester" + (i % 2), 20 + (i % 3), DateUtils.addHours(registDay, -(i % 4)));
			userList.add(user);
		}

		println("Sample list:", userList);

		assertEquals(userCount, userList.size());
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

		assertEquals(5, selectedList.size());
		assertEquals(10, userList.size());
	}

	@Test
	public void testSelectWithTwoCondition() {
		String name = "Tester1";
		Integer age = 21;
		List<User> selectedList = select
			(
				userList,
				allOf(
					having(
						on(User.class).getName(),
						equalTo(name)
					),
					having(
						on(User.class).getAge(),
						equalTo(age)
					)
				)
			);

		println("test to select with two condition:", selectedList);

		assertEquals(2, selectedList.size());
	}

	@Test
	public void testSelectWithTwoCondition2() {
		Date condition = userList.get(0).getRegistYmdt();
		System.out.println(condition);
		List<User> selectedList = select
			(
				userList,
				having(
					on(User.class).getRegistYmdt(),
					allOf(
						greaterThanOrEqualTo(DateUtils.addHours(condition, -2)),
						lessThanOrEqualTo(condition)
					)
				)
			);

		println("test to select with two condition:", selectedList);

		assertEquals(3, selectedList.size());
	}

	@Test
	public void testSelectWithNull() {
		userList.get(0).setName(null);
		List<User> selectedList = select
			(
				userList,
				having(
					on(User.class).getName(),
					nullValue()
				)
			);

		println("test to select with one condition:", selectedList);

		assertEquals(1, selectedList.size());
	}

	@Test
	public void testSelectFirst() {
		User selectedUser = selectFirst(userList, having(on(User.class).getName(), equalTo("Tester1")));
		println("select first", selectedUser);
		assertNotNull(selectedUser);
	}
	
	@Test
	public void testSelectMax() {
		User selectedUser = selectMax(userList, on(User.class).getAge());
		println("select first", selectedUser);
		assertNotNull(selectedUser);
	}

	@Test(expected = NullPointerException.class)
	public void testSelectFirstWhenIfNotExist() {
		User selectedUser = selectFirst(userList, having(on(User.class).getName(), equalTo("Tester")));
		println("select first", selectedUser.getAge());
		assertNull(selectedUser);
	}

	@Test
	public void testIndex() {
		Map<String, User> usersMap = index(userList, on(User.class).getName());
		println("usersMap", usersMap);
	}

	@Test
	public void testSorting() {
		Comparator<User> orderByAge = new ArgumentComparator<User, Integer>(on(User.class).getAge());
		List<User> sortedList = sort(userList, on(User.class), orderByAge);
		println("Test sorting:", sortedList);
	}

	/*@Test
	public void testSortingByDescending() {
	    // TODO: could not found lambdaj 2.4 from online repositories. but below function supported 2.4...
		List<User> sortedList = sort(userList, on(User.class).getAge(), SortDirection.DESCENDING.getOrdering());
		println("Test sorting:", sortedList);
	}*/

	@Test
	public void testSortingByMultipleColumn() {
		Comparator<User> byAge = new ArgumentComparator<User, Integer>(on(User.class).getAge());
		Comparator<User> byRegistYmdt = new ArgumentComparator<User, Date>(on(User.class).getRegistYmdt());
		@SuppressWarnings("unchecked") Comparator<User> orderBy = ComparatorUtils.chainedComparator(byAge, byRegistYmdt);

		List<User> sortedList = sort(userList, on(User.class), orderBy);
		println("Test sorting:", sortedList);
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

		assertEquals(3, selectedList.size());
	}

	@Test
	public void testOrderingComparisonWithDate() {
		String name = "Tester1";
		println("greaterThanOrEqualTo: ", DateUtils.addHours(now, -1));
		println("lessThanOrEqualTo: ", DateUtils.addHours(now, 1));
		List<User> selectedList = select
			(
				userList,
				allOf(
					having(
						on(User.class).getName(),
						equalTo(name)),
					having(
						on(User.class).getRegistYmdt(),
						org.hamcrest.core.AllOf.allOf(
							greaterThanOrEqualTo(DateUtils.addHours(now, -1)),
							lessThanOrEqualTo(DateUtils.addHours(now, 1))
							)
					)
				)
			);

		println("Test ordering comparison:", selectedList);

		assertEquals(2, selectedList.size());
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
		assertEquals(3, groupAgeKeys.size());

		for (String ageKey : groupAgeKeys) {
			println("age key:", ageKey);
			println("size of group by age key[" + ageKey + "]:", groupAgeOfUser.find(ageKey).size());
			for (User user : groupAgeOfUser.find(ageKey)) {
				println("user:", user);
			}
		}
	}

	@Test
	public void testGroupingUseObjectAsKey() {
		Group<User> groupMessageOfUser = group
			(
				userList,
				by(on(User.class).getMessages())
			);
		Set<String> groupMessageKeys = groupMessageOfUser.keySet();
		println("group keys:", groupMessageKeys);
		assertEquals(1, groupMessageKeys.size());

		for (String messageKey : groupMessageKeys) {
			println("message key:", messageKey);
			println("size of group by message key[" + messageKey + "]:", groupMessageOfUser.find(messageKey).size());
			for (User user : groupMessageOfUser.find(messageKey)) {
				println("user:", user);
			}
		}
	}

	@Test
	public void testGroupingUseComplexKey() {
		List<UserHasComplexKey> userHasComplexKeyList = createUserHasComplexKeyList();
		Group<UserHasComplexKey> groupOfUser = group
			(
				userHasComplexKeyList,
				by(on(UserHasComplexKey.class).getComplexKey())
			);

		Set<String> groupOfUserKeys = groupOfUser.keySet();
		for (String userComplexKey : groupOfUserKeys) {
			println("userComplex key:", userComplexKey);
			println("size of group by user Complex key[" + userComplexKey + "]:", groupOfUser.find(userComplexKey).size());
			for (UserHasComplexKey user : groupOfUser.find(userComplexKey)) {
				println("user:", user);
			}
		}
	}

	private List<UserHasComplexKey> createUserHasComplexKeyList() {
		List<UserHasComplexKey> userHasComplexKeyList = Lists.newArrayList();

		// Add the 10 users.
		for (int i = 1; i <= 10; i++) {
			UserHasComplexKey user = new UserHasComplexKey("Tester" + (i % 2), 20 + (i % 3), DateUtils.addDays(now, -(i % 3)));
			userHasComplexKeyList.add(user);
		}
		println("Sample list:", userHasComplexKeyList);
		assertEquals(10, userHasComplexKeyList.size());

		return userHasComplexKeyList;
	}

	@Test
	public void testGroupingUsingLambdaCollections() {
		Integer sum = 0;
		List<String> names = Lists.newArrayList("Tester0", "Tester1");
		LambdaGroup<User> ageOfUserGroup = LambdaCollections.with(userList)
			.clone()
			.group(by(on(User.class).getAge()));

		Set<String> ageKeys = ageOfUserGroup.keySet();
		for (String ageKey : ageKeys) {
			sum = LambdaCollections.with(ageOfUserGroup.find(ageKey))
				.clone()
				.retain(having(on(User.class).getName(), isIn(names)))
				.sum(on(User.class).getAge());
			println("sum of age " + ageKey + ": ", sum);
		}
	}

	@Test
	public void testSelectDistinct() {
		List<User> distinctedUserList = new ArrayList<User>(new HashSet<User>(userList));
		println("distinctedUserList: ", distinctedUserList);

		assertEquals(10, distinctedUserList.size());
	}

	@Test
	public void testIsIn() {
		List<User> selectedUserList = null;
		selectedUserList =
			select(
				userList,
				having(
					on(User.class).getAge(),
					isIn(Arrays.asList(18, 19, 20))
				)
			);
		println("selectedUserList", selectedUserList);
		assertEquals(3, selectedUserList.size());

		List<User> tmpUserList = Lists.newArrayList();
		tmpUserList.add(selectedUserList.get(0));
		tmpUserList.add(selectedUserList.get(1));
		println("tmpUserList", tmpUserList);

		selectedUserList = null;
		selectedUserList =
			select(
				userList,
				having(
					on(User.class),
					isIn(tmpUserList)
				)
			);
		println("selectedUserList", selectedUserList);
		assertEquals(2, selectedUserList.size());
	}

	@Test
	public void testExtract() {
		List<String> nameList = extract(userList, on(User.class).getName());
		println("nameList", nameList);
	}

	@Test
	public void testSumWithLambdaCollections() {
		Integer sum = 0;

		println("size: ", userList.size());
		String name = "Tester1";

		sum = LambdaCollections.with(userList)
			.clone()
			.retain(having(on(User.class).getName(), equalTo(name)))
			.sum(on(User.class).getAge());

		println("sum of ages: ", sum);
		assertEquals(new Integer(104), sum);
		assertEquals(10, userList.size());

		println("size: ", userList.size());
		List<String> names = Lists.newArrayList("Tester0", "Tester1");
		sum = LambdaCollections.with(userList)
			.clone()
			.retain(having(on(User.class).getName(), isIn(names)))
			.sum(on(User.class).getAge());

		println("sum of ages: ", sum);
		assertEquals(new Integer(210), sum);
		assertEquals(10, userList.size());
	}

	@Test
	public void testSumWithLambdaCollectionsWithoutClone() {
		Integer sum = 0;

		println("size: ", userList.size());
		String name = "Tester1";

		sum = LambdaCollections.with(userList)
			.retain(having(on(User.class).getName(), equalTo(name)))
			.sum(on(User.class).getAge());

		println("sum of ages: ", sum);
		assertEquals(new Integer(104), sum);
		assertEquals(5, userList.size());

		println("size: ", userList.size());
		List<String> names = Lists.newArrayList("Tester0", "Tester1");

		sum = LambdaCollections.with(userList)
			.retain(having(on(User.class).getName(), isIn(names)))
			.sum(on(User.class).getAge());

		println("sum of ages: ", sum);
		assertEquals(new Integer(210), sum);
		assertEquals(5, userList.size()); // LambdaCollections uses 'call by reference', if do not use clone() method.
	}

	private void println(String title, Object object) {
		System.out.println("==================================================");
		System.out.println(title);
		System.out.println("--------------------------------------------------");
		System.out.println("result: " + object);
		System.out.println("==================================================");
	}
}
