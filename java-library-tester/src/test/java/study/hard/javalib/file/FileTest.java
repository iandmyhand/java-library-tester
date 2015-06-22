package study.hard.javalib.file;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileTest {

	HandleFile handleFile;
	File tempFile;

	@Before
	public void setUp() {
		handleFile = new HandleFile();
	}

	@Test
	public void testCreateTempFile() throws IOException {
		tempFile = handleFile.getTempFile("TEST_TEMP_FILE.txt");
		System.out.println("Absolute path of temporary file: " + tempFile.getAbsolutePath());
	}

	@After
	public void clean() {
		tempFile.delete();
	}
}
