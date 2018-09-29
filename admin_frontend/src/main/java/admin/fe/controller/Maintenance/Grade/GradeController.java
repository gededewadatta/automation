package admin.fe.controller.Maintenance.Grade;

import admin.fe.controller.common.AbstractMainWindowTransaction;
import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.PopupCallerDepartmentInterface;
import admin.fe.engine.PopupCallerDivisionInterface;
import admin.fe.engine.SendJSON;
import admin.fe.model.*;
import org.apache.poi.sl.usermodel.TextBox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zul.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeController extends CommonController implements PopupCallerDivisionInterface,PopupCallerDepartmentInterface {

    private Window gradeCont;
    private AbstractMainWindowTransaction parent;

    protected Grid hGrid;
    protected ListModelList modelList;
    List<GradeJson> gradeList;

    Textbox idCompanyName;
    Textbox idDivision;
    Textbox idDepartement;

    Division div = new Division();
    Departement dep = new Departement();

    SendJSON send = new SendJSON();
    GradeJson grd = new GradeJson();

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$addButton(){
        System.out.println("Ini button Submit");
        navigateTo("layout/Grade/GradeDetail.zul",null,self);

    }

    public void onClick$searchButton(){

        if(div.getDivisionCode() == null||div.getDivisionCode().equals("")){
            grd.setDivisionCode("");
        }else{
            grd.setDivisionCode(div.getDivisionCode());
        }

        if(dep.getDepartementCode() == null||dep.getDepartementCode().equals("")){
            grd.setDepartementCode("");
        }else{
            grd.setDepartementCode(dep.getDepartementCode());
        }

        gradeList = send.getGradeJson(grd);

        modelList = new ListModelList(gradeList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());


    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(GradeJson)data);
            }

            private void renderDataRow(Row row, GradeJson gradeJson){

                row.setValue(gradeJson);
                new Label(gradeJson.getDivisionCode()).setParent(row);
                new Label(gradeJson.getDepartementCode()).setParent(row);
                new Label(gradeJson.getGradeCode()).setParent(row);
                new Label(gradeJson.getSubGradeCode()).setParent(row);


                Hbox hbox1 = new Hbox();

                Button view = new Button("View");
                view.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            /**
                             *
                             */
                            private static final long serialVersionUID = 2726307190666012319L;

                            public void onEvent(Event event) throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                        navigateTo("layout/Grade/GradeView.zul",getArg(gradeJson),gradeCont);
//                                            getArg(InvestmentModelObj),
//                                            winBancaFinTransactionSelection);
                                }
                            }
                        });
                hbox1.appendChild(view);

                Separator separator = new Separator("vertical");
                separator.setWidth("10px");
                separator.setParent(hbox1);

                Button edit = new Button("Edit");
                edit.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            /**
                             *
                             */
                            private static final long serialVersionUID = 5680895575895846548L;

                            public void onEvent(Event event)
                                    throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                    navigateTo("layout/Grade/GradeEdit.zul",getArg(gradeJson),gradeCont);
                                }
                            }
                        });
                hbox1.appendChild(edit);
                row.appendChild(hbox1);
            }
        };
    }

    public Map<String, Object> getArg(GradeJson obj) {

        Map<String, Object> args = new HashMap<String, Object>();

        args.put("idGrade", obj.getIdGrade());
        args.put("idSubGrade", obj.getIdSubGrade());
        args.put("divisionCode",obj.getDivisionCode());
        args.put("departementCode",obj.getDepartementCode());
        args.put("gradeCode",obj.getGradeCode());
        args.put("subGradeCode",obj.getSubGradeCode());
        args.put("gradeName",obj.getGradeName());
        args.put("subGradeName",obj.getSubGradeName());

        return args;
    }

    public void onClick$btnDepartment(){

        Map<String, Object> args = new HashMap<String, Object>();
        Departement departement = new Departement();
        args.put("object", departement);
        args.put("division", div);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Departement/DeptPopup.zul", self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }
    public void onClick$btnDivision(){

        Map<String, Object> args = new HashMap<String, Object>();
        Division div = new Division();
        args.put("object", div);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Division/DivisionPopup.zul", self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }

    @Override
    public void afterSelectDivision(Division division) {

        if(division != null){
            div = division;
            idDivision.setValue(division.getDivisionName());
        }

    }

    @Override
    public void afterSelectDepartement(Departement departement) {
        if(departement != null){
            dep = departement;
            idDepartement.setValue(departement.getDepartementName());
        }
    }
}
