package admin.fe.controller.Maintenance.Question;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.Question;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zul.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionSearchController extends CommonController {

    private Window questionWindow;
    Textbox idQuestionCode;
    Textbox idCompetency;
    protected ListModelList modelList;
    protected Grid hGrid;

    SendJSON send = new SendJSON();
    Question question = new Question();
    List<Question> questionList;

    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        comp.setAttribute("controller",this, true);
    }

    public void onClick$searchButton() {

        if(idQuestionCode.getValue()!=null||!idQuestionCode.getValue().equals("")){
            question.setQuestions(idQuestionCode.getValue());
        } else {
            question.setQuestions("");
        }

        if(idCompetency.getValue()!=null||idCompetency.getValue().equals("")){
            question.setCompetency(idCompetency.getValue());
        } else {
            question.setCompetency("");
        }

            questionList = send.getQuestion(question);

        modelList = new ListModelList(questionList);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());
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
                                    navigateTo("layout/Question/QuestionViewEdit.zul",getArgs(question,"VIEW"),questionWindow);
                                }
                            }
                        });

                bEdit.addEventListener(Events.ON_CLICK,
                        new SerializableEventListener() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                String eventName = event.getName();
                                if (eventName.equals(Events.ON_CLICK)) {
                                    navigateTo("layout/Question/QuestionViewEdit.zul",getArgs(question,"EDIT"),questionWindow);
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
        navigateTo("layout/Question/Question.zul",null,self);

    }

    public Map<String, Object> getArgs(Question obj, String type) {
        Map<String, Object> args = new HashMap<String, Object>();

        args.put("id",obj.getId());
        args.put("departementCode",obj.getDepartementCode());
        args.put("grade",obj.getGrade());
        args.put("subGrade",obj.getSubGrade());
        args.put("competency",obj.getCompetency());
        args.put("questions",obj.getQuestions());
        args.put("correctAnswer",obj.getCorrectAnswer());
        args.put("answer1",obj.getAnswer1());
        args.put("answer2",obj.getAnswer2());
        args.put("answer3",obj.getAnswer3());
        args.put("answer4",obj.getAnswer4());
        args.put("answer5",obj.getAnswer5());
        args.put("level",obj.getLevel());
        args.put("createdBy",obj.getCreatedBy());
        args.put("type",type);

        return args;
    }
}
