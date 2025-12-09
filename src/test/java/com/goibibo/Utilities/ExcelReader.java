package com.goibibo.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelReader {

	@DataProvider(name = "FlightBookingDetails")
	String[][] getExcelData() {
		Reader read = new Reader(".\\testData\\booking data.xlsx", "Sheet1");
		int row = read.getLastRow();
		int col = read.getLastCol();

		String[][] data = new String[row - 1][col];

		for (int i = 1; i < row; i++) {
			for (int j = 0; j < col; j++) {
				data[i - 1][j] = read.getData(i, j);

			}
		}
		System.out.println(Arrays.deepToString(data));
		return data;
	}
}

class Reader {

	FileInputStream fi;
	Workbook book;
	Sheet sheet;

	Reader(String path, String sheetName) {
		try {
			fi = new FileInputStream(path);
			book = WorkbookFactory.create(fi);
		} catch (Exception e) {

			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);

	}

	int getLastRow() {
		return sheet.getPhysicalNumberOfRows();
	}

	int getLastCol() {
		Row row = sheet.getRow(0);
		return row.getLastCellNum();
	}

	String getData(int r, int c) {
		Row row = sheet.getRow(r);
		Cell cell = row.getCell(c);

		DataFormatter df = new DataFormatter();
		return df.formatCellValue(cell);

	}

}
