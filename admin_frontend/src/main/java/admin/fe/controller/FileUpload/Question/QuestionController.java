package admin.fe.controller.FileUpload.Question;

import admin.fe.controller.common.CommonController;
import admin.fe.controller.common.SerializableRowRenderer;
import admin.fe.engine.SendJSON;
import admin.fe.model.Employee;
import admin.fe.model.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.*;
import org.zkoss.zul.Textbox;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class QuestionController extends CommonController {

    protected Grid hGrid;
    protected ListModelList modelList;
    protected Textbox idUpload;
    org.zkoss.zul.Row rw;

    int result = 0;

    List<Question> questions = new ArrayList<>();

    List<Question> isEmpty = new ArrayList<>();

    String destination = "Apps/Upload";

    Button btnSubmit;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("controller", this, true);
        btnSubmit.setDisabled(true);
        createDirDestination();
    }

    public void onUpload$browseButton(UploadEvent e){
        System.out.println("Test Mesia :"+e.getMedias());
        btnSubmit.setDisabled(false);
        doValidate(e.getMedias());

    }

    public void doValidate(Media[] multiMedia) {

        for (Media med : multiMedia) {

            idUpload.setValue(med.getName());
            copyToTemp(med);
        }


        modelList = new ListModelList(questions);
        hGrid.setModel(modelList);
        hGrid.setPageSize(5);
        hGrid.setRowRenderer(createGridRowRenderer());




    }

    protected SerializableRowRenderer createGridRowRenderer(){

        return new SerializableRowRenderer() {
            @Override
            public void render(org.zkoss.zul.Row row, Object data, int index) throws Exception {
                renderDataRow(row,(Question) data);
            }

            private void renderDataRow(org.zkoss.zul.Row row, Question question){

                row.setValue(question);
                new Label(question.getQuestions()).setParent(row);
                new Label(question.getGrade()).setParent(row);
                new Label(question.getSubGrade()).setParent(row);
                new Label(question.getCorrectAnswer()).setParent(row);
                new Label(question.getCompetency()).setParent(row);
                new Label(question.getLevel()).setParent(row);
            }
        };
    }

    public void loadFileTemp(String extension) {

        SimpleDateFormat format = new SimpleDateFormat("DDMMYYYY");
        String dateString = format.format( new Date()   );
        String path = "/Apps/Upload/QuestionTemp"+dateString+".xls";
        Question question;
        try{

            Cell cell;
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            Workbook wb = null;
            if(extension.equals("xls")){
                wb = new XSSFWorkbook(fis);

            }else{
                wb = new HSSFWorkbook(fis);
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
                    row = sheet.getRow(rowCount);
                    for(int j = 0; j < lastColNum; j++){
                        tempArray[i][j] = row.getCell(j);
                    }
                    rowCount++;
                }
                //Extract Data : end

                //Insert to DB : start
                System.out.println("tEMP aRRAY lENGTH"+tempArray.length);
                String val ="";
                for(int a = 1; a < sheet.getLastRowNum() + 1; a++){
                    question = new Question();
                    for(int b = 0; b < tempArray[0].length; b++){
                        if(a != 0){
                            if(tempArray[0][b].getStringCellValue().equals("question")){
                                val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                question.setQuestions(val);
                            }else if(tempArray[0][b].getStringCellValue().equals("answer1")){
                                val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                question.setAnswer1(val);
                            }else if(tempArray[0][b].getStringCellValue().equals("answer2")){
                                val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                question.setAnswer2(val);
                            }else if(tempArray[0][b].getStringCellValue().equals("answer3")){
                                val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                question.setAnswer3(val);
                            }else if(tempArray[0][b].getStringCellValue().equals("answer4")){
                                val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                question.setAnswer4(val);
                            }else if(tempArray[0][b].getStringCellValue().equals("answer5")){
                                val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                question.setAnswer5(val);
                            }else if(tempArray[0][b].getStringCellValue().contains("correct_answer")){
                                val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                question.setCorrectAnswer(val);
                            }else if(tempArray[0][b].getStringCellValue().equals("competency")){
                                val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                question.setCompetency(val);
                            }else if(tempArray[0][b].getStringCellValue().equals("grade")){
                                val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                question.setGrade(val);
                            }else if(tempArray[0][b].getStringCellValue().equals("sub_grade")){
                                val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                question.setSubGrade(String.valueOf(val));
                            }else if(tempArray[0][b].getStringCellValue().equals("level")){
                                val = formatter.formatCellValue(sheet.getRow(a).getCell(b));
                                question.setLevel(val);
                            }

                        }
                    }
                    questions.add(question);
                }
            }else{

                result = -1;
                Messagebox.show("Header of file is empty!");
                idUpload.setValue("");
                btnSubmit.setDisabled(true);

            }

            // Insert to DB : end
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void copyToTemp(Media media) {
        int rpt = 0;

        SimpleDateFormat format = new SimpleDateFormat("DDMMYYYY");
        String dateString = format.format( new Date()   );
        String pathTemp = "/Apps/Upload/QuestionTemp"+dateString+".xls";
        File f = new File(pathTemp);

        InputStream stream = media.getStreamData();
        OutputStream os = null;
        String extensinos = media.getName().substring(media.getName().lastIndexOf("."),media.getName().length());
        try {
            os = new FileOutputStream(f);
            byte buf[] = new byte[1024];
            int len;

            while ((len = stream.read(buf)) > 0) {
                os.write(buf, 0, len);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {

                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                }
            }
        }
        loadFileTemp(extensinos);

    }

    public void onClick$btnSubmit(){

        showConfirmDialog("Do you want to save data?");
    }

    public void showConfirmDialog(String message) {
        Messagebox.show(message, "confirm",
                Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                Messagebox.NO, new SerializableEventListener() {

                    private static final long serialVersionUID = -8695776168749565854L;

                    @Override
                    public void onEvent(Event event) throws Exception {
                        int data = (Integer) event.getData();
                        switch (data) {
                            case Messagebox.YES:

                                SendJSON send = new SendJSON();
                                List<Question> quest = new ArrayList<>();
                                try {

                                    if(result > 0 ){
                                        for(Question question: questions){

                                            if(send.insertQuestion(question).equals("200")){
                                                quest.add(question);
                                            }

                                        }

                                        if(quest.size() >0){
                                            Messagebox.show("Data Successfully Save");
                                        }else{
                                            Messagebox.show("Data already exists");
                                        }
                                    }else if(result == -1){

                                        Messagebox.show("Header of file is empty!");
                                        idUpload.setValue("");

                                    }else{

                                        Messagebox.show("There is some field still empty");
                                        idUpload.setValue("");
                                        isEmpty.clear();

                                    }

                                    btnSubmit.setDisabled(true);
                                    quest.clear();
                                    questions.clear();
                                    hGrid.removeChild(hGrid.getRows());
                                } catch (JsonProcessingException e) {
                                    Messagebox.show("Data failed to Save");
                                }

                        }
                    }
                });
    }

    public void createDirDestination(){
        String[] listdestinations = destination.split("/");
        String destinationCreate = "";
        for (String destinations: listdestinations){
            destinationCreate = destinationCreate+"/"+destinations;
            File file = new File(destinationCreate);
            System.out.println("Destination : "+destination);
            if(!file.exists()){
                if(file.mkdir()){
                    System.out.println(destination+" Destination directory successfull created");
                } else{
                    System.out.println("Failed created destination directory");
                }
            } else {
                System.out.println("Already Exist");
            }
        }
    }

}
