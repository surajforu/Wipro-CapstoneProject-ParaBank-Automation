package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static final String FILE_PATH = "src/test/resources/testdata/ParabankData.xlsx";

	public static String getCellData(String sheetName, int rowNum, int colNum) {

		try {

			FileInputStream fis = new FileInputStream(FILE_PATH);

			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workbook.getSheet(sheetName);

			if (sheet == null) {
				System.out.println("Sheet not found: " + sheetName);
				workbook.close();
				return "";
			}

			if (sheet.getRow(rowNum) == null) {
				System.out.println("Row not found: " + rowNum);
				workbook.close();
				return "";
			}

			if (sheet.getRow(rowNum).getCell(colNum) == null) {
				System.out.println("Cell not found");
				workbook.close();
				return "";
			}

			String value = sheet.getRow(rowNum).getCell(colNum).toString();

			workbook.close();

			return value;

		} catch (Exception e) {

			e.printStackTrace();
			return "";
		}
	}

	public static void setCellData(String sheetName, int rowNum, int colNum, String value) {

		try {

			FileInputStream fis = new FileInputStream(FILE_PATH);

			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workbook.getSheet(sheetName);

			Row row = sheet.getRow(rowNum);

			if (row == null) {
				row = sheet.createRow(rowNum);
			}

			Cell cell = row.getCell(colNum);

			if (cell == null) {
				cell = row.createCell(colNum);
			}

			cell.setCellValue(value);

			FileOutputStream fos = new FileOutputStream(FILE_PATH);

			workbook.write(fos);

			fos.close();
			workbook.close();
			fis.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}