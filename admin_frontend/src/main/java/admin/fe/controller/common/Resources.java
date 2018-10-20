package admin.fe.controller.common;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Resources {

    private Resources(){}

    private static final String WEB_APPLICATION_PROPERTIES = "webApplication";

    public static String getWebApplicationProperties(String key){

        String keyValue="";
        try
        {
            keyValue = ResourceBundle.getBundle(WEB_APPLICATION_PROPERTIES).getString(key).trim();
        }
        catch(MissingResourceException ex)
        {
            System.out.println("[UT] the key :"+key+", is not found or properties file not found");
            keyValue="";
        }
        catch(Exception ex)
        {
            System.out.println("Exception :"+ex.getMessage());
            keyValue="";
        }

        return keyValue;

    }

    //Competencies start :
    public static String competenciesHome = getWebApplicationProperties("competencies.home");
    public static String competenciesDetail = getWebApplicationProperties("competencies.detail");
    public static String competenciesDetailList = getWebApplicationProperties("competencies.detail.list");
    public static String competenciesViewEdit = getWebApplicationProperties("competencies.view.edit");
    public static String competenciesPopup = getWebApplicationProperties("competencies.popup");
    //Competencies stop :

    //Departement start :
    public static String departementHome = getWebApplicationProperties("departement.home");
    public static String departementDetail = getWebApplicationProperties("departement.detail");
    public static String departementPopup = getWebApplicationProperties("departement.popup");
    public static String departementViewEdit = getWebApplicationProperties("department.view.edit");
    //Departement stop

    //Division start :
    public static String divisionHome = getWebApplicationProperties("division.home");
    public static String divisionDetail = getWebApplicationProperties("division.detail");
    public static String divisionPopup = getWebApplicationProperties("division.popup");
    public static String divisionViewEdit = getWebApplicationProperties("division.view.edit");
    //Division stop :

    //Employee Start :
    public static String employeeHome = getWebApplicationProperties("employee.home");
    public static String employeeDetail = getWebApplicationProperties("employee.detail");
    public static String employeeEdit = getWebApplicationProperties("employee.edit");
    public static String employeePopup = getWebApplicationProperties("employee.popup");
    public static String employeeView = getWebApplicationProperties("employee.view");
    //Employee Stop :

    //Employee Upload Start :
    public static String employeeUpload = getWebApplicationProperties("employee.upload");
    //Employee Upload stop :

    //Question Upload Start :
    public static String questionUpload = getWebApplicationProperties("question.upload");
    //Question Upload stop :

    //Grade start :
    public static String gradeHome = getWebApplicationProperties("grade.home");
    public static String gradeDetail = getWebApplicationProperties("grade.detail");
    public static String gradeEdit = getWebApplicationProperties("grade.edit");
    public static String gradePopup = getWebApplicationProperties("grade.popup");
    public static String gradeView = getWebApplicationProperties("grade.view");
    public static String subgradeopup = getWebApplicationProperties("subgrade.popup");
    //Grade stop :

    //Question start :

    public static  String questionPopup = getWebApplicationProperties("question.popup");
    public static  String questionHome = getWebApplicationProperties("question.home");
    public static  String questionsearch = getWebApplicationProperties("question.search");
    public static  String questionViewEdit = getWebApplicationProperties("question.viewedit");

    //Question stop :

    public static String proxyHost = getWebApplicationProperties("proxy.host");

    public static String destinationDownload = getWebApplicationProperties("path.download");
    public static String destinationUpload = getWebApplicationProperties("path.upload");








}

