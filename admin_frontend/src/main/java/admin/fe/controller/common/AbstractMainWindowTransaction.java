package admin.fe.controller.common;

import org.zkoss.zul.Window;

public abstract class AbstractMainWindowTransaction extends AbstractMainWindow {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Window winSearch = null;
    private Window winAccount = null;
    private Window winPortfolio = null;
    private Window winTransaction = null;
    private Window winConfirm = null;


    private String subConfirmComposer;
    private String redConfirmComposer;
    private String swiConfirmComposer;
    private String coolConfirmComposer;

    public AbstractMainWindowTransaction(){
        super();
        setSubConfirmComposer(initiateSubConfirmComposer());
        setRedConfirmComposer(initiateRedConfirmComposer());
        setSwiConfirmComposer(initiateSwiConfirmComposer());
    }

    protected abstract String initiateSubConfirmComposer();
    protected abstract String initiateRedConfirmComposer();
    protected abstract String initiateSwiConfirmComposer();

    public void setWinSearch(Window winSearch) {
        this.winSearch = winSearch;
    }
    public Window getWinSearch() {
        return winSearch;
    }
    public void setWinAccount(Window winAccount) {
        this.winAccount = winAccount;
    }
    public Window getWinAccount() {
        return winAccount;
    }
    public void setWinTransaction(Window winTransaction) {
        this.winTransaction = winTransaction;
    }
    public Window getWinTransaction() {
        return winTransaction;
    }
    public void setWinConfirm(Window winConfirm) {
        this.winConfirm = winConfirm;
    }
    public Window getWinConfirm() {
        return winConfirm;
    }
    public void setSubConfirmComposer(String subConfirmComposer) {
        this.subConfirmComposer = subConfirmComposer;
    }
    public String getSubConfirmComposer() {
        return subConfirmComposer;
    }
    public void setRedConfirmComposer(String redConfirmComposer) {
        this.redConfirmComposer = redConfirmComposer;
    }
    public String getRedConfirmComposer() {
        return redConfirmComposer;
    }
    public void setSwiConfirmComposer(String swiConfirmComposer) {
        this.swiConfirmComposer = swiConfirmComposer;
    }
    public String getSwiConfirmComposer() {
        return swiConfirmComposer;
    }

    public String getCoolConfirmComposer() {
        return coolConfirmComposer;
    }

    public void setCoolConfirmComposer(String coolConfirmComposer) {
        this.coolConfirmComposer = coolConfirmComposer;
    }

    public Window getWinPortfolio() {
        return winPortfolio;
    }

    public void setWinPortfolio(Window winPortfolio) {
        this.winPortfolio = winPortfolio;
    }

}