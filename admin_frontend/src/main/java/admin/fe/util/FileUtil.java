package admin.fe.util;

import admin.fe.model.Dashboard;
import admin.fe.model.Report;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.zkoss.zul.Textbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

    public static boolean GenerateXLSFile(int skipline,
                                                       String fileLocation, int headerx, String[] header,
                                                       int recordx, List<Dashboard> data, int footerx,String sheetName){
        Workbook wb = new HSSFWorkbook();

        Sheet sheet = wb.createSheet(sheetName);

        org.apache.poi.ss.usermodel.Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);

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

        if(data!= null){
            for (Dashboard dashboard : data){
                Row row = sheet.createRow(rowData++);

                row.createCell(0).setCellValue(dashboard.getEmployeeId());
                row.createCell(1).setCellValue(dashboard.getGrade());
                row.createCell(2).setCellValue(dashboard.getResult());
            }
        }else {
            Row row = sheet.createRow(rowData++);
            row.createCell(0).setCellValue("");
            row.createCell(1).setCellValue("");
            row.createCell(2).setCellValue("");
        }

        for (int i = 0; i < header.length; i++){
            sheet.autoSizeColumn(i);
        }

        File file = new File(fileLocation);
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(file);
                    wb.write(out);
                    out.close();
                    return true;
                } catch (FileNotFoundException e) {
                    return false;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
     }

     public static String[] generateHeader(){
        String [] header = {"Employee ID","Grade","Result"};
        return header;
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

        if (dashboards!=null){
            for(int i = 0; i < dashboards.size(); i++){
                Dashboard dashboard = dashboards.get(i);
                table.addCell(dashboard.getEmployeeId());
                table.addCell(dashboard.getGrade());
                table.addCell(dashboard.getResult());
            }
        }else{
            table.addCell("");
            table.addCell("");
            table.addCell("");
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

    public static boolean generatePDFFileReportEmployee(String fileLocation, List<Report> reports){

        Document document = new Document();
        PdfPTable table = new PdfPTable(new float[] {1,1,1,1});

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

        cell = new PdfPCell(new Phrase("Competencies"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Mark"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        table.setHeaderRows(1);

        if(reports!=null){
            for(int i = 0; i < reports.size(); i++){
                Report report = reports.get(i);
                table.addCell(report.getEmployeeCode());
                table.addCell(report.getGrade());
                table.addCell(report.getCompetencies());
                table.addCell(report.getMark());
            }
        }else {
            table.addCell("");
            table.addCell("");
            table.addCell("");
            table.addCell("");
        }

        try{
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileLocation));
            document.open();
            Paragraph paragraph = new Paragraph("Report Employee", FontFactory.getFont(FontFactory.TIMES_ROMAN,20, com.itextpdf.text.Font.BOLD) );
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingAfter(25);
            document.setPageSize(PageSize.LETTER.rotate());
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

    public static String[] generateHeaderReportEmployee(){
        String [] header = {"Employee ID","Grade","Competencies","Mark"};
        return header;
    }

    public static boolean GenerateXLSFileReportEmployee(int skipline,
                                          String fileLocation, int headerx, String[] header,
                                          int recordx, List<Report> data, int footerx,String sheetName){
        Workbook wb = new HSSFWorkbook();

        Sheet sheet = wb.createSheet(sheetName);

        org.apache.poi.ss.usermodel.Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);

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

        if(data!=null){
            for (Report report : data){
                Row row = sheet.createRow(rowData++);

                row.createCell(0).setCellValue(report.getEmployeeCode());
                row.createCell(1).setCellValue(report.getGrade());
                row.createCell(2).setCellValue(report.getCompetencies());
                row.createCell(3).setCellValue(report.getMark());
            }
        } else {
            Row row = sheet.createRow(rowData++);

            row.createCell(0).setCellValue("");
            row.createCell(1).setCellValue("");
            row.createCell(2).setCellValue("");
            row.createCell(3).setCellValue("");
        }

        for (int i = 0; i < header.length; i++){
            sheet.autoSizeColumn(i);
        }

        File file = new File(fileLocation);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            wb.write(out);
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int validateRegex(List<Textbox> textboxes){

        String regex ="^[a-zA-Z0-9@''.]+\\s?[a-zA-Z0-9@''.]+$";
        int i = 0;

        Pattern pattern = Pattern.compile(regex);

        for(Textbox tb : textboxes){

            Matcher matcher = pattern.matcher(tb.getValue());

            if(matcher.matches() == false){
                i++;
            }


        }

        return i;

    }

}
