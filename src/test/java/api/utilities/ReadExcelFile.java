package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class ReadExcelFile {

	public static FileInputStream inputStream;
	public static XSSFWorkbook workBook;
	public static XSSFSheet excelSheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static String getCellValue(String fileName, String sheetName,int rowNo,int cellNo) throws IOException
	{
		inputStream=new FileInputStream(fileName);
		workBook=new XSSFWorkbook(inputStream);
		excelSheet=workBook.getSheet(sheetName);
		cell=excelSheet.getRow(rowNo).getCell(cellNo);
		
		DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell); // Safe for String & Numeric cells
        } catch (Exception e) {
            data = "";
        }
        
		workBook.close();
		inputStream.close();
		
		return data;
	}
	
	public static int getRowCount(String fileName, String sheetName) throws IOException{
		inputStream=new FileInputStream(fileName);
		workBook=new XSSFWorkbook(inputStream);
		excelSheet=workBook.getSheet(sheetName);
		int ttlRows=excelSheet.getLastRowNum()+1;
		workBook.close();
		inputStream.close();
		return ttlRows;
	}
	
	public static int getColCount(String fileName, String sheetName) throws IOException{
		inputStream=new FileInputStream(fileName);
		workBook=new XSSFWorkbook(inputStream);
		excelSheet=workBook.getSheet(sheetName);
		int ttlCells=excelSheet.getRow(0).getLastCellNum();
		workBook.close();
		inputStream.close();
		return ttlCells;
	}
}
