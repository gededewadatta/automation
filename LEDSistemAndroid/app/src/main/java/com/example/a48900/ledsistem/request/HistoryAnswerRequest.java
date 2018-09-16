package com.example.a48900.ledsistem.request;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author gederanadewadatta
 *
 */

public class HistoryAnswer {
	@JsonProperty("username")
    public String userName;
	@JsonProperty("idquestion")
    public Long idQuestion;
	@JsonProperty("answerquestion")
    public Long answerQuestion; 
	@JsonProperty("createdby")
    public String createdBy;
	@JsonProperty("createddate")
    public String createdDate;
	 
	 
	
}
