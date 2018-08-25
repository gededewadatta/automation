/**
 * 
 */
package admin.fe.engine;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import admin.fe.model.Employee;

/**
 * @author gederanadewadatta
 *
 */
public class ReadExcel {
	public Employee readUpload(File fileUpload) {
		List<String> listEmployee = new ArrayList<String>();
		Employee employee = new Employee();
		try {

			FileInputStream excelFile = new FileInputStream(fileUpload);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator(); 
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					// getCellTypeEnum shown as deprecated for version 3.15
					// getCellTypeEnum ill be renamed to getCellType starting
					// from version 4.0
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						listEmployee.add(currentCell.getStringCellValue());
						System.out.print(currentCell.getStringCellValue() + "--");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {

						listEmployee.add(currentCell.getNumericCellValue() + ",");
						System.out.print(currentCell.getNumericCellValue() + "--");
					}

				}
				System.out.println();

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		employee.setId(listEmployee.subList(0, 1).toString());
		employee.setName(listEmployee.subList(1, 2).toString());
		
		return employee;

	}
}
