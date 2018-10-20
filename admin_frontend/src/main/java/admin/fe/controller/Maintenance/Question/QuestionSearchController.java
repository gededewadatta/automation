package admin.fe.controller.Maintenance.Question;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.Resources;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.*;
import admin.fe.model.Competency;
import admin.fe.model.Grade;
import admin.fe.model.Question;
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

public class QuestionSearchController extends CommonController implements PopupCallerGradeInterface,PopupCallerCompetencyInterface {

    private Window questionWindow;
    Textbox idGradeCode;
    Textbox idCompetency;

    protected ListModelList modelList;
    protected Grid hGrid;

    SendJSON send = new SendJSON();
    Question question = new Question();
    List<Question> questionList;
    Grade grd = new Grade();
    Competency competence = new Competency();

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("controller",this, true);
    }

    public void onClick$cancelButton(){
        idGradeCode.setValue("");
        idCompetency.setValue("");
    }

    public void onClick$searchButton() {

        if(idGradeCode.getValue()!=null||!idGradeCode.getValue().equals(" ")){
            question.setGrade(idGradeCode.getValue());
        }

        if(idCompetency.getValue()!=null||idCompetency.getValue().equals(" ")){
            question.setCompetency(idCompetency.getValue());
        }

            questionList = send.getQuestion(question);

        if(questionList.size() > 0){
            modelList = new ListModelList(questionList);
            hGrid.setModel(modelList);
            hGrid.setPageSize(5);
            hGrid.setRowRenderer(createGridRowRenderer());
        }else{
            Messagebox.show("Data not found");
        }


    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Question) data);
            }

            private void renderDataRow(Row row, Question question){

                row.setValue(question);
                new Label(question.getDepartementCode()).setParent(row);
                new Label(question.getGrade()+"-"+question.getSubGrade()).setParent(row);
                new Label(question.getCompetency()).setParent(row);
                new Label(question.getQuestions()).setParent(row);
                new Label(question.getCorrectAnswer()).setParent(row);

                Hbox hbox = new Hbox();

                Button bView = new Button("View");
                Button bEdit = new Button("Edit");

                bView.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                    navigateTo(Resources.questionViewEdit,getArgs(question,"VIEW"),questionWindow);
                                }
                            }
                        });

                bEdit.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                    navigateTo(Resources.questionViewEdit,getArgs(question,"EDIT"),questionWindow);
                                }
                            }
                        });

                Separator separator = new Separator("vertical");
                separator.setWidth("10px");

                hbox.appendChild(bView);
                separator.setParent(hbox);
                hbox.appendChild(bEdit);
                row.appendChild(hbox);

            }
        };
    }

    public void onClick$addButton(){
        System.out.println("Ini button Submit");
        navigateTo(Resources.questionHome,null,self);

    }

    public Map<String, Object> getArgs(Question obj, String type) {
        Map<String, Object> args = new HashMap<String, Object>();

        args.put("id",obj.getId());
        args.put("departementCode",obj.getDepartementCode());
        args.put("grade",obj.getGrade());
        args.put("subGrade",obj.getSubGrade());
        args.put("competency",obj.getCompetency());
        args.put("questions",obj.getQuestions());
        args.put("questionCode",obj.getQuestionCode());
        args.put("correctAnswer",obj.getCorrectAnswer());
        args.put("answer1",obj.getAnswer1());
        args.put("answer2",obj.getAnswer2());
        args.put("answer3",obj.getAnswer3());
        args.put("answer4",obj.getAnswer4());
        args.put("answer5",obj.getAnswer5());
        args.put("level",obj.getLevel());
        args.put("createdBy",obj.getCreatedBy());
        args.put("questionType",obj.getQuestionType());
        args.put("type",type);

        return args;
    }

    public void onClick$btnCompetencyCode(){
        Map<String, Object> args = new HashMap<String, Object>();
        Competency competency = new Competency();
        args.put("grade",grd);
        args.put("subgrade",null);
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

    public void onClick$btnGrade(){
        Map<String, Object> args = new HashMap<String, Object>();
        Grade grade = new Grade();
        args.put("object", grade);
        args.put("departement", null);
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

    @Override
    public void afterSelectCompetencies(Competency competency) {
        if(competency != null){
            competence = competency;
            idCompetency.setValue(competence.getCompetencyCode());
        }
    }

    @Override
    public void afterSelectGrade(Grade grade) {
        if(grade != null){
            grd = grade;
            idGradeCode.setValue(grd.getGradeCode());
        }
    }


}
