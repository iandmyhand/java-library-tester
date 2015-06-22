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
		tempFile = new File(tempDirectory, filename);
		return tempFile;
	}

	File getTempDirectory() throws IOException {
		File tempDirectory = null;
		tempDirectory = File.createTempFile("TEST_DIR_", "");
		if (!tempDirectory.exists()) {
			tempDirectory.mkdir();
		}
		return tempDirectory;
	}
}
