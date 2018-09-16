/**
 * 
 */
package com.example.a48900.ledsistem.response;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;


/**
 * @author gederanadewadatta
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonResponse {
   @JsonProperty("httpcode")
   public String httpcode;
   @JsonProperty("httpmessage")
   public String httpmessage;
	 
	
}
