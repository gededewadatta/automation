package admin.fe.controller.common;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import java.util.Map;

public abstract class AbstractMainWindow extends Window {

    private static final long serialVersionUID = 1L;
    private String createZul;

    public AbstractMainWindow(){
        setCreateZul(initiateCreateZul());
    }
    protected abstract String initiateCreateZul();

    public void onCreate(){
        Executions.createComponents(this.getCreateZul(), this, getArgs());
    }
    public Map<String,Object> getArgs() {
        // TODO Auto-generated method stub
        return null;
    }
    public void loadPage(String uri, Map<String, Object> args){
        Executions.createComponents(uri, this, args);
    }
    public void setCreateZul(String createZul) {
        this.createZul = createZul;
    }
    public String getCreateZul() {
        return createZul;
    }

}
