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

import org.hibernate.validator.constraints.URL;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.*;
import org.zkoss.zul.Textbox;

import java.io.*;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmployeeUploadController extends CommonController {

    protected Grid hGrid;
    protected ListModelList modelList;
    protected Textbox idUpload;
    org.zkoss.zul.Row rw;
    private Window employeeUpCont;
    private AbstractMainWindowTransaction parent;
    Button btnSubmit;

    int result = 0;

    Set<Employee> emp = new HashSet<>();

    String destination = "Apps/Upload";

    List<Employee> isEmpty = new ArrayList<>();

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        employeeUpCont = (Window) comp;

        System.out.println("employeeUpCont :" + employeeUpCont);
        createDirDestination();

        btnSubmit.setDisabled(true);
    }

    public void onUpload$browseButton(UploadEvent e){
        System.out.println("Test Mesia :"+e.getMedias());
        btnSubmit.setDisabled(false);
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

    public void loadFileTemp(String extension) {

        SimpleDateFormat format = new SimpleDateFormat("DDMMYYYY");
        String dateString = format.format( new Date()   );
        String path = "/Apps/Upload/EMPLOYEETemp"+dateString+".xls";
        Set<Employee> employees = new HashSet<>();
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
            if(sheet.getRow(0).getCell(0)!=null){
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(0);
                DataFormatter formatter = new DataFormatter();
//			Row row;
                Cell[][] tempArray = new Cell[sheet.getLastRowNum()][row.getLastCellNum()];
                int lastColNum = row.getLastCellNum();
                //Extract Data : start
                int rowCount = 0;
                for(int i = 0; i < sheet.getLastRowNum(); i++){
                    if(sheet.getRow(rowCount)!=null){
                        row = sheet.getRow(rowCount);
                        for(int j = 0; j < lastColNum; j++){
                            tempArray[i][j] = row.getCell(j);
                        }
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
                            if(sheet.getRow(a)!=null){
                                if(tempArray[0][b].getStringCellValue().equals("DEPARTEMENT_CODE")){
                                    val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                    if(!val.equals("") || val != null){
                                        employee.setDepartementCode(val);
                                    }else{
                                        isEmpty.add(employee);
                                    }
                                }else if(tempArray[0][b].getStringCellValue().equals("GRADE_CODE")){
                                    val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                    if(!val.equals("")|| val != null) {
                                        employee.setGradeCode(val);
                                    }else{
                                        isEmpty.add(employee);
                                    }
                                }else if(tempArray[0][b].getStringCellValue().equals("DIVISION_CODE")){
                                    val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                    if(!val.equals("")|| val != null){
                                        employee.setDivisionCode(val);
                                    }else{
                                        isEmpty.add(employee);
                                    }

                                }else if(tempArray[0][b].getStringCellValue().equals("SUB_GRADE_CODE")){
                                    val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                    if(!val.equals("")|| val != null){
                                        employee.setSubGradeCode(val);
                                    }else{
                                        isEmpty.add(employee);
                                    }

                                }else if(tempArray[0][b].getStringCellValue().equals("EMPLOYEE_CODE")){
                                    val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                    if(!val.equals("")|| val != null){
                                        employee.setEmployeeCode(val);
                                    }else{
                                        isEmpty.add(employee);
                                    }

                                }else if(tempArray[0][b].getStringCellValue().equals("EMPLOYEE_NAME")){
                                    val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                    if(!val.equals("")|| val != null){
                                        employee.setEmployeeName(val);
                                    }else{
                                        isEmpty.add(employee);
                                    }

                                }else if(tempArray[0][b].getStringCellValue().contains("CREATED_BY")){
                                    val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                    if(!val.equals("")|| val != null){
                                        employee.setCreatedBy(val);
                                    }else{
                                        isEmpty.add(employee);
                                    }

                                }else if(tempArray[0][b].getStringCellValue().contains("USER_NAME")){
                                    val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                    if(!val.equals("")|| val != null){
                                        employee.setUserName(val);
                                    }else{
                                        isEmpty.add(employee);
                                    }

                                }
                            }
                        }
                    }
                    if(isEmpty.size() > 0){
                        result = 0;
                    }else{
                        emp.add(employee);
                    }

                }
            }else{

                result = -1;
                Messagebox.show("Header of file is empty!");
                idUpload.setValue("");
                btnSubmit.setDisabled(true);

            }

            // Insert to DB : end
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void copyToTemp(Media media) {
        int rpt = 0;
//        path for temporary
        SimpleDateFormat format = new SimpleDateFormat("DDMMYYYY");
        String dateString = format.format( new Date()   );
        String pathTemp = "/Apps/Upload/EMPLOYEETemp"+dateString+".xls";
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
       loadFileTemp(extensinos);

    }

    public void onClick$btnSubmit(){

        showConfirmDialog("Do you want to save data?");

    }

    public void showConfirmDialog(String message) {
//        Insert into db
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

                                    if(result > 0 ){

                                        Set<Employee> isInserted = new HashSet<>();
                                        for(Employee employee: emp){
                                            if(employee!=null){
                                                if(send.insertEmployee(employee).equals("200")){
                                                    isInserted.add(employee);
                                                }
                                            }
                                        }

                                        if(isInserted.size() > 0){
                                            Messagebox.show("Data Success to Save");
                                            isInserted.clear();
                                            idUpload.setValue("");
                                        }else{
                                            Messagebox.show("Data Already Exists");
                                            isInserted.clear();
                                            idUpload.setValue("");
                                        }

                                    }else if(result == 0){

                                        Messagebox.show("There is some field still empty");
                                        idUpload.setValue("");
                                        isEmpty.clear();
                                    }

                                    btnSubmit.setDisabled(true);
                                    emp.clear();
                                    hGrid.removeChild(hGrid.getRows());

                                } catch (JsonProcessingException e) {
                                    Messagebox.show("Data failed to Save");
                                }

                        }
                    }
                });
    }

    public void createDirDestination(){
        String[] listdestinations = destination.split("/");
        String destinationCreate = "";
        for (String destinations: listdestinations){
            destinationCreate = destinationCreate+"/"+destinations;
            File file = new File(destinationCreate);
            System.out.println("Destination : "+destination);
            if(!file.exists()){
                if(file.mkdir()){
                    System.out.println(destination+" Destination directory successfull created");
                } else{
                    System.out.println("Failed created destination directory");
                }
            } else {
                System.out.println("Already Exist");
            }
        }
    }

}
