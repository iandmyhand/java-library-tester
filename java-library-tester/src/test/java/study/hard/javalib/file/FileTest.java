package study.hard.javalib.file;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileTest {

	HandleFile handleFile;
	File tempDirectoryFile;
	File tempFile;

	@Before
	public void setUp() {
		handleFile = new HandleFile();
	}

	@Test
	public void testCreateTempFile() throws IOException {
		tempDirectoryFile = handleFile.getTempFile();
		System.out.println("Absolute path of temporary directory: " + tempDirectoryFile.getAbsolutePath());
		if (!tempDirectoryFile.exists()) {
			tempDirectoryFile.mkdirs();
		}
		tempFile = new File(tempDirectoryFile, "TEST_TEMP_FILE.txt");
		System.out.println("Absolute path of temporary file: " + tempFile.getAbsolutePath());
	}

	@After
	public void clean() {
		tempFile.delete();
		tempDirectoryFile.delete();
	}
}
