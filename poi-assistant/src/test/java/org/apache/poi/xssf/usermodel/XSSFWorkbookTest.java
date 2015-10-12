package org.apache.poi.xssf.usermodel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class XSSFWorkbookTest {
	// private File file;
	private XSSFWorkbook workbook = new XSSFWorkbook();
	private String sheetName = "sheetName";
	private XSSFSheet sheet;
	private int rowNum = 3;
	private XSSFRow row;
	private int booleanIndex = 0;
	private int calendarIndex = 1;
	private int dateIndex = 2;
	private int doubleIndex = 3;
	private int richTextStringIndex = 4;
	private int stringIndex = 5;
	private boolean booleanValue = true;
	private Calendar calendarValue = Calendar.getInstance();
	private Date dateValue = new Date();
	private double doubleValue = 1.1d;
	private RichTextString richTextStringValue = workbook.getCreationHelper()
			.createRichTextString("rich text string");
	private String stringValue = "string";
	private Cell booleanCell;
	private Cell calendarCell;
	private Cell dateCell;
	private Cell doubleCell;
	private Cell richTextStringCell;
	private Cell stringCell;

	@BeforeClass
	public void beforeClass() {
	}

	@Test
	public void getSheet() {
		XSSFSheet sheet = workbook.getSheet(sheetName);
		Assert.assertNull(sheet);
	}

	@Test(dependsOnMethods = { "getSheet" })
	public void createSheet() {
		workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);
		Assert.assertNotNull(sheet);
	}

	@Test(dependsOnMethods = { "createSheet" })
	public void getRow() {
		XSSFRow row = sheet.getRow(rowNum);
		Assert.assertNull(row);
	}

	@Test(dependsOnMethods = { "getRow" })
	public void createRow() {
		sheet.createRow(rowNum);
		row = sheet.getRow(rowNum);
		Assert.assertNotNull(row);
	}

	@Test(dependsOnMethods = { "createRow" })
	public void getCell() {
		XSSFCell booleanCell = row.getCell(booleanIndex);
		Assert.assertNull(booleanCell);
		XSSFCell calendarCell = row.getCell(calendarIndex);
		Assert.assertNull(calendarCell);
		XSSFCell dateCell = row.getCell(dateIndex);
		Assert.assertNull(dateCell);
		XSSFCell doubleCell = row.getCell(doubleIndex);
		Assert.assertNull(doubleCell);
		XSSFCell richTextStringCell = row.getCell(richTextStringIndex);
		Assert.assertNull(richTextStringCell);
		XSSFCell stringCell = row.getCell(stringIndex);
		Assert.assertNull(stringCell);
	}

	@Test(dependsOnMethods = { "getCell" })
	public void createCell() {
		row.createCell(booleanIndex);
		booleanCell = row.getCell(booleanIndex);
		Assert.assertNotNull(booleanCell);
		row.createCell(calendarIndex);
		calendarCell = row.getCell(calendarIndex);
		Assert.assertNotNull(calendarCell);
		row.createCell(dateIndex);
		dateCell = row.getCell(dateIndex);
		Assert.assertNotNull(dateCell);
		row.createCell(doubleIndex);
		doubleCell = row.getCell(doubleIndex);
		Assert.assertNotNull(doubleCell);
		row.createCell(richTextStringIndex);
		richTextStringCell = row.getCell(richTextStringIndex);
		Assert.assertNotNull(richTextStringCell);
		row.createCell(stringIndex);
		stringCell = row.getCell(stringIndex);
		Assert.assertNotNull(stringCell);
	}

	@Test(dependsOnMethods = { "createCell" })
	public void cellValue() {
		booleanCell.setCellValue(booleanValue);
		Assert.assertEquals(booleanCell.getCellType(), Cell.CELL_TYPE_BOOLEAN);
		Assert.assertEquals(booleanCell.getBooleanCellValue(), booleanValue);
		calendarCell.setCellValue(calendarValue);
		Assert.assertEquals(calendarCell.getCellType(), Cell.CELL_TYPE_NUMERIC);
		Assert.assertEquals(calendarCell.getDateCellValue(), calendarValue.getTime());
		dateCell.setCellValue(dateValue);
		Assert.assertEquals(dateCell.getCellType(), Cell.CELL_TYPE_NUMERIC);
		Assert.assertEquals(dateCell.getDateCellValue(), dateValue);
		doubleCell.setCellValue(doubleValue);
		Assert.assertEquals(doubleCell.getCellType(), Cell.CELL_TYPE_NUMERIC);
		Assert.assertEquals(doubleCell.getNumericCellValue(), doubleValue);
		richTextStringCell.setCellValue(richTextStringValue);
		Assert.assertEquals(richTextStringCell.getCellType(),
				Cell.CELL_TYPE_STRING);
		Assert.assertEquals(richTextStringCell.getRichStringCellValue().getString(),
				richTextStringValue.getString());
		stringCell.setCellValue(stringValue);
		Assert.assertEquals(stringCell.getCellType(), Cell.CELL_TYPE_STRING);
		Assert.assertEquals(stringCell.getStringCellValue(), stringValue);
	}

	@Test(dependsOnMethods = { "cellValue" })
	public void write() throws Exception {
		File file = new File("/tmp/XSSFWorkbookTest_" + System.nanoTime()
				+ ".xlsx");
		FileOutputStream outputStream = new FileOutputStream(
				file.getAbsolutePath());
		workbook.write(outputStream);
		Assert.assertTrue(file.exists());
	}

}
