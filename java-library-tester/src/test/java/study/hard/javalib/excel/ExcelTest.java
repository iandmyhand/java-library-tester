package study.hard.javalib.excel;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelTest {

	private static final Logger logger = LoggerFactory.getLogger(ExcelTest.class);

	private HandleExcel handleExcel;
	private File tempFile;

	@Before
	public void setUp() throws IOException {
		logger.debug("##### Set up to handle excel.");
		handleExcel = new HandleExcel();
		tempFile = handleExcel.createTemporaryExcelFile();
		logger.debug("Absolute path of test file: {}", tempFile.getAbsolutePath());
	}

	@Test
	public void testExcelWriteAndRead() {
		logger.debug("\n##### Write excel test.");
		handleExcel.writeExcel(tempFile);
		logger.debug("\n##### Read excel test.");
		handleExcel.readExcel(tempFile);
		logger.debug("\n##### Update excel test.");
		handleExcel.updateExcel(tempFile);
		logger.debug("\n##### Read excel test.");
		handleExcel.readExcel(tempFile);
	}

	@After
	public void clean() {
		tempFile.delete();
	}

}
