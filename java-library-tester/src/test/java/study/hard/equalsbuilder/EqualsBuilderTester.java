package study.hard.equalsbuilder;

import static org.junit.Assert.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Test;

import study.hard.java.commons.User;

/**
 * @author SeomGi, Han(iandmyhand@gmail.com)
 */
public class EqualsBuilderTester {

	private class UserOverrideEquals extends User {
		private static final long serialVersionUID = 3964053505870438947L;

		public UserOverrideEquals(String name, Integer age) {
			super(name, age);
		}

		@Override
		public boolean equals(Object obj) {
			return EqualsBuilder.reflectionEquals(this, obj);
		}
	}

	private UserOverrideEquals user1;
	private UserOverrideEquals user2;
	private UserOverrideEquals user3;
	private UserOverrideEquals user4;

	@Before
	public void setUp() {
		user1 = new UserOverrideEquals("Tester1", 20);
		user2 = new UserOverrideEquals("Tester1", 20);
		user3 = new UserOverrideEquals("Tester2", 20);
		user4 = new UserOverrideEquals("Tester3", 25);
	}

	@Test
	public void testEquals() {
		assertTrue(user1.equals(user2));
		assertFalse(user3.equals(user4));
	}
}
