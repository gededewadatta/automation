package admin.fe.controller.Maintenance.Question;

import admin.fe.controller.common.CommonController;
import admin.fe.engine.SendJSON;
import admin.fe.model.Grade;
import admin.fe.model.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.util.Date;

public class QuestionController extends CommonController {

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




}
