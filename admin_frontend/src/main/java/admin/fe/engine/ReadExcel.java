/**
 * 
 */
package admin.fe.engine;

 
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import admin.fe.model.Employee;

/**
 * @author gederanadewadatta
 *
 */
public class ReadExcel {
	@Value("${path.temp.employee}")
	protected String pathTempEmployee;
	@Value("${path.temp.question}")
	protected String pathTempQuestion;
 

	public List<String> copyEmployeeToTemp(InputStream stream) {
		List<String> result;
		File f = new File(pathTempEmployee);
		OutputStream os = null;
		try {
			os = new FileOutputStream(f);
			byte buf[] = new byte[1024];
			int len;

			while ((len = stream.read(buf)) > 0) {
				os.write(buf, 0, len);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e1) {
//					logger.error(e1.getMessage(), e1);
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
//					logger.error(e1.getMessage(), e1);
				}
			}
		}
		result = this.loadFileTempEmployee(pathTempEmployee);
		return result;
	}

	private List<String> loadFileTempEmployee(String pathTempEmployee) {
		// TODO Auto-generated method stub
		List<String> dataTemp = new ArrayList<String>();
		try{
			Cell cell;
			File file = new File(pathTempEmployee);
			FileInputStream fis = new FileInputStream(file);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(0);
//			Row row;
			Cell[][] tempArray = new Cell[sheet.getLastRowNum()][row.getLastCellNum()];
			int lastColNum = row.getLastCellNum();
			//Extract Data : start
			int rowCount = 0;
			for(int i = 0; i < sheet.getLastRowNum(); i++){
				row = sheet.getRow(rowCount);
				for(int j = 0; j < lastColNum; j++){
					tempArray[i][j] = row.getCell(j);
				}
				rowCount++;
			}
			//Extract Data : end

			//Insert to DB : start
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
			for(int a = 1; a < tempArray.length; a++){
				for(int b = 0; b < tempArray[0].length; b++){
					if(a != 0){
						if(tempArray[0][b].getStringCellValue().equals("Bulan")){
							System.out.println("Bulan Type Data="+tempArray[a][b].getCellType());
							dataTemp.add(sdf.format(tempArray[a][b].getDateCellValue()));
						}else if(tempArray[0][b].getStringCellValue().equals("REGION")){
							System.out.println("REGION Type Data="+tempArray[a][b].getCellType());
							dataTemp.add(tempArray[a][b].getStringCellValue());
						}else if(tempArray[0][b].getStringCellValue().equals("Branch Code")){
							System.out.println("Branch Code Type Data="+tempArray[a][b].getCellType());
							dataTemp.add(String.valueOf(tempArray[a][b].getNumericCellValue()));
						}else if(tempArray[0][b].getStringCellValue().equals("Branch name")){
							System.out.println("Branch Name Type Data="+tempArray[a][b].getCellType());
							dataTemp.add(tempArray[a][b].getStringCellValue());
						}else if(tempArray[0][b].getStringCellValue().equals("Product Code")){
							System.out.println("Product Code Type Data="+tempArray[a][b].getCellType());
							dataTemp.add(tempArray[a][b].getStringCellValue());
						}else if(tempArray[0][b].getStringCellValue().equals("Product Name")){
							System.out.println("Product Name Type Data="+tempArray[a][b].getCellType());
							dataTemp.add(tempArray[a][b].getStringCellValue());
						}else if(tempArray[0][b].getStringCellValue().contains("Target APE\nFull Year")){
							System.out.println("Full Year Type Data="+tempArray[a][b].getCellType());
							dataTemp.add(String.valueOf(tempArray[a][b].getNumericCellValue()));
						}else if(tempArray[0][b].getStringCellValue().equals("Target APE")){
							System.out.println("Target APE Type Data="+tempArray[a][b].getCellType());
							dataTemp.add(String.valueOf(tempArray[a][b].getNumericCellValue()));
						}else if(tempArray[0][b].getStringCellValue().equals("Aktual APE")){
							System.out.println("Aktual APE Type Data="+tempArray[a][b].getCellType());
							dataTemp.add(String.valueOf(tempArray[a][b].getNumericCellValue()));
						}
					}
				}
				 
			}
			// Insert to DB : end
		}catch (FileNotFoundException ex){
			ex.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return dataTemp; 
	}
}
