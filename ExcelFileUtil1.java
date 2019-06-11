package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelFileUtil1 {
	Workbook wb;

	// Loading Excel file
	public ExcelFileUtil1() throws Throwable {
		FileInputStream fis = new FileInputStream("./TestInputs/InputSheet.xlsx");
		wb = WorkbookFactory.create(fis);
	}

	// count no of rows in sheet
	public int rowCount(String sheetname) {
		Sheet sh = wb.getSheet(sheetname);
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
			int celldata = (int) wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getNumericCellValue();
			data = String.valueOf(celldata);
		} else {
			data = wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getStringCellValue();
		}
		return data;
	}

	// writing data into cell
	public void setData(String sheetname, int row, int colnum, String status) throws Throwable {
		Sheet sh = wb.getSheet(sheetname);
		Row rownum = sh.getRow(row);
		Cell cell = rownum.createCell(colnum);
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("Pass")) {
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			// Apply colour to font
			font.setColor(IndexedColors.GREEN.getIndex());
			// Apply bold to the text
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(colnum).setCellStyle(style);
		}
		if (status.equalsIgnoreCase("Fail")) {
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			// Apply colour to font
			font.setColor(IndexedColors.RED.getIndex());
			// Apply bold to the text
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(colnum).setCellStyle(style);
		}
		if (status.equalsIgnoreCase("Not Excuted")) {
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			// Apply colour to font
			font.setColor(IndexedColors.BLUE.getIndex());
			// Apply bold to the text
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(colnum).setCellStyle(style);
		}
		FileOutputStream fos = new FileOutputStream("./TestOutputs/InputSheet.xlsx");
		wb.write(fos);
		fos.close();

	}
}
