package study.hard.javalib.file;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileTest {

	FileHandler fileHandler;
	File tempFile;

	@Before
	public void setUp() {
		fileHandler = new FileHandler();
	}

	@Test
	public void testCreateTempFile() throws IOException {
		tempFile = fileHandler.getTempFile("TEST_TEMP_FILE.txt");
		System.out.println("Absolute path of temporary file: " + tempFile.getAbsolutePath());
	}

	@After
	public void clean() {
		tempFile.delete();
	}
}
