package admin.fe.util;

import admin.fe.model.Dashboard;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.zul.Messagebox;

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
                                                       String fileLocation, int headerx, String[] header,
                                                       int recordx, List<Dashboard> data, int footerx,String sheetName){
        boolean result = false;
        Workbook wb = new XSSFWorkbook();

        Sheet sheet = wb.createSheet(sheetName);
        //PrintSetup printSetup = sheet.getPrintSetup();
        //printSetup.setLandscape(true);
        //sheet.setFitToPage(true);
        //sheet.setHorizontallyCenter(true);

        org.apache.poi.ss.usermodel.Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 11);

        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setWrapText(false);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerCellStyle.setFillBackgroundColor(IndexedColors.RED.index);
        headerCellStyle.setFillForegroundColor(IndexedColors.RED.index);

        Row headerRow = sheet.createRow(0);

        if(header.length != 0){
            for(int i = 0; i < header.length; i++){
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(headerCellStyle);
            }
        }

        int rowData = 1;

        for (data DATA)

        // detail row
        /*if (records != null) {
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
        }*/

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

     public static String[] generateHeader(){
        String [] header = {"Employee ID","Grade","Result"};
        return header;
     }

    /*public static String[][] generateExcelDashBoardHeader(){
        String[][] header = new String[4][3];
        header[0][1] = "Employee Grade Result";
        return header;
    }*/


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

    public static boolean generatePDFFile(String fileLocation, List<Dashboard> dashboards){

        Document document = new Document();
        PdfPTable table = new PdfPTable(new float[] {1,1,1});

        table.setSpacingAfter(25);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell cell = null;

        cell = new PdfPCell(new Phrase("Employee ID"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Grade"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Result"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        table.setHeaderRows(1);

        for(int i = 0; i < dashboards.size(); i++){
            Dashboard dashboard = dashboards.get(i);
            table.addCell(dashboard.getEmployeeId());
            table.addCell(dashboard.getGrade());
            table.addCell(dashboard.getResult());
        }

        try{
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileLocation));
            document.open();
            Paragraph paragraph = new Paragraph("Employee Grade Result", FontFactory.getFont(FontFactory.TIMES_ROMAN,20, com.itextpdf.text.Font.BOLD) );
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingAfter(25);
            document.add(paragraph);
            document.add(table);
            document.close();
            writer.close();

            return true;
        }catch (DocumentException e){
            e.printStackTrace();
            return false;
        }catch (FileNotFoundException e){
            e.printStackTrace();
           return false;
        }

    }

    public static boolean genarateXLSFile(String fileLocation, List<Dashboard> dashboards){

        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Sheet1");
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setWrapText(true);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index);
        headerCellStyle.setShrinkToFit(true);

        Row headerRow = sheet.createRow(0);

        Cell cell1 = headerRow.createCell(0);
        cell1.setCellValue("Employee ID");
        cell1.setCellStyle(headerCellStyle);

        Cell cell2 = headerRow.createCell(1);
        cell2.setCellValue("Grade");
        cell2.setCellStyle(headerCellStyle);

        Cell cell3 = headerRow.createCell(2);
        cell3.setCellValue("Result");
        cell3.setCellStyle(headerCellStyle);

        for(int i = 0; i < dashboards.size(); i++){
            Dashboard dashboard = dashboards.get(i);
            Row row = sheet.createRow(i++);
            row.createCell(0).setCellValue(dashboard.getEmployeeId());
            row.createCell(1).setCellValue(dashboard.getGrade());
            row.createCell(2).setCellValue(dashboard.getResult());
        }

        for (int i=0; i<2;i++){
            sheet.autoSizeColumn(i);
        }

        try{
            File file = new File(fileLocation);
            FileOutputStream fileout = new FileOutputStream(file);
            workbook.write(fileout);
            fileout.close();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
