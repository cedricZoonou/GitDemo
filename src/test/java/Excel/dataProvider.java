package Excel;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvider {

		DataFormatter formatter= new DataFormatter();
		@Test(dataProvider="driveTest")
		public void testCaseData(String email, String password, String product) 
		{
			System.out.println(email);
			
		}
		// TODO Auto-generated method stub

		@DataProvider(name="driveTest")
		public Object[][] getData() throws IOException
		{

			
			FileInputStream fis= new FileInputStream("C:\\Users\\CedricIvars\\Documents\\dataTest.xlsx");
			 XSSFWorkbook workbook=new  XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			int rowCount =	sheet.getPhysicalNumberOfRows();
			XSSFRow row = sheet.getRow(0);
			int colcount =	row.getLastCellNum();
			Object data[][] = new Object[rowCount -1][colcount];
			for(int i=0;i<rowCount-1;i++) 
			{
				row=sheet.getRow(i+1);
				for(int j=0;j<colcount;j++) 
				{
					XSSFCell cell= row.getCell(j);
					
					data[i][j]=formatter.formatCellValue(cell);
				}
			}
			
			
			return data;
		}
		
		
		
	}


 