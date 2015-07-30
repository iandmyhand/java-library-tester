package study.hard.javalib.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import study.hard.javalib.exception.BaseException;

public class ExcelHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExcelHandler.class);

	private static final String EXCEL_FILE_EXTENSION = "xlsx";
	private static final String TEMP_DIR_PREFIX = "TEMP_DIR_";
	private static final String TEMP_EXCEL_FILENAME = "temp_excel_file";

	public File createTemporaryExcelFile() throws IOException {
		File directory = null;
		try {
			directory = File.createTempFile(TEMP_DIR_PREFIX, "");
		} catch (IOException e) {
			logger.error("Failed to create temporary directory.", e);
			throw e;
		}
		if (directory == null) {
			throw new BaseException("Failed to create temporary directory.");
		}
		directory.delete();
		if (!directory.exists()) {
			directory.mkdirs();
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = TEMP_EXCEL_FILENAME + "_" + dateFormat.format(new Date()) + "." + EXCEL_FILE_EXTENSION;

		return new File(directory, fileName);
	}

	public void writeExcel(File file) {
		//Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		//Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Employee Data");

		//This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
		data.put("2", new Object[] {1, "Amit", "Shukla"});
		data.put("3", new Object[] {2, "Lokesh", "Gupta"});
		data.put("4", new Object[] {3, "John", "Adwards"});
		data.put("5", new Object[] {4, "Brian", "Schultz"});

		//Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr)
			{
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String)obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer)obj);
			}
		}
		try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
			//Write the workbook in file system
			workbook.write(fileOutputStream);
			logger.debug("{} written successfully on disk.", file.getPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readExcel(File file) {
		try (FileInputStream fileInputStream = new FileInputStream(file)) {

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				//For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					//Check the cell type and format accordingly
					switch (cell.getCellType())
					{
						case Cell.CELL_TYPE_NUMERIC:
							logger.debug("{}\t", cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							logger.debug("{}\t", cell.getStringCellValue());
							break;
					}
				}
				logger.debug("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateExcel(File file) {
		try (FileInputStream fileInputStream = new FileInputStream(file)) {

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				Cell cell = row.getCell(0);
				Cell newCell = null;
				if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
					Double cellValue = cell.getNumericCellValue();
					newCell = row.createCell(0, Cell.CELL_TYPE_NUMERIC);
					newCell.setCellValue(cellValue * 2d);
				}
				cell = row.getCell(1);
				if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
					String cellValue = cell.getStringCellValue();
					newCell = row.createCell(0, Cell.CELL_TYPE_STRING);
					newCell.setCellValue("UPDATED: " + cellValue);
				}
			}
			try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
				workbook.write(fileOutputStream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
