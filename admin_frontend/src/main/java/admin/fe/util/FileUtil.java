package admin.fe.util;

import admin.fe.model.Dashboard;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
        record[0][0] = "No.";
        record[0][1] = "Nama";
        record[0][2] = "Jenis Kelamin";

        for (int i = 1; i <= dashboards.size(); i++) {
            Dashboard dashboard = dashboards.get(i - 1);
            record[i][0] = dashboard.getEmployeeId();
            record[i][1] = dashboard.getGrade();
            record[i][2] = dashboard.getResult();
        }
        return record;
    }
}
