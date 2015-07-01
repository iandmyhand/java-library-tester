package study.hard.javalib.file;

import java.io.File;
import java.io.IOException;

public class HandleFile {

	File getTempFile() throws IOException {
		return getTempFile("TEMP_FILE");
	}

	File getTempFile(String filename) throws IOException {
		File tempFile = null;
		File tempDirectory = getTempDirectory();
		System.out.println(tempDirectory.isDirectory());
		tempFile = new File(tempDirectory, filename);
		return tempFile;
	}

	File getTempDirectory() throws IOException {
		File tempDirectory = null;
		tempDirectory = File.createTempFile("TEST_DIR_", "");
		tempDirectory.delete();
		if (!tempDirectory.exists()) {
			boolean result = tempDirectory.mkdir();
			System.out.println("temp directory created: " + result);
		}
		return tempDirectory;
	}
}
