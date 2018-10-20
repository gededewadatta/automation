package admin.fe.controller;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.Dashboard;
import admin.fe.util.FileUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zul.*;

import admin.fe.model.StockModel;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/*

Author Muhammad Burhanudin

 */
public class DashboardController extends CommonController {

    protected ListModelList modelList;
    Dashboard dashboard;
    protected Grid hGrid;
    protected Listbox idCombo;
    private int count;
    List<Dashboard> ddbLIst;
	
	private static final long serialVersionUID = 1L;
    
    final StockModel stockData = new StockModel();

    String filename = "DashboardResults";
    String filenameprint = null;
    String pattern = "ddMMyyyy_HHmmss";
    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
    Date date = new Date();

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);

        createDirDestination();

        SendJSON send = new SendJSON();

        ddbLIst = send.getDashBoard();

        count = 1;
        modelList = new ListModelList(ddbLIst);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());

        List<String> comboList = new ArrayList<>();

        comboList.add("--Select--");
        comboList.add("XLS");
        comboList.add("PDF");

        ListModel<String> comboModel =  new SimpleListModel<String>(comboList);
        idCombo.setModel(comboModel);
        idCombo.setSelectedIndex(0);

        idCombo.addEventListener(Events.ON_SELECT, new EventListener<SelectEvent>() {

            public void onEvent(SelectEvent event) {

                if(event.getSelectedObjects().contains("PDF")) {
                    System.out.println("INI PDF ISINYA");

                    filenameprint = null;
                    filenameprint = filename+"_"+dateFormat.format(date)+".pdf";

                    if(FileUtil.generatePDFFile("/"+Resources.destinationDownload +"/"+filenameprint,ddbLIst)){
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

                }else if (event.getSelectedObjects().contains("XLS")) {
                    System.out.println("INI EXCEL ISINYA");

                    filenameprint = null;
                    filenameprint = filename+"_"+dateFormat.format(date)+".xls";

                    if(FileUtil.GenerateXLSFile(0,"/"+Resources.destinationDownload+"/"+filenameprint,1,FileUtil.generateHeader(),1,
                            ddbLIst,1,"Dashboard Report")){
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
        });
    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Dashboard)data);
            }

            private void renderDataRow(Row row, Dashboard dashboard){

                row.setValue(dashboard);
                new Label(dashboard.getEmployeeId()).setParent(row);
                new Label(dashboard.getGrade()).setParent(row);
                new Label(dashboard.getResult()).setParent(row);

            }
        };
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
