package admin.fe.constant;

public enum QuestionType {

    YESNO("0","Yes/No"),
    FREETEXT("1","Free Text"),
    OPTIONAL("2","Optional");

    private QuestionType(String code, String value){

            this.code=code;
            this.value=value;
    }

    private String code;

    private String value;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
}

