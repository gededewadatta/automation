package admin.fe.controller.Maintenance.Competencies;

import admin.fe.model.Competency;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.Map;

public class CompetenciesDetailListController extends GenericForwardComposer {

    Textbox idDepartment;

    Textbox idGrade;

    Textbox idSubGrade;

    Textbox idCompetencies;

    Window competenciesDetailListWdw;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, false);
        competenciesDetailListWdw = (Window) comp;
    }

    public Map<String, Object> getArgs() {

        Map<String, Object> args = new HashMap<String, Object>();
        Competency competency = new Competency();
        competency.setGradeCode(idGrade.getValue());
        competency.setSubGradeCode(idSubGrade.getValue());
        competency.setCompetencyName(idCompetencies.getValue());
        competency.setDepartementCode(idDepartment.getValue());
        args.put("Competencies", competency);

        return  args;
    }
}
