package admin.fe.util;

import admin.fe.model.Dashboard;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FileUtil {

    public static boolean GenerateExcelFile(int skipline,
                                                       String fileLocation, int headerx, String[][] header,
                                                       int recordx, String[][] records, int footerx){
        boolean result = false;
        Workbook wb = new HSSFWorkbook();

        Sheet sheet = wb.createSheet("Sheet1");
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);

        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setWrapText(true);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index);
        headerCellStyle.setShrinkToFit(true);
        

        // header row
        if (header != null)

            for (int i = 0; i < header.length; i++) {
                Row row = sheet.createRow(i);
                for (int j = 0; j < header[i].length; j++) {
                    Cell cell = row.createCell(j + headerx);
                    cell.setCellValue((String) header[i][j]);
                }
            }

//        sheet.addMergedRegion(new CellRangeAddress(0,2,1,3));

        // detail row
        if (records != null) {
            int headerLength = (header == null ? 0 : header.length);
            for (int i = 0; i < records.length; i++) {
                Row row = sheet.createRow(i + headerLength + skipline);
                for (int j = 0; j < records[i].length; j++) {
                    Cell cell = row.createCell(j + recordx);
                    cell.setCellValue((String) records[i][j]);
                    if(i==0){
                        cell.setCellStyle(headerCellStyle);
                    }

                }
            }
        }

        // footer row
//        if (footer != null)
//            for (int i = 0; i < footer.length; i++) {
//                Row row = sheet.createRow(i + header.length + records.length);
//                for (int j = 0; j < footer[i].length; j++) {
//                    Cell cell = row.createCell(j + footerx);
//                    cell.setCellValue((String) footer[i][j]);
//                }
//            }

        // adjust column width
        for (int i = 2; i < records[0].length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        if (wb instanceof XSSFWorkbook)
            fileLocation += "x";
        File file = new File(fileLocation);
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(file);
                    wb.write(out);
                    out.close();
                    result = true;
                } catch (FileNotFoundException e) {
                    result = false;
                } catch (IOException e) {
                    result = false;
                    e.printStackTrace();
                }

                return result;
     }

    public static String[][] generateExcelDashBoardHeader(){
        String[][] header = new String[4][3];
        header[0][1] = "Employee Grade Result";
        return header;
    }

    public static String[][] generateExcelDashBoardBody(
            List<Dashboard> dashboards) {
        String[][] record = new String[dashboards.size() + 1][22];
        record[0][0] = "Employee ID";
        record[0][1] = "Grade";
        record[0][2] = "Result";

        for (int i = 1; i <= dashboards.size(); i++) {
            Dashboard dashboard = dashboards.get(i - 1);
            record[i][0] = dashboard.getEmployeeId();
            record[i][1] = dashboard.getGrade();
            record[i][2] = dashboard.getResult();
        }
        return record;
    }
    
    public static Date getDate(Date date){
        Date result = new Date();
        try {
            if(date == null){

                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

                String dateString = format.format(result);

                Date date1 = format.parse ( dateString );

                return date1;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        
        
        return result;
        
        
    }
}
