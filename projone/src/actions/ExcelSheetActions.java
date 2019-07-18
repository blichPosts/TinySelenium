package actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*; // excel read in sheet // zzz
import org.codehaus.groovy.transform.sc.ListOfExpressionsExpression;

import helpers.DatabaseTable;

public class ExcelSheetActions {

	public static List<DatabaseTable> listOfDataBaseTables = new ArrayList<DatabaseTable>(); 

	
	// read excel sheet
	// https://www.callicoder.com/java-read-excel-file-apache-poi/
	// https://www.callicoder.com/java-read-excel-file-apache-poi/
	//@SuppressWarnings("deprecation")
	public static void readExcelSheet() throws Exception {
		String file = "C:/LichPublic/BoneYard/Cloud Elasticube Billing Data V2.xlsx";
		int loopCounter = 0;
		int numberOfStoredDatabases = -1;
		
		Workbook workbook = WorkbookFactory.create(new File(file));
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
		
        System.out.println("Retrieving Sheets using for-each loop");
        for(Sheet sheet: workbook) {
            System.out.println("=> " + sheet.getSheetName());
        }
        
        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);
        
        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        
        for (Row row: sheet) {
        	if(loopCounter == 0){ // don't user top row of sheet
        		loopCounter++;
        		continue;
        	}
        	
        	// loop through the rows of selected sheet
        	for(Cell cell: row) {
            	if(cell.getColumnIndex() == 5){ // check table name column
            		if(cell.toString() != "") { 
                        String cellValue = dataFormatter.formatCellValue(cell);
                        //System.out.println("Table ***** " +  cellValue);
                        listOfDataBaseTables.add(new DatabaseTable(cellValue));
                        numberOfStoredDatabases++;
            		}
            	}
            	
            	if(cell.getColumnIndex() == 6){
                    String cellValue = dataFormatter.formatCellValue(cell);
                    //ystem.out.print(cellValue + "\t");
                    listOfDataBaseTables.get(numberOfStoredDatabases).addColumnName(cellValue);
            	}
            	
            	if(cell.getColumnIndex() == 12){
            		if(cell.toString() != "") { 
                		String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.println("Field Alias " +  cellValue);
            		}
            	}
        	}
        }
    	workbook.close();
        System.out.println("num stored databases = " + numberOfStoredDatabases);
        for(DatabaseTable dataBaseTable: listOfDataBaseTables) {
        	dataBaseTable.Show();
        }
	}
}