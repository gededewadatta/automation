package admin.fe.controller;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.Dashboard;
import admin.fe.model.DumyModel;
import admin.fe.util.FileUtil;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import admin.fe.model.Stock;
import admin.fe.model.StockModel;

import java.io.IOException;
import java.util.*;

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

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);

        SendJSON send = new SendJSON();

        ddbLIst = send.getDashBoard();

        count = 1;
        modelList = new ListModelList(ddbLIst);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());

        List<String> comboList = new ArrayList<>();

        comboList.add("XLS");
        comboList.add("CSV");

        ListModel<String> comboModel =  new SimpleListModel<String>(comboList);
        idCombo.setModel(comboModel);
        idCombo.setSelectedIndex(0);

        idCombo.addEventListener(Events.ON_SELECT, new EventListener<SelectEvent>() {

            public void onEvent(SelectEvent event) {

                String filename = "Dashboard_Result"+ ".xls";
                if(FileUtil.GenerateExcelFile(0,"E:\\"+filename,1,FileUtil.generateExcelDashBoardHeader(),1,
                        FileUtil.generateExcelDashBoardBody(ddbLIst),1)){

                    Messagebox.show("Data Already Downloaded");

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
}
