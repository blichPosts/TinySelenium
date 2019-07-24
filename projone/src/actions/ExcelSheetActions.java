package actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*; // excel read in sheet // zzz
import org.codehaus.groovy.transform.sc.ListOfExpressionsExpression;

import helpers.DatabaseTable;

public class ExcelSheetActions {

	public static List<DatabaseTable> listOfDataBaseTables = new ArrayList<DatabaseTable>(); 
	public static String currentFieldName = "";
	public static String currentFieldAlias = "";
	
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
            	
        		// check table name column. if it has a name, create a new database table object, add it to the list of database tables, and update the number of stored databases counter.
        		if(cell.getColumnIndex() == 5){ 
            		if(cell.toString() != "") { 
                        String cellValue = dataFormatter.formatCellValue(cell);
                        listOfDataBaseTables.add(new DatabaseTable(cellValue));
                        numberOfStoredDatabases++;
            		}
            	}
            	
        		// store field name (column name)
            	if(cell.getColumnIndex() == 6){ 
                    String cellValue = dataFormatter.formatCellValue(cell);
                    currentFieldName = cellValue;
            	}
            	 
            	// this is the last cell in the row to check (field alias). after getting this value the field name and field alias can be added to the currently active database tables.
            	if(cell.getColumnIndex() == 12){  
            		if(cell.toString() != "") { 
                		String cellValue = dataFormatter.formatCellValue(cell);
                        currentFieldAlias = cellValue;
            		}
                    listOfDataBaseTables.get(numberOfStoredDatabases).addFields(currentFieldName, currentFieldAlias);
                    currentFieldAlias = "";
                    currentFieldName = "";
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