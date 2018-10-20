package admin.fe.controller.Maintenance.Competencies;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.*;
import admin.fe.model.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetenciesController extends CommonController implements PopupCallerDepartmentInterface,PopupCallerGradeInterface,PopupCallerSubGradeInterface {

    Textbox idDepartment;
    Textbox idGrade;
    Textbox idSubGrade;

    Competency comp = new Competency();
    SendJSON send = new SendJSON();
    protected Grid hGrid;
    protected ListModelList modelList;
    Division div = new Division();
    Departement dep = new Departement();
    List<Competency> competencies = new ArrayList<>();
    Grade grd = new Grade();
    SubGrade subGrd = new SubGrade();

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$addButton(){
        System.out.println("Ini button Submit");
        navigateTo(Resources.competenciesDetail,null,self);
    }

    public void onClick$searchButton(){

        if(idGrade.getValue()!=null||!idGrade.getValue().equals("")){
            comp.setGradeCode(idGrade.getValue());
        }else {
            comp.setGradeCode("");
        }
        if(idSubGrade.getValue()!=null||!idSubGrade.getValue().equals("")){
            comp.setSubGradeCode(idSubGrade.getValue());
        }else {
            comp.setSubGradeCode("");
        }
        if(idDepartment.getValue()!=null||!idDepartment.getValue().equals("")){
            comp.setDepartementCode(idDepartment.getValue());
        }else {
            comp.setDepartementCode("");
        }

        competencies = send.getCompetencyByGradeCode(comp);

        modelList = new ListModelList(competencies);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());


    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Competency)data);
            }

            private void renderDataRow(Row row, Competency competency){

                row.setValue(competency);
                new Label(competency.getDepartementCode()).setParent(row);
                new Label(competency.getGradeCode()).setParent(row);
                new Label(competency.getSubGradeCode()).setParent(row);
                new Label(competency.getCompetencyName()).setParent(row);

                Hbox hbox1 = new Hbox();

                Button view = new Button("View");
                Button edit = new Button("Edit");

                view.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            /**
                             *
                             */
                            private static final long serialVersionUID = 2726307190666012319L;

                            public void onEvent(Event event) throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                    navigateTo(Resources.competenciesViewEdit,getArg(competency,"VIEW"),self);

                                }
                            }
                        });
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
                                    navigateTo(Resources.competenciesViewEdit,getArg(competency,"EDIT"),self);
                                }
                            }
                        });

                Separator separator = new Separator("vertical");
                separator.setWidth("10px");

                hbox1.appendChild(view);
                separator.setParent(hbox1);
                hbox1.appendChild(edit);
                row.appendChild(hbox1);
            }
        };
    }

    public Map<String, Object> getArg(Competency obj,String type) {

        Map<String, Object> args = new HashMap<String, Object>();

        args.put("id",obj.getId());
        args.put("subGradeCode",obj.getSubGradeCode());
        args.put("competencyCode",obj.getCompetencyCode());
        args.put("competencyName",obj.getCompetencyName());
        args.put("gradeCode",obj.getGradeCode());
        args.put("departmentCode",obj.getDepartementCode());
        args.put("competencyCode",obj.getCompetencyCode());
        args.put("createdBy",obj.getCreatedBy());
        args.put("type",type);
        return args;
    }

    public void onClick$btnDepartement(){

        Map<String, Object> args = new HashMap<String, Object>();
        Departement departement = new Departement();
        args.put("object", departement);
        args.put("division", div);
        args.put("caller", this);
        Component c = Executions.createComponents(Resources.departementPopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }

    @Override
    public void afterSelectDepartement(Departement departement) {
        if(departement != null){
            dep = departement;
            idDepartment.setValue(departement.getDepartementCode());
        }
    }

    public void onClick$btnGrade(){

        Map<String, Object> args = new HashMap<String, Object>();
        Grade grade = new Grade();
        args.put("objectGrade", grade);
        args.put("departement", dep);
        args.put("caller", this);
        Component c = Executions.createComponents(Resources.gradePopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }
    public void onClick$btnSubGrade(){

        Map<String, Object> args = new HashMap<String, Object>();
        SubGrade subgrd = new SubGrade();
        args.put("object", subgrd);
        args.put("grade",grd);
        args.put("caller", this);
        Component c = Executions.createComponents(Resources.subgradeopup, self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
        }

    }

    public void afterSelectGrade(Grade grade) {
        if(grade != null){
            grd = grade;
            idGrade.setValue(grd.getGradeCode());
        }

    }

    public void afterSelectSubGrade(SubGrade subGrade) {

        if(subGrade != null){
            subGrd = subGrade;
            idSubGrade.setValue(subGrd.getSubGradeCode());
        }
    }


}
