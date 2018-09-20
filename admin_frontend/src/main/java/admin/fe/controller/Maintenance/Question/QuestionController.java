package admin.fe.controller.Maintenance.Question;

import admin.fe.controller.common.CommonController;
import admin.fe.engine.*;
import admin.fe.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QuestionController extends CommonController implements PopupCallerDepartmentInterface,PopupCallerDivisionInterface,
        PopupCallerGradeInterface,PopupCallerSubGradeInterface {

    @Wire
    Textbox idDivision;

    @Wire
    Textbox idDepartment;

    @Wire
    Textbox idGrade;

    @Wire
    Textbox idSubGrade;

    @Wire
    Textbox idCompetencies;

    @Wire
    Textbox idQuestion;

    @Wire
    Textbox idAnswer;

    @Wire
    Textbox idAnswer1;

    @Wire
    Textbox idAnswer2;

    @Wire
    Textbox idAnswer3;

    @Wire
    Textbox idAnswer4;

    @Wire
    Textbox idValidAns;

    Division div = new Division();

    Departement dep = new Departement();

    Grade grd = new Grade();

    SubGrade subGrd = new SubGrade();

    @Value("${led.question.insert}")
    protected String questionInsert;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
    }

    public void onClick$submitButton(){
        System.out.println("Ini Fucking Submit");

        Question quest = new Question();

        SendJSON send = new SendJSON();

        System.out.println("Ini Fucking Submit2");
        quest.setGrade(idGrade.getValue());
        quest.setSubGrade(idSubGrade.getValue());
        quest.setCreatedBy("Burhan");
        quest.setCreatedDate(new Date());
        quest.setQuestions(idQuestion.getValue());
        quest.setAnswer1(idAnswer.getValue());
        quest.setAnswer2(idAnswer1.getValue());
        quest.setAnswer3(idAnswer2.getValue());
        quest.setAnswer4(idAnswer3.getValue());
        quest.setAnswer5(idAnswer4.getValue());
        quest.setCompetency(idCompetencies.getValue());
        quest.setCorrectAnswer(idValidAns.getValue());

        try {
            String result = send.insertQuestion(quest);

            if(result.equals("200")){
                Messagebox.show("Data Already Saved");
            }else{
                Messagebox.show("Data Failed To save");
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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

    public void onClick$btnGrade(){

        Map<String, Object> args = new HashMap<String, Object>();
        Grade grade = new Grade();
        args.put("objectGrade", grade);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Grade/GradePopup.zul", self, args);
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
        SubGrade div = new SubGrade();
        args.put("object", div);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Grade/SubGradePopup.zul", self, args);
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
            idDivision.setValue(division.getDivisionCode());
        }

    }

    @Override
    public void afterSelectDepartement(Departement departement) {
        if(departement != null){
            dep = departement;
            idDepartment.setValue(departement.getDepartementCode());
        }
    }

    @Override
    public void afterSelectGrade(Grade grade) {
        if(grade != null){
            grd = grade;
            idGrade.setValue(grd.getGradeCode());
        }

    }

    @Override
    public void afterSelectSubGrade(SubGrade subGrade) {

        if(subGrade != null){
            subGrd = subGrade;
            idSubGrade.setValue(subGrd.getSubGradeCode());
        }
    }




}
