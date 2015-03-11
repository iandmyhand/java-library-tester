package study.hard.javalib.jackson;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import study.hard.javalib.commons.User;

/**
 * @author SeomGi, Han(iandmyhand@gmail.com)
 */
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
	public void testBasicUsageOfJackson() {
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

	@Test
	public void testDeserialiseArrayOfObjects() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(file, user);
			JsonNode jsonNode = mapper.readValue(file, JsonNode.class);

			// 1. As Array:
			String[] messagesAsStringArray = mapper.readValue(jsonNode.get("messages"), String[].class);
			for (String message : messagesAsStringArray) {
				System.out.println("message: " + message);
			}
			System.out.println();

			// 2. As List:
			List<String> messagesAsStringList = mapper.readValue(jsonNode.get("messages"), new TypeReference<List<String>>() {
			});
			System.out.println("messagesAsStringList: " + messagesAsStringList + "\n");

			// 3. Another way to specify the List type:
			List<String> messagesAsStringList2 = mapper.readValue(jsonNode.get("messages"), mapper.getTypeFactory().constructCollectionType(List.class, String.class));
			System.out.println("messagesAsStringList2: " + messagesAsStringList2 + "\n");
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
