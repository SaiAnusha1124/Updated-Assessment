package com.atmecs.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.atmecs.reports.LogReports;

public class ReadExcelFile {
	static FileInputStream fileReader;
	static Workbook book;
	static Sheet sheet;
	static LogReports log = new LogReports();

	public Object[][] readExcel(String sheetName, String path) {
		@SuppressWarnings("unused")
		FileInputStream file = null;
		try {
			fileReader = new FileInputStream(path);

		} catch (FileNotFoundException e) {
			log.info("file not found");
		}
		try {
			book = WorkbookFactory.create(fileReader);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
}
