package study.hard.javalib.excel;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class WriteExcelTest {

	private HandleExcel handleExcel;
	private File tempFile;

	@Before
	public void setUp() {
		System.out.println("##### Set up to handle excel.");
		handleExcel = new HandleExcel();
		tempFile = new File("test_file.xlsx");
		System.out.println("Absolute path of test file: " + tempFile.getAbsolutePath());
	}

	@Test
	public void testExcelWriteAndRead() {
		System.out.println("\n##### Write excel test.");
		handleExcel.writeExcel(tempFile);
		System.out.println("\n##### Read excel test.");
		handleExcel.readExcel(tempFile);
	}

}
