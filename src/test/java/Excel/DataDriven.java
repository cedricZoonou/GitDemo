package Excel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {
	
	
	
	
	//identify testcases column by scanning entire 1row
	//once the column is identified then scan entire testcase column to identify purchase testcase row
	// after you grab testcase row = pull all the data of that row and feed into test

	
	public ArrayList<String> getData(String testcaseName) throws IOException 
	{
		//fileInputstream argument
				ArrayList<String> a=new ArrayList<String>();
				
				FileInputStream fis= new FileInputStream("C:\\Users\\CedricIvars\\Documents\\dataTest.xlsx");
				 XSSFWorkbook workbook=new  XSSFWorkbook(fis);
				 
				int sheets= workbook.getNumberOfSheets();
				 for(int i=0;i<sheets;i++) 
				 {
					if(workbook.getSheetName(i).equalsIgnoreCase("Sheet1"))
						{
							XSSFSheet sheet = workbook.getSheetAt(i);
					
							//identify testcases column by scanning entire 1row
							Iterator<Row> rows=	sheet.iterator();
							Row firstrow=	rows.next();//row is collection of cells
							Iterator<Cell> ce =	firstrow.cellIterator();
							int k=0;
							int column = 0;
							while(ce.hasNext()) 
								{
								Cell value =	ce.next();
								if(value.getStringCellValue().equalsIgnoreCase("Testcases")) 
									{
										column=k;
									}
								k++;
								}
							
							
							//once the column is identified then scan entire testcase column to identify purchase testcase row
							while(rows.hasNext()) 
							{
							 Row r=rows.next();
							 if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) 
							 	{
								// after you grab testcase row = pull all the data of that row and feed into test	
								 
								 
								 Iterator<Cell> cv=r.cellIterator();
								 while(cv.hasNext()) 
								 	{
									 Cell c= cv.next();
									 if(c.getCellType()==CellType.STRING)
										 
									 { 
									a.add(c.getStringCellValue());
								 	}
									 else 
									 {
										a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
										 
									 }
									 }
								 
							 	}
							}
						}
				 }
				 return a;
			}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
	}
}
