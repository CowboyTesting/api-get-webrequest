package com.ea.eadp.data.aem.test.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.entity.mime.Header;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;
/*import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;*/

public class ExceldSpreadsheetData
{
	private transient List<?> data = null;

	public ExceldSpreadsheetData(final InputStream excelInputStream) throws IOException	{
		this.data = loadFromSpreadsheet(excelInputStream);
	}
	
	public List<?> getData(){
		return data;
	}
	
	private List<?> loadFromSpreadsheet(final InputStream excelFile) throws IOException{
		Map<String, String> rowMap = new HashMap<String, String>();
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		
		XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
		XSSFSheet worksheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = worksheet.iterator();
		
		List<XSSFTable> tables = worksheet.getTables();
		/*for(XSSFTable table : tables){
			CTTable ctTable = table.getCTTable();
			CTTableColumns ctColumns = ctTable.getTableColumns();
			List<String> strColumns = new ArrayList<String>();
			for(CTTableColumn column : ctColumns){
				strColumns.add(column.getName());
			}
			ctTable.
			int rowCount = table.getRowCount();
			
			while(rowIterator.hasNext()){
				XSSFRow row = rowIterator.next();
				CTRow ctRow = row.getCTRow();
				ctRow.getCArray(arg0)
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()){
					XSSFCell cell = cellIterator.next();
					CTCell ctCell = cell.getCTCell();
					ctCell.
					}
				}
			}
		}*/
		org.apache.poi.ss.usermodel.Header header = worksheet.getHeader();
		
		return rowsList;
	}
}
