package admin.fe.controller.Report.ReportEmployee;

/*
 * @Author FikriAsandhita
 *
 */

import admin.fe.controller.common.AbstractMainWindowTransaction;
import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PopupCallerDepartmentInterface;
import admin.fe.engine.PopupCallerDivisionInterface;
import admin.fe.engine.PopupCallerEmployeeInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.Departement;
import admin.fe.model.Division;
import admin.fe.model.Employee;
import admin.fe.model.Report;
import admin.fe.util.FileUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportEmployeeController extends CommonController implements PopupCallerEmployeeInterface {

    private Window reportEmployeeWindow;
    private AbstractMainWindowTransaction parent;

    protected Grid hGrid;
    protected ListModelList modelList;

    protected Listbox idCombo;

    Textbox idGrade;
    Textbox idEmployee;
    List<Report> reportList;

    Employee emp = new Employee();
    Report report = new Report();
    SendJSON send = new SendJSON();

    String filename = "ReportEmployee";
    String filenameprint = null;
    String pattern = "ddMMyyyy_HHmmss";
    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
    Date date = new Date();

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);

        List<String> comboList = new ArrayList<>();

        comboList.add("--Select--");
        comboList.add("XLS");
        comboList.add("PDF");

        ListModel<String> comboModel =  new SimpleListModel<String>(comboList);
        idCombo.setModel(comboModel);
        idCombo.setSelectedIndex(0);
    }

    public void onClick$generateButton() {
        String option = idCombo.getSelectedItem().getValue();

        if(option.equalsIgnoreCase("PDF")){
            createDirDestination();
            System.out.println("INI PDF ISINYA");

            filenameprint = null;
            filenameprint = filename+"_"+dateFormat.format(date)+".pdf";

            if(FileUtil.generatePDFFileReportEmployee("/"+Resources.destinationDownload +"/"+filenameprint,reportList)){
                try {
                    File file = new File("/"+Resources.destinationDownload+"/"+filenameprint);
                    FileInputStream inStream = new FileInputStream(file);
                    Filedownload.save(inStream, "application/pdf", filenameprint);
                } catch (IOException e){
                    e.printStackTrace();
                }
                Messagebox.show("Data Already Downloaded");
            }else{
                Messagebox.show("Data Failed to Download");
            }
        }else if (option.equalsIgnoreCase("XLS")){
            createDirDestination();
            System.out.println("INI EXCEL ISINYA");

            filenameprint = null;
            filenameprint = filename+"_"+dateFormat.format(date)+".xls";

            if(FileUtil.GenerateXLSFileReportEmployee(0,"/"+Resources.destinationDownload+"/"+filenameprint,1,FileUtil.generateHeaderReportEmployee(),1,
                    reportList,1,"Report Employee")){
                try {
                    File file = new File("/"+Resources.destinationDownload+"/"+filenameprint);
                    FileInputStream inStream = new FileInputStream(file);
                    Filedownload.save(inStream, "application/ms-excel", filenameprint);
                } catch (IOException e){
                    e.printStackTrace();
                }

                Messagebox.show("Data Already Downloaded");
            }else{
                Messagebox.show("Data Failed to Download");
            }
        }
    }

    public void onClick$searchButton(){
        if(report.getEmployeeCode() == null || report.getEmployeeCode().equals("")){
            report.setEmployeeCode("");
        }

        if(report.getGrade() == null|| report.getGrade().equals("")){
            report.setGrade("");
        }

        reportList = send.getReport(report);

        modelList = new ListModelList(reportList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());
    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Report)data);
            }

            private void renderDataRow(Row row, Report report){

                row.setValue(report);
                new Label(report.getEmployeeCode()).setParent(row);
                new Label(report.getGrade()).setParent(row);
                new Label(report.getCompetencies()).setParent(row);
                new Label(report.getMark()).setParent(row);
            }
        };
    }

    public void onClick$cancelButton(){
        idEmployee.setValue("");
        idGrade.setValue("");
    }

    public void onClick$btnEmployee(){
        Map<String, Object> args = new HashMap<String, Object>();
        Employee emp = new Employee();
        args.put("object", emp);
        args.put("caller", this);
        Component c = Executions.createComponents(
                Resources.employeePopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }
    }

    @Override
    public void afterSelectEmployee(Employee employee) {
        if(employee != null){
            emp = employee;
            idEmployee.setValue(emp.getEmployeeCode());
        }
    }

    public void createDirDestination(){
        String[] listdestinations = Resources.destinationDownload.split("/");
        String destinationCreate = "";
        for (String destinations: listdestinations){
            destinationCreate = destinationCreate+"/"+destinations;
            File file = new File(destinationCreate);
            System.out.println("Destination : "+Resources.destinationDownload);
            if(!file.exists()){
                if(file.mkdir()){
                    System.out.println(Resources.destinationDownload+" Destination directory successfull created");
                } else{
                    System.out.println("Failed created destination directory");
                }
            } else {
                System.out.println("Already Exist");
            }
        }
    }
}
