package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="AllData")
	public String[][] AllDataProvider() throws IOException{
		String fname=System.getProperty("user.dir")+"//TestData//TestData.xlsx";
		int ttlRowCnt=ReadExcelFile.getRowCount(fname, "Sheet1");
		int ttlColCnt=ReadExcelFile.getColCount(fname, "Sheet1");
		
		String UserData[][]=new String[ttlRowCnt-1][ttlColCnt];
		
		for(int rowNo=1;rowNo<ttlRowCnt;rowNo++){
			for(int colNo=0;colNo<ttlColCnt;colNo++) {
				UserData[rowNo-1][colNo]=ReadExcelFile.getCellValue(fname, "Sheet1", rowNo, colNo);
			}
			
		}
		return UserData;
	}
	
	@DataProvider(name="UserNameData")
	public String[] UserNameDataProvider() throws IOException {
		String fname=System.getProperty("user.dir")+"//TestData//TestData.xlsx";
		int ttlRowCnt=ReadExcelFile.getRowCount(fname, "Sheet1");
		
		String UserData[]=new String[ttlRowCnt-1];
		
		for(int rowNo=1;rowNo<ttlRowCnt;rowNo++){
				UserData[rowNo-1]=ReadExcelFile.getCellValue(fname, "Sheet1", rowNo, 1);
		}
		return UserData;
	}
}
