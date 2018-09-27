package admin.fe.controller.Maintenance.Question;

import admin.fe.constant.QuestionType;
import admin.fe.controller.common.CommonController;
import admin.fe.engine.*;
import admin.fe.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.poi.sl.usermodel.TextBox;
import org.springframework.beans.factory.annotation.Value;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;
import org.zkoss.zul.Window;

import java.awt.*;
import java.util.*;
import java.util.List;

public class QuestionController extends CommonController implements PopupCallerDepartmentInterface,PopupCallerDivisionInterface,
        PopupCallerGradeInterface,PopupCallerSubGradeInterface,PopupCallerCompetencyInterface {

    Textbox idDivision;

    Textbox idDepartment;

    Textbox idGrade;

    Textbox idSubGrade;

    Textbox idCompetencyCode;

    Textbox idCompetencyName;

    Textbox idQuestion;

    Textbox idValidAns;

    Textbox idTextAnswer;

    int i=0;

    Vbox boxAnswer;

    Row btnAnswer;

    Row textAnswer;

    Listbox idQuestionType;

    Division div = new Division();

    Departement dep = new Departement();

    Grade grd = new Grade();

    SubGrade subGrd = new SubGrade();

    Competency competency = new Competency();

    List<Textbox> textboxList= new ArrayList<>();

    Question quest = new Question();

    SendJSON send = new SendJSON();



    @Value("${led.question.insert}")
    protected String questionInsert;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        btnAnswer.setVisible(false);
        textAnswer.setVisible(false);

        idQuestionType.appendItem(QuestionType.YESNO.getValue(),QuestionType.YESNO.getCode());
        idQuestionType.appendItem(QuestionType.FREETEXT.getValue(),QuestionType.FREETEXT.getCode());
        idQuestionType.appendItem(QuestionType.OPTIONAL.getValue(),QuestionType.OPTIONAL.getCode());
    }

    public void setAnswer(){
        if(idQuestionType.getSelectedItem().getValue().equals("0")){
            quest.setAnswer1(textboxList.get(0).getValue());
            quest.setAnswer2(textboxList.get(1).getValue());
            quest.setAnswer3("");
            quest.setAnswer4("");
            quest.setAnswer5("");
        }else if(idQuestionType.getSelectedItem().getValue().equals("1")){
            quest.setAnswer1(idTextAnswer.getValue());
        }else{
            quest.setAnswer1(textboxList.get(0).getValue());
            quest.setAnswer2(textboxList.get(1).getValue());
            quest.setAnswer3(textboxList.get(2).getValue());
            quest.setAnswer4(textboxList.get(3).getValue());
            quest.setAnswer5("");
        }
    }

    public void onClick$submitButton(){
        System.out.println("Ini Fucking Submit");

        quest.setGrade(idGrade.getValue());
        quest.setSubGrade(idSubGrade.getValue());
        quest.setCreatedBy("Burhan");
        quest.setCreatedDate(new Date());
        quest.setQuestions(idQuestion.getValue());
        quest.setCompetency(idCompetencyName.getValue());
        quest.setCorrectAnswer(idValidAns.getValue());
        setAnswer();
        try {
            String result = send.insertQuestion(quest);
            clearAnswer();
            textboxList.clear();
            idTextAnswer.setValue("");
            clearObject();
            if(result.equals("200")){
                Messagebox.show("Data Already Saved");
            }else{
                Messagebox.show("Data Failed To save");
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void onClick$btnCompetencyCode(){
        Map<String, Object> args = new HashMap<String, Object>();
        Competency competency = new Competency();
        args.put("object", competency);
        args.put("caller", this);
        Component c = Executions.createComponents(
                "layout/Competencies/CompetencyPopUp.zul", self, args);
        try {
            onModalToTop((Window) c);
        } catch (SuspendNotAllowedException e1) {
            Messagebox.show(e1.getMessage());
        } catch (InterruptedException e1) {
            Messagebox.show(e1.getMessage());
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

    @Override
    public void afterSelectCompetencies(Competency competency) {

        if(competency != null){
            this.competency = competency;
            idCompetencyCode.setValue(this.competency.getCompetencyCode());
            idCompetencyName.setValue(this.competency.getCompetencyName());
        }

    }

    public void onSelect$idQuestionType(){
        String value = idQuestionType.getSelectedItem().getValue();
        if(value.equals("1")){
            textAnswer.setVisible(true);
            btnAnswer.setVisible(false);
            boxAnswer.setVisible(false);
            textboxList.clear();
        }else{
            textAnswer.setVisible(false);
            btnAnswer.setVisible(true);
            boxAnswer.setVisible(true);
        }

    }


    public void onClick$add(){

        Textbox textBoxAnswer = new Textbox();
        String value = idQuestionType.getSelectedItem().getValue();
        if(value.equals("0")){
            if(i <2){
                textBoxAnswer.setId("textBoxAnswer"+i);
                textBoxAnswer.setParent(boxAnswer);
                i++;
                System.out.println("count textBox add = "+i);
            }

            if(textBoxAnswer.getId().equals("textBoxAnswer0")){
                textBoxAnswer.setValue("yes");
                textBoxAnswer.setDisabled(true);
            }

            if(textBoxAnswer.getId().equals("textBoxAnswer1")){
                textBoxAnswer.setValue("no");
                textBoxAnswer.setDisabled(true);
            }
        }else if(value.equals("2")){
            if(i<4){
                textBoxAnswer.setId("textBoxAnswer"+i);
                textBoxAnswer.setParent(boxAnswer);
                i++;
                System.out.println("count textBox add = "+i);
            }
        }


        textboxList.add(textBoxAnswer);

    }

    public void onClick$delete(){clearAnswer();}

    public void  clearAnswer(){
        for(int a=0;a<textboxList.size();a++){
            boxAnswer.removeChild(textboxList.get(a));
            if(i>0){
                i=i-1;
            }

            System.out.println("count textBox delete = "+i);
        }
    }

    public void clearObject(){

        quest.setAnswer1("");
        quest.setAnswer2("");
        quest.setAnswer3("");
        quest.setAnswer4("");
        quest.setAnswer5("");

    }



}
