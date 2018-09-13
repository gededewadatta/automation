package admin.fe.controller.FileUpload.Employee;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.Department;
import admin.fe.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.TextBox;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeUploadController extends CommonController {

    protected Grid hGrid;
    protected ListModelList modelList;
    protected Textbox idUpload;
    org.zkoss.zul.Row rw;

    List<Employee> emp = new ArrayList<>();



    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onUpload$browseButton(UploadEvent e){
        System.out.println("Test Mesia :"+e.getMedias());
        doValidate(e.getMedias());
    }

    public void doValidate(Media[] multiMedia) {

        for (Media med : multiMedia) {

            idUpload.setValue(med.getName());
            copyToTemp(med.getStreamData());
        }


        modelList = new ListModelList(emp);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());




    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(org.zkoss.zul.Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Employee) data);
            }

            private void renderDataRow(org.zkoss.zul.Row row, Employee employee){

                row.setValue(employee);
                new Label(employee.getDivisionCode()).setParent(row);
                new Label(employee.getDepartementCode()).setParent(row);
                new Label(employee.getEmployeeCode()).setParent(row);
                new Label(employee.getEmployeeName()).setParent(row);
                rw = row;
            }
        };
    }

    public List<Employee> loadFileTemp() {
        int result = 0;

        List<String> dataTemp = new ArrayList<String>();
        String path = "E:/Latihan/EMPLOYEETemp.xls";
        List<Employee> employees = new ArrayList<>();
        Employee employee;
        try{

            Cell cell;
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            Workbook wb = new HSSFWorkbook(fis);
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
            System.out.println("tEMP aRRAY lENGTH"+tempArray.length);
            for(int a = 1; a < tempArray.length; a++){
                employee = new Employee();
                for(int b = 0; b < tempArray[0].length; b++){
                    if(a != 0){
                        if(tempArray[0][b].getStringCellValue().equals("DEPARTEMENT_CODE")){
                            System.out.println("Departement COde="+tempArray[a][b].getStringCellValue());
                            employee.setDepartementCode(tempArray[a][b].getStringCellValue());
                        }else if(tempArray[0][b].getStringCellValue().equals("GRADE_CODE")){
                            System.out.println("Grade Code="+tempArray[a][b].getNumericCellValue());
                            employee.setGradeCode(String.valueOf(tempArray[a][b].getNumericCellValue()));
                        }else if(tempArray[0][b].getStringCellValue().equals("DIVISION_CODE")){
                            System.out.println("Division Code="+tempArray[a][b].getStringCellValue());
                            employee.setDivisionCode(String.valueOf(tempArray[a][b].getStringCellValue()));
                        }else if(tempArray[0][b].getStringCellValue().equals("SUB_GRADE_CODE")){
                            System.out.println("Sub Grade Code="+tempArray[a][b].getNumericCellValue());
                            employee.setSubGradeCode(String.valueOf(tempArray[a][b].getNumericCellValue()));
                        }else if(tempArray[0][b].getStringCellValue().equals("EMPLOYEE_CODE")){
                            System.out.println("Employee Code="+tempArray[a][b].getStringCellValue());
                            employee.setEmployeeCode(String.valueOf(tempArray[a][b].getStringCellValue()));
                        }else if(tempArray[0][b].getStringCellValue().equals("EMPLOYEE_NAME")){
                            System.out.println("Employee Name="+tempArray[a][b].getStringCellValue());
                            employee.setEmployeeName(tempArray[a][b].getStringCellValue());
                        }else if(tempArray[0][b].getStringCellValue().contains("CREATED_BY")){
                            System.out.println("Created By="+tempArray[a][b].getStringCellValue());
                            employee.setCreatedBy(tempArray[a][b].getStringCellValue());
                        }

                    }
                }
                employees.add(employee);
            }
            // Insert to DB : end
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }

    private void copyToTemp(InputStream stream) {
        int rpt = 0;
        String pathTemp = "E:/Latihan/EMPLOYEETemp.xls";
        File f = new File(pathTemp);
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

                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                }
            }
        }

        emp.addAll(loadFileTemp());

    }

    public void onClick$btnSubmit(){

        SendJSON send = new SendJSON();
        for(Employee employee: emp){
            try {
                send.insertEmployee(employee);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        rw.setValue("");

    }

}
