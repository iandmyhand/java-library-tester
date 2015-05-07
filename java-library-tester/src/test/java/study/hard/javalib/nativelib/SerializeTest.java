package study.hard.javalib.nativelib;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import study.hard.javalib.commons.entity.NonSerializableAddress;
import study.hard.javalib.commons.entity.User;

import com.google.common.collect.Lists;

public class SerializeTest {

	private static final String FILE_NAME = "object.dat";

	private List<User> userList;
	private Date now = new Date();

	@Before
	public void setUp() {
		userList = Lists.newArrayList();
		int userCount = 3;

		// create the users.
		for (int i = 1; i <= userCount; i++) {
			Date registDay = DateUtils.addDays(now, -(i % 3));
			User user = new User("Tester" + (i % 2), 20 + (i % 3), DateUtils.addHours(registDay, -(i % 4)));
			userList.add(user);
		}

		assertEquals(userCount, userList.size());
	}

	@Test
	public void testSerializeWithoutNonSerializableObject() throws Exception {
		storeUserObjects();
		restoreUserObjects();
	}

	@Test(expected = java.io.NotSerializableException.class)
	public void testSerializeWithNonSerializableObject() throws Exception {
		for (User user : userList) {
			user.setAddress(new NonSerializableAddress(false, "123-456", "test address."));
		}
		storeUserObjects();
		restoreUserObjects();
	}

	@After
	public void finish() {
		try {
			File file = new File(FILE_NAME);
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted.");
			} else {
				System.out.println("Delete operation is failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void storeUserObjects() throws Exception {
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;

		try {
			fileOutputStream = new FileOutputStream(FILE_NAME);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);

			for (User user : userList) {
				objectOutputStream.writeObject(user);
			}

			System.out.println("Completed to store 'User' objects.");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (fileOutputStream != null)
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (objectOutputStream != null)
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	private void restoreUserObjects() throws Exception {
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;

		try {
			fileInputStream = new FileInputStream(FILE_NAME);
			objectInputStream = new ObjectInputStream(fileInputStream);

			System.out.println((User)objectInputStream.readObject());
			System.out.println((User)objectInputStream.readObject());
			System.out.println((User)objectInputStream.readObject());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (fileInputStream != null)
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (objectInputStream != null)
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
