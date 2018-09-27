package led.com.myapplication.request;



import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author gederanadewadatta
 *
 */
public class SubmitQuestionRequest {
	@JsonProperty("username")
    public String userName;
	@JsonProperty("idquestion")
    public Long idQuestion;
	@JsonProperty("attemptanswer")
    public Long attemptAnswer;
	@JsonProperty("answerstatus")
    public String answerStatus;
	@JsonProperty("createddate")
    public String createdBy;
	@JsonProperty("createddate")
    public String createdDate;
	 
	 
	
	
	
	
	
}
