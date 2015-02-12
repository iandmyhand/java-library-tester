package practice.hard.jackson;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import practice.hard.commons.User;

public class JacksonTester {

	private static final String PATHNAME = "user.json";
	private static final String USER_NAME = "tester";
	private static final Integer USER_AGE = 20;

	private File file;
	private User user;

	@Before
	public void setUp() {
		file = new File(PATHNAME);
		System.out.println("Absolute path: " + file.getAbsoluteFile() + "\n");

		user = new User(USER_NAME, USER_AGE);
	}

	@Test
	public void test() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			//1. Convert Java object to JSON format
			mapper.writeValue(file, user);

			//2. Convert JSON to Java object
			User user2 = mapper.readValue(file, User.class);
			System.out.println("user2: " + user2 + "\n");

			JsonNode jsonNode = mapper.readValue(file, JsonNode.class);
			System.out.println("jsonNode: " + jsonNode + "\n");

			User user3 = mapper.readValue(jsonNode, User.class);
			System.out.println("user3: " + user3 + "\n");

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@After
	public void rollBack() {
		file.delete();
	}

}
