package edu.handong.finalproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;




public class ReadFile {
	
	public ArrayList<String> getData(String path) {
		ArrayList<String> values = new ArrayList<String>();
		
		System.out.println(path);
		
		try (InputStream inp = new FileInputStream(path)) {
		    //InputStream inp = new FileInputStream("workbook.xlsx");
		    
		        Workbook wb = WorkbookFactory.create(inp);
		        Sheet sheet = (Sheet) wb.getSheetAt(0);
		        Row row = ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(2);
		        Cell cell = row.getCell(1);
		        if (cell == null)
		            cell = row.createCell(3);
		        
		        values.add(cell.getStringCellValue());
		        
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return values;
	}
	
	
	public ArrayList<String> getData(InputStream is) {
		ArrayList<String> values = new ArrayList<String>();
		
		try (InputStream inp = is) {
		    
		        Workbook wb = WorkbookFactory.create(inp);
		       
				Sheet sheet = wb.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for(int rowIndex = 0;rowIndex < rows;rowIndex++) {
					Row row = sheet.getRow(rowIndex);
					if(row != null) {
						int cells = row.getPhysicalNumberOfCells();
						String[] myRow = new String[cells];
						for(int columnIndex = 0; columnIndex< cells ;columnIndex++) {
							Cell cell = row.getCell(columnIndex);
							if(cell!=null) {
								myRow[columnIndex] = getStringValue(cell);
							}else {
								myRow[columnIndex] = " ";
							}
						}
						String all = Arrays.toString(myRow);
						
						values.add(all);
					}
				}
				
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return values;
	}
	
	public static String getStringValue(Cell cell) {
	    String rtnValue = "";
	    try {
	        rtnValue = cell.getStringCellValue();
	    } catch(IllegalStateException e) {
	        rtnValue = Integer.toString((int)cell.getNumericCellValue());            
	    }
	    
	    return rtnValue;
	}

}
