/**
 * 
 */
package led.automation.admin.service.impl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import led.automation.admin.model.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import led.automation.admin.dao.AdminDAO;
import led.automation.admin.service.AdminService;

/**
 * @author gederanadewadatta
 *
 */
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDAO adminDAO;
	@Autowired
	private Employee employee;
    @Autowired
    private UploadFile uploadFile;
	@Autowired
	private Grade grade;
	@Autowired
	private Departement departement;
	@Autowired
	private Division division;
	@Autowired
	private SubGrade subGrade;
	@Autowired
	private Question question;
	@Autowired
	private Competency competency;
	@Autowired
	private PendingQuestion pendingQuestion;
	@Autowired
	private GradeJson gradeJson;

	private JSONObject jsonResponse;

	static String createdBy = "ADMIN";
	static Date createdDate = new Date();
	ObjectMapper objectMapper = new ObjectMapper();

	int result = 0;

    String destination = "Apps/Upload";

	// insert data : start
//            Maap gw ganti pake object Mapper
	@Override
	public String insertEmployee(String body) {
		// TODO Auto-generated method stub
		try {
			employee = objectMapper.readValue(body, Employee.class);
			System.out.println("Employee Name is :" + employee.getEmployeeName());
			System.out.println("Employee Code is :" + employee.getEmployeeCode());
//			Insert employee

//            jsonResponse = new JSONObject(body);
//            employee = new Employee();
//            employee.setEmployeeName(jsonResponse.isNull("employeeName") ? "" : jsonResponse.getString("employeeName"));
//            employee.setEmployeeCode(jsonResponse.isNull("employeeCode") ? "" : jsonResponse.getString("employeeCode"));
//            employee.setSubGradeCode(jsonResponse.isNull("subGradeCode") ? "" : jsonResponse.getString("subGradeCode"));
//            employee.setGradeCode(jsonResponse.isNull("gradeCode") ? "" : jsonResponse.getString("gradeCode"));
//            employee.setDepartementCode(jsonResponse.isNull("departmentCode") ? "" : jsonResponse.getString("departmentCode"));
//            employee.setDivisionCode(jsonResponse.isNull("divisionCode") ? "" : jsonResponse.getString("divisionCode"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.insertEmployee(employee) == 0 ? "Failure" : "Success";
	}

    @Override
    public String insertUpload(String body) {
        // TODO Auto-generated method stub
        try {

            uploadFile = objectMapper.readValue(body, UploadFile.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadFileTemp(uploadFile.getGetMediaFile(),uploadFile.getExtensions(),uploadFile.getUploadType());
    }

    public String loadFileTemp(String fileName, String extension, String uplooadType) {

		int resultInsert =0;
		try{

            Cell cell;
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            Workbook wb = null;
            if(extension.equals("xls")||extension.equals(".xls")){
                wb = new HSSFWorkbook(fis);

            }else{
                wb = new XSSFWorkbook(fis);
            }
            Sheet sheet = wb.getSheetAt(0);
            if(sheet.getRow(0).getCell(0)!=null){
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(0);
                DataFormatter formatter = new DataFormatter();
//			Row row;
                Cell[][] tempArray = new Cell[sheet.getLastRowNum()][row.getLastCellNum()];
                int lastColNum = row.getLastCellNum();
                //Extract Data : start
                int rowCount = 0;
                for(int i = 0; i < sheet.getLastRowNum(); i++){
                    if(sheet.getRow(rowCount)!=null){
                        row = sheet.getRow(rowCount);
                        for(int j = 0; j < lastColNum; j++){
                            tempArray[i][j] = row.getCell(j);
                        }
                    }
                    rowCount++;
                }
                //Extract Data : end
                //Insert to DB : start
                System.out.println("tEMP aRRAY lENGTH"+tempArray.length);
                String val ="";

                if(uplooadType.equals("EMPLOYEE")){
                    resultInsert += insertUploadEmployee(sheet,tempArray,val,formatter,new ArrayList<>(),new ArrayList<>());
                }else{
                    resultInsert += insertUploadQuestion(sheet,tempArray,val, formatter,new ArrayList<>(),new ArrayList<>());
                }

            }
            // Insert to DB : end
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return resultInsert == 0 ? "Failure" : "Success";

    }

    public int insertUploadQuestion(Sheet sheet,Cell[][] tempArray,String val,DataFormatter formatter,
                                    List<Question> isEmpty,List<Question>questionListt){
        int resultInsert=0;

        for(int a = 1; a < sheet.getLastRowNum() + 1; a++){
            question = new Question();
            for(int b = 0; b < tempArray[0].length; b++){
                if(a != 0){
                    if(sheet.getRow(a)!=null){
                        if(tempArray[0][b].getStringCellValue().equals("question")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setQuestions(val);
                            }else{
                                isEmpty.add(question);
                            }

                        }else if(tempArray[0][b].getStringCellValue().equals("answer1")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setAnswer1(val);
                            }else{
                                isEmpty.add(question);
                            }

                        }else if(tempArray[0][b].getStringCellValue().equals("answer2")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setAnswer2(val);
                            }else{
                                isEmpty.add(question);
                            }

                        }else if(tempArray[0][b].getStringCellValue().equals("answer3")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setAnswer3(val);
                            }else{
                                isEmpty.add(question);
                            }

                        }else if(tempArray[0][b].getStringCellValue().equals("answer4")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setAnswer4(val);
                            }else{
                                isEmpty.add(question);
                            }

                        }else if(tempArray[0][b].getStringCellValue().equals("answer5")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setAnswer5(val);
                            }else{
                                isEmpty.add(question);
                            }

                        }else if(tempArray[0][b].getStringCellValue().contains("correct_answer")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setCorrectAnswer(val);
                            }else{
                                isEmpty.add(question);
                            }

                        }else if(tempArray[0][b].getStringCellValue().equals("competency")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setCompetency(val);
                            }else{
                                isEmpty.add(question);
                            }

                        }else if(tempArray[0][b].getStringCellValue().equals("grade")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setGrade(val);
                            }else{
                                isEmpty.add(question);
                            }

                        }else if(tempArray[0][b].getStringCellValue().equals("sub_grade")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setSubGrade(String.valueOf(val));
                            }else{
                                isEmpty.add(question);
                            }

                        }else if(tempArray[0][b].getStringCellValue().equals("level")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setLevel(val);
                            }else{
                                isEmpty.add(question);
                            }

                        }else if(tempArray[0][b].getStringCellValue().equals("questioncode")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setQuestionCode(val);
                            }else{
                                isEmpty.add(question);
                            }
                        }else if(tempArray[0][b].getStringCellValue().equals("departmentcode")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setDepartementCode(val);
                            }else{
                                isEmpty.add(question);
                            }
                        }else if(tempArray[0][b].getStringCellValue().equals("questiontype")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                question.setQuestionType(val);
                            }else{
                                isEmpty.add(question);
                            }
                        }
                    }
                }
            }

            if(isEmpty.size() > 0){
                result = 0;
            }else{
                result +=1;
                if(!val.equals("")) {
                    questionListt.add(question);
                }

            }
        }

        for(Question question : questionListt){

            resultInsert += adminDAO.insertQuestion(question);

        }

        return  resultInsert;

    }


    public int insertUploadEmployee(Sheet sheet,Cell[][] tempArray,String val,DataFormatter formatter,
                                    List<Employee> isEmpty,List<Employee>employeeListt){

	    int resultInsert=0;

        for(int a = 1; a < sheet.getLastRowNum() + 1; a++){
            employee = new Employee();
            for(int b = 0; b < tempArray[0].length; b++){
                if(a != 0){
                    if(sheet.getRow(a)!=null){
                        if(tempArray[0][b].getStringCellValue().equals("DEPARTEMENT_CODE")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("") || val != null){
                                employee.setDepartementCode(val);
                            }else{
                                isEmpty.add(employee);
                            }
                        }else if(tempArray[0][b].getStringCellValue().equals("GRADE_CODE")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("")|| val != null) {
                                employee.setGradeCode(val);
                            }else{
                                isEmpty.add(employee);
                            }
                        }else if(tempArray[0][b].getStringCellValue().equals("DIVISION_CODE")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("")|| val != null){
                                employee.setDivisionCode(val);
                            }else{
                                isEmpty.add(employee);
                            }

                        }else if(tempArray[0][b].getStringCellValue().equals("SUB_GRADE_CODE")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("")|| val != null){
                                employee.setSubGradeCode(val);
                            }else{
                                isEmpty.add(employee);
                            }

                        }else if(tempArray[0][b].getStringCellValue().equals("EMPLOYEE_CODE")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("")|| val != null){
                                employee.setEmployeeCode(val);
                            }else{
                                isEmpty.add(employee);
                            }

                        }else if(tempArray[0][b].getStringCellValue().equals("EMPLOYEE_NAME")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("")|| val != null){
                                employee.setEmployeeName(val);
                            }else{
                                isEmpty.add(employee);
                            }

                        }else if(tempArray[0][b].getStringCellValue().contains("CREATED_BY")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("")|| val != null){
                                employee.setCreatedBy(val);
                            }else{
                                isEmpty.add(employee);
                            }

                        }else if(tempArray[0][b].getStringCellValue().contains("USER_NAME")){
                            val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                            if(!val.equals("")|| val != null){
                                employee.setUserName(val);
                            }else{
                                isEmpty.add(employee);
                            }

                        }
                    }
                }
            }
            if(isEmpty.size() > 0){
                result = 0;
            }else{
                if(!val.equals("")) {
                    result +=1;
                    employeeListt.add(employee);
                }
            }

        }

        for(Employee emp : employeeListt){

            resultInsert += adminDAO.insertEmployee(emp);

        }

        return resultInsert;

    }

	public String updateEmployee(String body) {
		// TODO Auto-generated method stub
		try {
			employee = objectMapper.readValue(body, Employee.class);
//			Insert employee

//            jsonResponse = new JSONObject(body);
//            employee = new Employee();
//            employee.setEmployeeName(jsonResponse.isNull("employeeName") ? "" : jsonResponse.getString("employeeName"));
//            employee.setEmployeeCode(jsonResponse.isNull("employeeCode") ? "" : jsonResponse.getString("employeeCode"));
//            employee.setSubGradeCode(jsonResponse.isNull("subGradeCode") ? "" : jsonResponse.getString("subGradeCode"));
//            employee.setGradeCode(jsonResponse.isNull("gradeCode") ? "" : jsonResponse.getString("gradeCode"));
//            employee.setDepartementCode(jsonResponse.isNull("departmentCode") ? "" : jsonResponse.getString("departmentCode"));
//            employee.setDivisionCode(jsonResponse.isNull("divisionCode") ? "" : jsonResponse.getString("divisionCode"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.updateEmployee(employee) == 0 ? "Failure" : "Success";
	}



	@Override
	public String updateCompetency(String body) {
		// TODO Auto-generated method stub
		try {
			competency = objectMapper.readValue(body, Competency.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.updateCompetency(competency) == 0 ? "Failure" : "Success";
	}
	@Override
	public String insertGrade(String body) {
		// TODO Auto-generated method stub

		try {
			gradeJson = objectMapper.readValue(body, GradeJson.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		grade.setGradeCode(gradeJson.getGradeCode());
		grade.setGradeName(gradeJson.getGradeName());
		grade.setCreatedDate(new Date());
		grade.setGradeName(gradeJson.getGradeName());
		grade.setDivisionCode(gradeJson.getDivisionCode());
		grade.setDepartementCode(gradeJson.getDepartementCode());
		subGrade.setSubGradeCode(gradeJson.getSubGradeCode());
		subGrade.setSubGradeName(gradeJson.getSubGradeName());
		subGrade.setGradeCode(gradeJson.getGradeCode());
		subGrade.setDepartementCode(gradeJson.getDepartementCode());
		subGrade.setCreatedBy(gradeJson.getCreatedBy());
		subGrade.setCreatedDate(new Date());

		int gradeInsertResult = adminDAO.insertGrade(grade);
		int subGradeInsertResult = adminDAO.insertSubGrade(subGrade);

		int lastResult = gradeInsertResult + subGradeInsertResult;

		return lastResult == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertQuestion(String body) {
		// TODO Auto-generated method stub
		String result = null;
		int result2 = 0;
		List<String> username = new ArrayList<String>();
		try {
			question = objectMapper.readValue(body, Question.class);
			// get data username based on question grade and sub grade : start
			username = adminDAO.searchEmployeeByGradeAndSubGrade(question.getGrade(), question.getSubGrade());
			// get data username based on question grade and sub grade : stop

			result2 =adminDAO.insertQuestion(question);
			// to insert into PendingQuestion Table:start
			if(result2 > 0){
				for (String user : username) {
					pendingQuestion.setQuestions(question.getQuestionCode());
					pendingQuestion.setAnswer1(question.getAnswer1());
					pendingQuestion.setAnswer2(question.getAnswer2());
					pendingQuestion.setAnswer3(question.getAnswer3());
					pendingQuestion.setAnswer4(question.getAnswer4());
					pendingQuestion.setAnswer5(question.getAnswer5());
					pendingQuestion.setCorrectAnswer(question.getCorrectAnswer());
					pendingQuestion.setCreatedDate(question.getCreatedDate());
					pendingQuestion.setCreatedBy(question.getCreatedBy());
					pendingQuestion.setCompetency(question.getCompetency());
					pendingQuestion.setLevel(question.getLevel());
					pendingQuestion.setUserName(user);
					pendingQuestion.setQuestionType(question.getQuestionType());
					result2 += adminDAO.insertPendingQuestion(pendingQuestion);
				}
			}

			System.out.println("result insert Question is :"+result2);
			// to insert into PendingQuestion Table:stop
			result = result2 <= 1 ? "Failure" : "Success";

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String insertQuestions(String body) {
		// TODO Auto-generated method stub

		try {
			question = objectMapper.readValue(body, Question.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.insertQuestion(question) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertDivision(String body) {
		// TODO Auto-generated method stub
		try {
			division = objectMapper.readValue(body,Division.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*jsonResponse = new JSONObject(body);
		division = new Division();
		division.setDivisonCode(jsonResponse.isNull("divisionCode") ? "" : jsonResponse.getString("divisionCode"));
		division.setDivisionName(jsonResponse.isNull("divisionName") ? "" : jsonResponse.getString("divisionName"));
		division.setCreatedBy(createdBy);
		division.setCreatedDate(createdDate);*/

		return adminDAO.insertDivision(division) == 0 ? "Failure" : "Success";
	}

	@Override
	public String updateDivision(String body) {
		// TODO Auto-generated method stub
		try {
			division = objectMapper.readValue(body,Division.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*jsonResponse = new JSONObject(body);
		division = new Division();
		division.setDivisonCode(jsonResponse.isNull("divisionCode") ? "" : jsonResponse.getString("divisionCode"));
		division.setDivisionName(jsonResponse.isNull("divisionName") ? "" : jsonResponse.getString("divisionName"));
		division.setCreatedBy(createdBy);
		division.setCreatedDate(createdDate);*/

		return adminDAO.updateDivision(division) == 0 ? "Failure" : "Success";
	}

	@Override
	public String updateDepartement(String body) {
		// TODO Auto-generated method stub
		try {
			departement = objectMapper.readValue(body,Departement.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*jsonResponse = new JSONObject(body);
		division = new Division();
		division.setDivisonCode(jsonResponse.isNull("divisionCode") ? "" : jsonResponse.getString("divisionCode"));
		division.setDivisionName(jsonResponse.isNull("divisionName") ? "" : jsonResponse.getString("divisionName"));
		division.setCreatedBy(createdBy);
		division.setCreatedDate(createdDate);*/

		return adminDAO.updateDepartement(departement) == 0 ? "Failure" : "Success";
	}

	@Override
	public String updateGrade(String body) {
		// TODO Auto-generated method stub

		try {
			gradeJson = objectMapper.readValue(body, GradeJson.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		grade.setIdGrade(gradeJson.getIdGrade());
		grade.setGradeCode(gradeJson.getGradeCode());
		grade.setGradeName(gradeJson.getGradeName());
		grade.setCreatedDate(new Date());
		grade.setCreatedBy(gradeJson.getCreatedBy());
		grade.setDivisionCode(gradeJson.getDivisionCode());
		grade.setDepartementCode(gradeJson.getDepartementCode());
		subGrade.setIdSubGrade(gradeJson.getIdSubGrade());
		subGrade.setSubGradeCode(gradeJson.getSubGradeCode());
		subGrade.setSubGradeName(gradeJson.getSubGradeName());
		subGrade.setDepartementCode(gradeJson.getDepartementCode());
		subGrade.setGradeCode(gradeJson.getGradeCode());
		subGrade.setCreatedBy(gradeJson.getCreatedBy());
		subGrade.setCreatedDate(new Date());

		int gradeUpdateResult = adminDAO.updateGrade(grade);
		int subGrdUpdateResult = adminDAO.updateSubGrade(subGrade);

		int lastResult = gradeUpdateResult + subGrdUpdateResult;

		return lastResult == 0 ? "Failure" : "Success";
	}

	@Override
	public String updateSubGrade(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		subGrade = new SubGrade();
		subGrade.setIdSubGrade(jsonResponse.isNull("id") ? null : jsonResponse.getLong("id"));
		subGrade.setSubGradeCode(jsonResponse.isNull("subGradeCode") ? "" : jsonResponse.getString("subGradeCode"));
		subGrade.setSubGradeName(jsonResponse.isNull("subGradeName") ? "" : jsonResponse.getString("subGradeName"));
		subGrade.setGradeCode(jsonResponse.isNull("gradeCode") ? "" : jsonResponse.getString("gradeCode"));
		subGrade.setCreatedBy(createdBy);
		subGrade.setCreatedDate(createdDate);

		return adminDAO.updateSubGrade(subGrade) == 0 ? "Failure" : "Success";
	}

	@Override
	public String updateQuestion(String body) {
		// TODO Auto-generated method stub
		try {
			question = objectMapper.readValue(body,Question.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int result =0;

		List<String>username = adminDAO.searchEmployeeByGradeAndSubGrade(question.getGrade(), question.getSubGrade());
		// get data username based on question grade and sub grade : stop

		result = adminDAO.updateQuestion(question);
		// to insert into PendingQuestion Table:start
		if( result > 0){
			for (String user : username) {
				pendingQuestion.setQuestions(question.getQuestionCode());
				pendingQuestion.setAnswer1(question.getAnswer1());
				pendingQuestion.setAnswer2(question.getAnswer2());
				pendingQuestion.setAnswer3(question.getAnswer3());
				pendingQuestion.setAnswer4(question.getAnswer4());
				pendingQuestion.setAnswer5(question.getAnswer5());
				pendingQuestion.setCorrectAnswer(question.getCorrectAnswer());
				pendingQuestion.setCreatedDate(question.getCreatedDate());
				pendingQuestion.setCreatedBy(question.getCreatedBy());
				pendingQuestion.setCompetency(question.getCompetency());
				pendingQuestion.setLevel(question.getLevel());
				pendingQuestion.setUserName(user);
				result += adminDAO.updatePendingQuestion(pendingQuestion);
			}
		}

		/*jsonResponse = new JSONObject(body);
		division = new Division();
		division.setDivisonCode(jsonResponse.isNull("divisionCode") ? "" : jsonResponse.getString("divisionCode"));
		division.setDivisionName(jsonResponse.isNull("divisionName") ? "" : jsonResponse.getString("divisionName"));
		division.setCreatedBy(createdBy);
		division.setCreatedDate(createdDate);*/

		return result == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertDepartement(String body) {
		// TODO Auto-generated method stub
		try {
			departement = objectMapper.readValue(body,Departement.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*jsonResponse = new JSONObject(body);
		departement = new Departement();
		departement.setDivisionCode(jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode"));
		departement.setDepartementCode(
				jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode"));
		departement.setDepartementName(
				jsonResponse.isNull("departementname") ? "" : jsonResponse.getString("departementname"));
		departement.setCreatedBy(createdBy);
		departement.setCreatedDate(createdDate);*/
		return adminDAO.insertDepartement(departement) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertSubGrade(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		subGrade = new SubGrade();
		subGrade.setIdSubGrade(jsonResponse.isNull("id") ? null : jsonResponse.getLong("id"));
		subGrade.setSubGradeCode(jsonResponse.isNull("subGradeCode") ? "" : jsonResponse.getString("subGradeCode"));
		subGrade.setSubGradeName(jsonResponse.isNull("subGradeName") ? "" : jsonResponse.getString("subGradeName"));
		subGrade.setGradeCode(jsonResponse.isNull("gradeCode") ? "" : jsonResponse.getString("gradeCode"));
		subGrade.setCreatedBy(createdBy);
		subGrade.setCreatedDate(createdDate);

		return adminDAO.insertSubGrade(subGrade) == 0 ? "Failure" : "Success";
	}

	@Override
	public String insertCompetency(String body) {
		// TODO Auto-generated method stub
		try {
			competency = objectMapper.readValue(body, Competency.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.insertCompetency(competency) == 0 ? "Failure" : "Success";
	}
	// insert data : stop

	// upload data : start
	@Override
	public int uploadQuestion(String body) {
		// TODO Auto-generated method stub
		int result = 0;
		int result2 = 0;
		List<String> username = new ArrayList<String>();
		try {
			question = objectMapper.readValue(body, Question.class);
			// get data username based on question grade and sub grade : start
			username = adminDAO.searchEmployeeByGradeAndSubGrade(question.getGrade(), question.getSubGrade());
			// get data username based on question grade and sub grade : stop

			// to insert into PendingQuestion Table:start
			for (String user : username) {
				pendingQuestion.setQuestions(question.getQuestions());
				pendingQuestion.setAnswer1(question.getAnswer1());
				pendingQuestion.setAnswer2(question.getAnswer2());
				pendingQuestion.setAnswer3(question.getAnswer3());
				pendingQuestion.setAnswer4(question.getAnswer4());
				pendingQuestion.setAnswer5(question.getAnswer5());
				pendingQuestion.setCorrectAnswer(question.getCorrectAnswer());
				pendingQuestion.setCreatedDate(question.getCreatedDate());
				pendingQuestion.setCreatedBy(question.getCreatedBy());
				pendingQuestion.setCompetency(question.getCompetency());
				pendingQuestion.setLevel(question.getLevel());
				pendingQuestion.setUserName(user);
				result2 += adminDAO.insertPendingQuestion(pendingQuestion);
			}
			// to insert into PendingQuestion Table:stop
			if (result2 > 0)
				result = adminDAO.insertQuestion(question);
			else
				result = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int uploadEmployee(String body) {
		// TODO Auto-generated method stub
		int result = 0;
		jsonResponse = new JSONObject(body);
		division = new Division();
		departement = new Departement();
		grade = new Grade();
		subGrade = new SubGrade();
		competency = new Competency();
		// insert division :start
		division.setDivisonCode(jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode"));
		division.setDivisionName(jsonResponse.isNull("divisionname") ? "" : jsonResponse.getString("divisionname"));
		division.setCreatedBy(createdBy);
		division.setCreatedDate(createdDate);
		result += adminDAO.insertDivision(division);
		// insert division :stop
		// insert departement :start
		departement.setDivisionCode(jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode"));
		departement.setDepartementCode(
				jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode"));
		departement.setDepartementName(
				jsonResponse.isNull("departementname") ? "" : jsonResponse.getString("departementname"));
		departement.setCreatedBy(createdBy);
		departement.setCreatedDate(createdDate);
		result += adminDAO.insertDepartement(departement);
		// insert departement :stop
		// insert grade :start
		grade.setDepartementCode(
				jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode"));
		grade.setCreatedBy(createdBy);
		grade.setCreatedDate(createdDate);
		grade.setGradeCode(jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode"));
		grade.setGradeName(jsonResponse.isNull("gradename") ? "" : jsonResponse.getString("gradename"));
		result += adminDAO.insertGrade(grade);
		// insert grade :stop
		// insert sub grade :start
		subGrade.setSubGradeCode(jsonResponse.isNull("subgradecode") ? "" : jsonResponse.getString("subgradecode"));
		subGrade.setSubGradeName(jsonResponse.isNull("subgradename") ? "" : jsonResponse.getString("subgradename"));
		subGrade.setGradeCode(jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode"));
		subGrade.setCreatedBy(createdBy);
		subGrade.setCreatedDate(createdDate);
		result += adminDAO.insertSubGrade(subGrade);
		// insert sub grade :stop
		// insert competency : start
		competency.setCompetencyCode(
				jsonResponse.isNull("competencycode") ? "" : jsonResponse.getString("competencycode"));
		competency.setCompetencyName(
				jsonResponse.isNull("competencyname") ? "" : jsonResponse.getString("competencyname"));
		competency.setCreatedBy(createdBy);
		competency.setCreatedDate(createdDate);
		competency.setDepartementCode(
				jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode"));
		competency.setGradeCode(jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode"));
		result += adminDAO.insertCompetency(competency);
		// insert competency : stop
		return result;
	}
	// upload data : stop

	// search data : start

	@Override
	public List<Dashboard> findDashboard() {
		// TODO Auto-generated method stub
		return adminDAO.findDashboard();
	}

	@Override
	public List<Question> searchQuestion(String body) {
		// TODO Auto-generated method stub

		List<Question> questions = new ArrayList<>();
		try {
			question = objectMapper.readValue(body, Question.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if((question.getGrade() == null || question.getGrade().equalsIgnoreCase(""))
				&& (question.getCompetency() == null || question.getCompetency().equalsIgnoreCase(""))){
			questions.addAll(adminDAO.searchQuestionAll());
		}else if((question.getGrade() != null || !question.getGrade().equalsIgnoreCase(""))
				&& (question.getCompetency() == null || question.getCompetency().equalsIgnoreCase(""))){
			questions.addAll(adminDAO.searchQuestionByGrade(question.getGrade()));
		}else if((question.getGrade() == null || question.getGrade().equalsIgnoreCase(""))
				&& (question.getCompetency() != null || !question.getCompetency().equalsIgnoreCase(""))){
			questions.addAll(adminDAO.searchQuestionByCompetencies(question.getCompetency()));
		}else{
			questions.addAll(adminDAO.searchQuestionByGradeAndCompetencies(question.getGrade(),question.getCompetency()));
		}

		return questions;
	}

	@Override
	public List<Competency> searchCompetency(String body) {
		// TODO Auto-generated method stub

		try {
			competency = objectMapper.readValue(body, Competency.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.searchCompetency(competency.getCompetencyCode(),competency.getCompetencyName());
	}

	@Override
	public List<Competency> searchCompetencyPopup(String body) {
		// TODO Auto-generated method stub
		Competency competency = new Competency();
		try {
			competency = objectMapper.readValue(body, Competency.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.searchCompetencyPopup(competency.getCompetencyCode(),competency.getCompetencyName(),competency.getDepartementCode(),competency.getGradeCode(),competency.getSubGradeCode());
	}

	@Override
	public List<Competency> searchCompetencyByGradeCode(String body) {
		// TODO Auto-generated method stub

		try {
			competency = objectMapper.readValue(body, Competency.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.searchCompetencyByGradeCode(competency.getGradeCode(),competency.getSubGradeCode(),competency.getDepartementCode());
	}

    @Override
    public List<Report> searchReportEmployee(String body) {
		// TODO Auto-generated method stub
		Report rep = new Report();
		try {
			rep = objectMapper.readValue(body, Report.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		jsonResponse = new JSONObject(body);
//		String divisionCode = null;
//		divisionCode = jsonResponse.isNull("divisioncode")?"":jsonResponse.getString("divisioncode");
//		String divisionName = null;
//		divisionName = jsonResponse.isNull("divisionname")?"":jsonResponse.getString("divisionname");
		return adminDAO.searchReportEmployee(rep.getEmployeeCode(), rep.getGrade());
    }

    @Override
	public List<SubGrade> searchSubGrade(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String subGradeCode = null;
		subGradeCode = jsonResponse.isNull("subgradeCode") ? "" : jsonResponse.getString("subgradeCode");
		String subGradeName = null;
		subGradeName = jsonResponse.isNull("subgradeName") ? "" : jsonResponse.getString("subgradeName");

		return adminDAO.searchSubGrade(subGradeCode, subGradeName);
	}

	@Override
	public List<GradeJson> searchGradeJson(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);

		System.out.println(jsonResponse);
		String departmentCode = "";
		departmentCode = jsonResponse.isNull("departementCode") ? "" : jsonResponse.getString("departementCode");
		String departmentName = "";
		departmentName = jsonResponse.isNull("divisionCode") ? "" : jsonResponse.getString("divisionCode");

		return adminDAO.searchGradeJson(departmentCode, departmentName);
	}

	@Override
	public List<Grade> searchGrade(String body) {
		// TODO Auto-generated method stub
		Grade grd = new Grade();
		try {
			grd = objectMapper.readValue(body, Grade.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("INI COBA MASUK");
//		String gradeCode = null;
//		String gradeName = null;
//		jsonResponse = new JSONObject(body);
//		gradeCode = jsonResponse.isNull("gradecode")?"":jsonResponse.getString("gradecode");
//		gradeName = jsonResponse.isNull("gradename")?"":jsonResponse.getString("gradename");

		return adminDAO.searchGrade(grd.getDepartementCode(), grd.getDivisionCode());
	}

	@Override
	public List<Grade> searchGradePopup(String body) {
		// TODO Auto-generated method stub
		Grade grd = new Grade();
		try {
			grd = objectMapper.readValue(body, Grade.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("INI COBA MASUK");

		return adminDAO.searchGradePopup(grd.getDepartementCode(), grd.getDivisionCode(),grd.getGradeCode(),grd.getGradeName());
	}

	@Override
	public List<SubGrade> searchSubGradePopup(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		subGrade = new SubGrade();
		subGrade.setIdSubGrade(jsonResponse.isNull("id") ? null : jsonResponse.getLong("id"));
		subGrade.setSubGradeCode(jsonResponse.isNull("subGradeCode") ? "" : jsonResponse.getString("subGradeCode"));
		subGrade.setSubGradeName(jsonResponse.isNull("subGradeName") ? "" : jsonResponse.getString("subGradeName"));
		subGrade.setGradeCode(jsonResponse.isNull("gradeCode") ? "" : jsonResponse.getString("gradeCode"));
		subGrade.setDepartementCode(jsonResponse.isNull("departementCode") ? "" : jsonResponse.getString("departementCode"));
		subGrade.setCreatedBy(createdBy);
		subGrade.setCreatedDate(createdDate);

		return adminDAO.searchSubGradePopup(subGrade.getDepartementCode(), subGrade.getGradeCode(), subGrade.getSubGradeCode(), subGrade.getSubGradeName());
	}

	@Override
	public List<Employee> searchEmployee(String body) {
		// TODO Auto-generated method stub
		try {
			employee = objectMapper.readValue(body, Employee.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.searchEmployee(employee.getEmployeeCode(), employee.getEmployeeName());
	}

	@Override
	public Employee searchEmployeeByCode(String body) {
		// TODO Auto-generated method stub
//		return adminDAO.searchEmployee(body,"");
		return null;
	}

	@Override
	public List<Departement> searchDepartement(String body) {
		// TODO Auto-generated method stub
		Departement dep = new Departement();
		try {
			dep = objectMapper.readValue(body, Departement.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * jsonResponse = new JSONObject(body); String departementCode = null;
		 * departementCode =
		 * jsonResponse.isNull("departementcode")?"":jsonResponse.getString(
		 * "departementcode"); String departementName = null; departementName =
		 * jsonResponse.isNull("departementname")?"":jsonResponse.getString(
		 * "departementname");
		 */
		return adminDAO.searchDepartement(dep.getDivisionCode(), dep.getDepartementCode());
	}

	@Override
	public List<Departement> searchDepartementPopup(String body) {
		// TODO Auto-generated method stub
		Departement dep = new Departement();
		try {
			dep = objectMapper.readValue(body, Departement.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return adminDAO.searchDepartementPopup(dep.getDivisionCode(), dep.getDepartementCode(), dep.getDepartementName());
	}

	@Override
	public List<Division> searchDivision(String body) {
		// TODO Auto-generated method stub
		Division div = new Division();
		try {
			div = objectMapper.readValue(body, Division.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		jsonResponse = new JSONObject(body);
//		String divisionCode = null;
//		divisionCode = jsonResponse.isNull("divisioncode")?"":jsonResponse.getString("divisioncode");
//		String divisionName = null;
//		divisionName = jsonResponse.isNull("divisionname")?"":jsonResponse.getString("divisionname");
		return adminDAO.searchDivision(div.getDivisionCode(), div.getDivisionName());
	}
	// search data : stop

	// generate data : start

	@Override
	public List<String> generateEmployee(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String departementCode = null;
		String employeeCode = null;

		divisionCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode");
		employeeCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("employeecode");
		departementCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("departementcode");
		return adminDAO.generateEmployee(divisionCode, employeeCode, departementCode);
	}

	@Override
	public List<String> generateGrade(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String departementCode = null;
		String company = null;
		company = jsonResponse.isNull("company") ? "" : jsonResponse.getString("company");
		divisionCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode");
		departementCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("departementcode");

		return adminDAO.generateGrade(company, divisionCode, departementCode);
	}

	@Override
	public List<String> generateSubGrade(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String departementCode = null;
		String gradeCode = null;
		divisionCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode");
		departementCode = jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode");
		gradeCode = jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode");

		return adminDAO.generateSubGrade(divisionCode, departementCode, gradeCode);
	}

	@Override
	public List<String> generateQuestion(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String departementCode = null;
		String gradeCode = null;
		departementCode = jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode");
		gradeCode = jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode");

		return adminDAO.generateQuestion(departementCode, gradeCode);
	}

	@Override
	public List<String> generateDepartement(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String company = null;
		divisionCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode");
		company = jsonResponse.isNull("company") ? "" : jsonResponse.getString("company");

		return adminDAO.generateDepartement(divisionCode, company);
	}

	@Override
	public List<String> generateDivision(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String company = null;
		company = jsonResponse.isNull("company") ? "" : jsonResponse.getString("company");

		return adminDAO.generateDivision(company);
	}

	@Override
	public List<String> generateCompetency(String body) {
		// TODO Auto-generated method stub
		jsonResponse = new JSONObject(body);
		String divisionCode = null;
		String gradeCode = null;
		String departementCode = null;
		divisionCode = jsonResponse.isNull("divisioncode") ? "" : jsonResponse.getString("divisioncode");
		departementCode = jsonResponse.isNull("departementcode") ? "" : jsonResponse.getString("departementcode");
		gradeCode = jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode");

		return adminDAO.generateCompetency(divisionCode, departementCode, gradeCode);
	}

	@Override
	public List<String> findSubGradeByGradeCode(String body) {
		// TODO Auto-generated method stub
		String gradeCode = null;
		gradeCode = jsonResponse.isNull("gradecode") ? "" : jsonResponse.getString("gradecode");
		return adminDAO.findSubGradeByGradeCode(gradeCode);
	}

	/*@Override
	public List<String> findGradeByDeptCode(String body) {
		return null;
	}

	@Override
	public List<String> findDepartementByDivCode(String body) {
		return null;
	}*/

	// generate data : stop

}
