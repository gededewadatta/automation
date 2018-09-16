package admin.fe.controller.FileUpload.Employee;

import admin.fe.controller.common.AbstractMainWindowTransaction;
import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.TextBox;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.*;
import org.zkoss.zul.Textbox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeUploadController extends CommonController {

    protected Grid hGrid;
    protected ListModelList modelList;
    protected Textbox idUpload;
    org.zkoss.zul.Row rw;
    private Window employeeUpCont;
    private AbstractMainWindowTransaction parent;


    List<Employee> emp = new ArrayList<>();



    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        employeeUpCont = (Window) comp;

        System.out.println("employeeUpCont :" + employeeUpCont);
    }

    public void onUpload$browseButton(UploadEvent e){
        System.out.println("Test Mesia :"+e.getMedias());
        doValidate(e.getMedias());
    }

    public void doValidate(Media[] multiMedia) {

        for (Media med : multiMedia) {

            idUpload.setValue(med.getName());
            copyToTemp(med);
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

    public List<Employee> loadFileTemp(String extension) {
        int result = 0;

        List<String> dataTemp = new ArrayList<String>();
        String path = "E:/Latihan/EMPLOYEETemp.xls";
        List<Employee> employees = new ArrayList<>();
        Employee employee;
        try{

            Cell cell;
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            Workbook wb = null;
            if(extension.equals("xls")){
                wb = new XSSFWorkbook(fis);

            }else{
                wb = new HSSFWorkbook(fis);
            }
            Sheet sheet = wb.getSheetAt(0);
            org.apache.poi.ss.usermodel.Row row = sheet.getRow(0);
            DataFormatter formatter = new DataFormatter();
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
            String val ="";
            for(int a = 1; a < sheet.getLastRowNum() + 1; a++){
                employee = new Employee();
                for(int b = 0; b < tempArray[0].length; b++){
                    if(a != 0){
                        if(tempArray[0][b].getStringCellValue().equals("DEPARTEMENT_CODE")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            employee.setDepartementCode(val);
                        }else if(tempArray[0][b].getStringCellValue().equals("GRADE_CODE")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            employee.setGradeCode(val);
                        }else if(tempArray[0][b].getStringCellValue().equals("DIVISION_CODE")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            employee.setDivisionCode(val);
                        }else if(tempArray[0][b].getStringCellValue().equals("SUB_GRADE_CODE")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            employee.setSubGradeCode(val);
                        }else if(tempArray[0][b].getStringCellValue().equals("EMPLOYEE_CODE")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            employee.setEmployeeCode(val);
                        }else if(tempArray[0][b].getStringCellValue().equals("EMPLOYEE_NAME")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            employee.setEmployeeName(val);
                        }else if(tempArray[0][b].getStringCellValue().contains("CREATED_BY")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            employee.setCreatedBy(val);
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

    private void copyToTemp(Media media) {
        int rpt = 0;
        String pathTemp = "E:/Latihan/"+media.getName();
        File f = new File(pathTemp);
        InputStream stream = media.getStreamData();
        OutputStream os = null;
        String extensinos = media.getName().substring(media.getName().lastIndexOf("."),media.getName().length());
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

        emp.addAll(loadFileTemp(extensinos));

    }

    public void onClick$btnSubmit(){

        showConfirmDialog("Do you want to save data?");

    }

    public void showConfirmDialog(String message) {
        Messagebox.show(message, "confirm",
                Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                Messagebox.NO, new SerializableEventListener() {

                    private static final long serialVersionUID = -8695776168749565854L;

                    @Override
                    public void onEvent(Event event) throws Exception {
                        int data = (Integer) event.getData();
                        switch (data) {
                            case Messagebox.YES:

                                SendJSON send = new SendJSON();
                                try {
                                    for(Employee employee: emp){
                                        send.insertEmployee(employee);
                                    }
                                    emp.clear();
                                    Messagebox.show("Data Success to Save");
                                } catch (JsonProcessingException e) {
                                    Messagebox.show("Data failed to Save");
                                }

                        }
                    }
                });
    }

}
