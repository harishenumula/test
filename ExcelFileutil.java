/**
 * 
 */
package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author harish
 *
 */
public class ExcelFileutil {
	// loading Excel File.
	Workbook wb;

	public ExcelFileutil() throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fi = new FileInputStream("./TestInputs/InputSheet.xlsx");
		wb = WorkbookFactory.create(fi);
	}

	// Count no of rows in sheet
	public int rowCount(String sheetname) {
		return wb.getSheet(sheetname).getLastRowNum();
	}

	// count no of columns
	public int colCount(String sheetname, int rownum) {
		return wb.getSheet(sheetname).getRow(rownum).getLastCellNum();
	}

	// get cell value
	public String getData(String sheetname, int rownum, int colnum) {
		String data = "";
		if (wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getCellType() == Cell.CELL_TYPE_NUMERIC) {
			data = String.valueOf(wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getNumericCellValue());
		} else {
			data = wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getStringCellValue();
		}
		return data;
	}

	public void setData(String sheetname, int row, int colnum, String status) throws IOException {
		Sheet sh = wb.getSheet(sheetname);
		Row rownum = sh.getRow(row);
		Cell c = rownum.createCell(colnum);
		c.setCellValue(status);
		// Add color to cell
		if (status.equalsIgnoreCase("pass")) {
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());

			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(colnum).setCellStyle(style);
		}
		if (status.equalsIgnoreCase("fail")) {
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());

			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(colnum).setCellStyle(style);
		}
		if (status.equalsIgnoreCase("not Executed")) {
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());

			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(colnum).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream("./TestOutputs/InputSheet.xlsx");
		wb.write(fo);
		fo.close();
		wb.close();
	}
}
