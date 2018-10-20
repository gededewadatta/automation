package admin.fe.controller.Maintenance.Question;

import admin.fe.constant.QuestionType;
import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import admin.fe.engine.*;
import admin.fe.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.springframework.beans.factory.annotation.Value;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;


import java.util.*;

public class QuestionViewEdit extends CommonController implements PopupCallerDepartmentInterface,
        PopupCallerGradeInterface,PopupCallerSubGradeInterface,PopupCallerCompetencyInterface {

    Textbox idDepartment;
    Textbox idGrade;
    Textbox idSubGrade;
    Textbox idCompetencyCode;
    Textbox idLevel;
    Textbox idQuestion;
    Textbox idValidAns;
    Textbox idTextAnswer;
    Textbox idQuestionCode;
    Textbox idQuestionId;

    Label lblDivision;
    Label lblDepartment;
    Label lblGrade;
    Label lblSubGrade;
    Label lblCompetencyCode;
    Label lblLevel;
    Label lblQuestion;
    Label lblValidAns;
    Label lblTextAnswer;
    Label lblQuestionType;
    Label answer1;
    Label answer2;
    Label answer3;
    Label answer4;
    Label answer5;
    Label lblQuestionCode;


    Button backButton;
    Button submitButton;
    Button clearButton;
    Button btnDepartment;
    Button btnGrade;
    Button btnSubGrade;
    Button btnCompetencyCode;

    SendJSON send = new SendJSON();

    int i = 0;

    Vbox boxAnswer;
    Row btnAnswer;
    Row textAnswer;
    Listbox idQuestionType;
    Departement dep = new Departement();
    Grade grd = new Grade();
    SubGrade subGrd = new SubGrade();
    Competency competence = new Competency();
    List<Textbox> textboxList= new ArrayList<>();
    Question quest = new Question();

    EventListener event = null;

    List<String> answer = new ArrayList<>();
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        idQuestionType.appendItem(QuestionType.YESNO.getValue(),QuestionType.YESNO.getCode());
        idQuestionType.appendItem(QuestionType.FREETEXT.getValue(),QuestionType.FREETEXT.getCode());
        idQuestionType.appendItem(QuestionType.OPTIONAL.getValue(),QuestionType.OPTIONAL.getCode());
        disableComponent();

        getAllData();
        addValueToComponent();
    }

    public void getAllData() {
        quest.setQuestions((String) arg.get("questions"));
        quest.setQuestionCode((String) arg.get("questionCode"));
        quest.setLevel((String) arg.get("level"));
        quest.setGrade((String) arg.get("grade"));
        quest.setSubGrade((String) arg.get("subGrade"));
        quest.setCompetency((String) arg.get("competency"));
        quest.setCorrectAnswer((String) arg.get("correctAnswer"));
        quest.setQuestionType((String)arg.get("questionType"));
        quest.setDepartementCode((String)arg.get("departementCode"));
        quest.setQuestionCode((String)arg.get("questionCode"));
        quest.setAnswer1((String)arg.get("answer1"));
        quest.setAnswer2((String)arg.get("answer2"));
        quest.setAnswer3((String)arg.get("answer3"));
        quest.setAnswer4((String)arg.get("answer4"));
        quest.setAnswer5((String)arg.get("answer5"));
    }

    public void addValueToComponent(){

        if((String) arg.get("type") == "VIEW"){

            lblDepartment.setValue(quest.getDepartementCode());
            lblGrade.setValue(quest.getGrade());
            lblSubGrade.setValue(quest.getSubGrade());
            lblCompetencyCode.setValue(quest.getCompetency());
            lblLevel.setValue(quest.getLevel());
            lblQuestion.setValue(quest.getQuestions());
            lblValidAns.setValue(quest.getCorrectAnswer());
            lblQuestionType.setValue(quest.getQuestionType());
            lblQuestionCode.setValue(quest.getQuestionCode());
            lblAnswer();

        }else{

            idQuestionId.setValue(String.valueOf(quest.getId()));
            idDepartment.setValue(quest.getDepartementCode());
            idGrade.setValue(quest.getGrade());
            idSubGrade.setValue(quest.getSubGrade());
            idCompetencyCode.setValue(quest.getCompetency());
            idLevel.setValue(quest.getLevel());
            idQuestion.setValue(quest.getQuestions());
            idValidAns.setValue(quest.getCorrectAnswer());
            idQuestionCode.setValue(quest.getQuestionCode());
            isSelectedCombobox();
            txtAnswer();
        }

    }

    public void txtAnswer(){
        Textbox textBoxAnswer;

        if(quest.getQuestionType().equals("YES/NO")){

            answer.add(quest.getAnswer1());
            answer.add(quest.getAnswer2());

            i = answer.size();

            for(int a=0;a<answer.size();a++){
                textBoxAnswer = new Textbox();
                textBoxAnswer.setParent(boxAnswer);
                textBoxAnswer.setValue(answer.get(a));
                textBoxAnswer.setDisabled(true);
                textboxList.add(textBoxAnswer);
            }

            textAnswer.setVisible(false);

            answer.clear();

        }else if(quest.getQuestionType().equals("FREETEXT")){
            textAnswer.setValue(quest.getAnswer1());
            textAnswer.setVisible(true);
        }else{
            answer.add(quest.getAnswer1());
            answer.add(quest.getAnswer2());
            answer.add(quest.getAnswer3());
            answer.add(quest.getAnswer4());
            answer.add(quest.getAnswer5());
            textAnswer.setVisible(false);
            i = answer.size();

            for(int a=0;a<i;a++){
                textBoxAnswer = new Textbox();
                textBoxAnswer.setParent(boxAnswer);
                textBoxAnswer.setValue(answer.get(a));
                textboxList.add(textBoxAnswer);
            }

            answer.clear();
        }
    }

    public void updatedData() {
        quest.setQuestions(idQuestion.getValue());
        quest.setQuestionCode(idQuestionCode.getValue());
        quest.setLevel(idLevel.getValue());
        quest.setGrade(idGrade.getValue());
        quest.setSubGrade(idSubGrade.getValue());
        quest.setCompetency(idCompetencyCode.getValue());
        quest.setCorrectAnswer(idValidAns.getValue());
        quest.setQuestionType(idQuestionType.getSelectedItem().getLabel());
        quest.setDepartementCode(idDepartment.getValue());
        quest.setAnswer1(textboxList.get(0).getValue());
        quest.setAnswer2(textboxList.get(1).getValue());
        quest.setAnswer3(textboxList.get(2).getValue());
        quest.setAnswer4(textboxList.get(3).getValue());
        quest.setAnswer5(textboxList.get(4).getValue());
    }

    public void lblAnswer(){
        if(quest.getQuestionType().equals("YES/NO")){

            answer1.setValue(quest.getAnswer1());
            answer2.setValue(quest.getAnswer2());

        }else if(quest.getQuestionType().equals("FREETEXT")){
            lblTextAnswer.setValue(quest.getAnswer1());
        }else{
            answer1.setValue(quest.getAnswer1());
            answer2.setValue(quest.getAnswer2());
            answer3.setValue(quest.getAnswer3());
            answer4.setValue(quest.getAnswer4());
            answer5.setValue(quest.getAnswer5());

        }
    }

    public void disableComponent() {
        if((String) arg.get("type") == "VIEW"){
            idDepartment.setVisible(false);
            idGrade.setVisible(false);
            idSubGrade.setVisible(false);
            idCompetencyCode.setVisible(false);
            idLevel.setVisible(false);
            idQuestion.setVisible(false);
            idValidAns.setVisible(false);
            idTextAnswer.setVisible(false);
            idQuestionCode.setVisible(false);
            submitButton.setVisible(false);
            clearButton.setVisible(false);
            btnDepartment.setVisible(false);
            idQuestionType.setVisible(false);
            answer1.setVisible(true);
            answer2.setVisible(true);
            answer3.setVisible(true);
            answer4.setVisible(true);
            answer5.setVisible(true);
            btnGrade.setVisible(false);
            btnCompetencyCode.setVisible(false);
            btnSubGrade.setVisible(false);
            btnAnswer.setVisible(false);
            textAnswer.setVisible(false);
            backButton.setLabel("Back");
        }
        else if((String) arg.get("type") == "EDIT"){

            lblDepartment.setVisible(false);
            lblGrade.setVisible(false);
            lblSubGrade.setVisible(false);
            lblCompetencyCode.setVisible(false);
            lblLevel.setVisible(false);
            lblQuestion.setVisible(false);
            lblQuestionCode.setVisible(false);
            lblValidAns.setVisible(false);
            lblTextAnswer.setVisible(false);
            lblQuestionType.setVisible(false);
            answer1.setVisible(false);
            answer2.setVisible(false);
            answer3.setVisible(false);
            answer4.setVisible(false);
            answer5.setVisible(false);
            submitButton.setVisible(true);
            clearButton.setVisible(true);
            btnDepartment.setVisible(true);
            btnGrade.setVisible(true);
            btnCompetencyCode.setVisible(true);
            btnSubGrade.setVisible(true);
            backButton.setLabel("Cancel");
        }
    }

    public void isSelectedCombobox(){
        if(quest.getQuestionType().equals("YES/NO")){

            idQuestionType.setSelectedIndex(0);

        }else if(quest.getQuestionType().equals("FREETEXT")){
            idQuestionType.setSelectedIndex(1);
        }else {
            idQuestionType.setSelectedIndex(2);
        }
    }

    public void onSelect$idQuestionType(){
        String value = idQuestionType.getSelectedItem().getValue();
        if(value.equals("1")){
            textAnswer.setVisible(true);
            btnAnswer.setVisible(false);
            boxAnswer.setVisible(false);
            lblTextAnswer.setVisible(false);
            clearAnswer();
        }else{
            textAnswer.setVisible(false);
            btnAnswer.setVisible(true);
            boxAnswer.setVisible(true);

            clearAnswer();
        }
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
        textboxList.clear();
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
            if(i<5){
                textBoxAnswer.setId("textBoxAnswer"+i);
                textBoxAnswer.setParent(boxAnswer);
                i++;
                System.out.println("count textBox add = "+i);
            }
        }
        textboxList.add(textBoxAnswer);

    }

    public void onClick$btnCompetencyCode(){
        Map<String, Object> args = new HashMap<String, Object>();
        Competency competency = new Competency();
        args.put("grade",grd);
        args.put("subgrade",subGrd);
        args.put("object", competency);
        args.put("caller", this);
        Component c = Executions.createComponents(
                Resources.competenciesPopup, self, args);
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
        args.put("caller", this);
        Component c = Executions.createComponents(
                Resources.departementPopup, self, args);
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
        args.put("object", grade);
        args.put("departement", dep);
        args.put("caller", this);
        Component c = Executions.createComponents(
                Resources.gradePopup, self, args);
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
        SubGrade subGrd = new SubGrade();
        args.put("grade",grd);
        args.put("object", subGrd);
        args.put("caller", this);
        Component c = Executions.createComponents(
                Resources.subgradeopup, self, args);
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
            competence = competency;
            idCompetencyCode.setValue(competence.getCompetencyCode());
        }
    }

    public void onClick$backButton(){
        navigateTo(Resources.questionsearch,null,self);
    }

    public void onClick$submitButton(){
        updatedData();
        quest.setCreatedDate(new Date());

        Messagebox.show("Are you sure want to Update?", "Confirm Dialog", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, event = new EventListener() {
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onYes")) {
                    try {
                        String resultGrade = send.updateQuestion(quest);

                        if (resultGrade.equals("200")) {
                            Messagebox.show("Data Already Updated", "Information", Messagebox.OK, Messagebox.INFORMATION, event = new EventListener() {
                                public void onEvent(Event evt) throws InterruptedException {
                                    navigateTo(Resources.questionsearch, null, self);
                                }
                            });
                        } else if (!resultGrade.equals("200")) {
                            Messagebox.show("Data Failed To update to Table Division" , "Information", Messagebox.OK, Messagebox.INFORMATION, event = new EventListener(){
                                public void onEvent(Event evt) throws InterruptedException {
                                    navigateTo(Resources.questionsearch, null, self);
                                }
                            });
                        }

                    } catch (JsonProcessingException e) {
                        Messagebox.show("All data Failed To Update");
                    }
                }
            }
        });
    }
}
