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

	private ExcelHandler excelHandler;
	private File tempFile;

	@Before
	public void setUp() throws IOException {
		logger.debug("##### Set up to handle excel.");
		excelHandler = new ExcelHandler();
		tempFile = excelHandler.createTemporaryExcelFile();
		logger.debug("Absolute path of test file: {}", tempFile.getAbsolutePath());
	}

	@Test
	public void testExcelWriteAndRead() {
		logger.debug("\n##### Write excel test.");
		excelHandler.writeExcel(tempFile);
		logger.debug("\n##### Read excel test.");
		excelHandler.readExcel(tempFile);
		logger.debug("\n##### Update excel test.");
		excelHandler.updateExcel(tempFile);
		logger.debug("\n##### Read excel test.");
		excelHandler.readExcel(tempFile);
	}

	@After
	public void clean() {
		tempFile.delete();
	}

}
