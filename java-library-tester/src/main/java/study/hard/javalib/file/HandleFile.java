package study.hard.javalib.file;

import java.io.File;
import java.io.IOException;

public class HandleFile {

	File getTempFile() throws IOException {
		File tempFile = null;
		tempFile = File.createTempFile("TEST_DIR_", "");
		return tempFile;
	}
}
