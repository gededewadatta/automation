/**
 * 
 */
package led.automation.wsproxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
// [START fs_include_dependencies]
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
// [END fs_include_dependencies]
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author gederanadewadatta
 *
 */
public class ProxyEngine {

	private Firestore db;
	ObjectMapper mapper = new ObjectMapper();
	private SubmitQuestion submit = new SubmitQuestion();
	private HistoryAnswer history = new HistoryAnswer();

	private PendingQuestion data = new PendingQuestion();

	private void generateQuestion(String username) {

		RestTemplate restTemplate = new RestTemplate();
		String body = null;
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(
				"http://localhost:7003/automation/api/search/pendingquestion", HttpMethod.POST, entity, String.class);

		String respons = "{\"arrayJson\"" + ":" + responseEntity.getBody() + "}";
		JSONObject jsonResponse = new JSONObject(respons);
		JSONArray jsonArray = jsonResponse.getJSONArray("arrayJson");
		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject jsonObjVal = jsonArray.getJSONObject(i);
			System.out.println("Json Object Adalah :" + jsonObjVal);
			String createDate = jsonObjVal.getString("createdDate");
			data.setId(jsonObjVal.getString("id"));
			data.setLevel(jsonObjVal.getString("level"));
			data.setAnswer1(jsonObjVal.getString("answer1"));
			data.setAnswer2(jsonObjVal.getString("answer2"));
			data.setAnswer3(jsonObjVal.getString("answer3"));
			data.setAnswer4(jsonObjVal.getString("answer4"));
			data.setAnswer5(jsonObjVal.getString("answer5"));
			data.setCompetency(jsonObjVal.getString("competency"));
			data.setCorrectAnswer(jsonObjVal.getString("correctAnswer"));
			data.setQuestions(jsonObjVal.getString("questions"));
			data.setUserName(jsonObjVal.getString("userName"));

		}
		addPendingQuestions(data);

	}

	private void insertSubmit(SubmitQuestion data) throws JsonProcessingException {
		String body = mapper.writeValueAsString(data);
		String result = null;
		System.out.println("===== INPUT ==== " + body);

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());

		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(
				"http://localhost:7003/led/api/automation/insert/submitquestion", HttpMethod.POST, entity,
				String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {

			System.out.println("Response isinya adalah :" + responseEntity.getBody());
			System.out.println("Response Code isinya adalah :" + responseEntity.getStatusCode());

			if (responseEntity.getStatusCode() == HttpStatus.OK) {

				System.out.println("Response isinya adalah :" + responseEntity.getBody());
				System.out.println("Response Code isinya adalah :" + responseEntity.getStatusCode());

				if (responseEntity.getBody().equals(null) || responseEntity.getBody() == null) {
					result = null;
				} else {
					if (responseEntity.getBody().equals("Failure")) {
						result = String.valueOf(responseEntity.getBody());
					} else {
						result = String.valueOf(responseEntity.getStatusCode());
					}
				}
			}

		}
	}

	private void insertHistory(HistoryAnswer data) throws JsonProcessingException {
		String body = mapper.writeValueAsString(data);
		String result = null;
		System.out.println("===== INPUT ==== " + body);

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());

		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(
				"http://localhost:7003/led/api/automation/insert/historyanswer", HttpMethod.POST, entity, String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {

			System.out.println("Response isinya adalah :" + responseEntity.getBody());
			System.out.println("Response Code isinya adalah :" + responseEntity.getStatusCode());

			if (responseEntity.getStatusCode() == HttpStatus.OK) {

				System.out.println("Response isinya adalah :" + responseEntity.getBody());
				System.out.println("Response Code isinya adalah :" + responseEntity.getStatusCode());

				if (responseEntity.getBody().equals(null) || responseEntity.getBody() == null) {
					result = null;
				} else {
					if (responseEntity.getBody().equals("Failure")) {
						result = String.valueOf(responseEntity.getBody());
					} else {
						result = String.valueOf(responseEntity.getStatusCode());
					}
				}
			}

		}
	}

	private void retrieveEmployee() throws Exception {
		// [START fs_get_all]

		// asynchronously retrieve all users
		ApiFuture<DocumentSnapshot> query = db.collection("question").document("employee").get();
		// ...
		// query.get() blocks on response
		DocumentSnapshot querySnapshot = query.get();
		String username = (String) querySnapshot.get("username");
		// generate question:start:
		this.generateQuestion(username);
		// generate question:stop

		// [END fs_get_all]
	}

	private void retrieveSubmit() throws Exception {
		// [START fs_get_all]

		// asynchronously retrieve all users
		ApiFuture<DocumentSnapshot> query = db.collection("question").document("submit").get();
		// ...
		// query.get() blocks on response
		DocumentSnapshot querySnapshot = query.get();
		String answer = (String) querySnapshot.get("answer");
		String answerStatus = (String) querySnapshot.get("answerStatus");
		String attemptAnswer = (String) querySnapshot.get("attemptAnswer");
		String id = (String) querySnapshot.get("id");
		String idQuestion = (String) querySnapshot.get("idQuestion");
		String userName = (String) querySnapshot.get("userName");
		// save to class SubmitQuestion.java :start
		submit.setAnswer(answer);
		submit.setAnswerStatus(answerStatus);
		submit.setAttemptAnswer(attemptAnswer);
		submit.setId(id);
		submit.setIdQuestion(idQuestion);
		submit.setUserName(userName);
		// save to class SubmitQuestion.java :stop
		// integrate with back-end : start
		insertSubmit(submit);
		// integrate with back-end : stop
		// [END fs_get_all]
	}

	private void retrieveHistory() throws Exception {
		// [START fs_get_all]

		// asynchronously retrieve all users
		ApiFuture<DocumentSnapshot> query = db.collection("question").document("history").get();
		// ...
		// query.get() blocks on response
		DocumentSnapshot querySnapshot = query.get();
		String answerQuestion = (String) querySnapshot.get("answerQuestion");
		String createdBy = (String) querySnapshot.get("createdBy");
		String createdDate = (String) querySnapshot.get("createdDate");
		String id = (String) querySnapshot.get("id");
		String idQuestion = (String) querySnapshot.get("idQuestion");
		String userName = (String) querySnapshot.get("userName");

		// save to class historyanswer:start
		history.setAnswerQuestion(answerQuestion);
		history.setCreatedBy(createdBy);
		history.setCreatedDate(createdDate);
		history.setId(id);
		history.setIdQuestion(idQuestion);
		history.setUserName(userName);
		// save to class historyanswer:stop
		// integrate with back-end : start
		insertHistory(history);
		// integrate with back-end : stop

		// [END fs_get_all]
	}

	private void addPendingQuestions(PendingQuestion pending) {
		// [START fs_add_data_1]
		DocumentReference docRef = db.collection("question").document("pending");
		Map<String, Object> dataPending = new HashMap<>();

		dataPending.put("answer1", pending.getAnswer1());
		dataPending.put("answer2", pending.getAnswer2());
		dataPending.put("answer3", pending.getAnswer3());
		dataPending.put("answer4", pending.getAnswer4());
		dataPending.put("answer5", pending.getAnswer5());
		dataPending.put("competency", pending.getCompetency());
		dataPending.put("correctAnswer", pending.getCorrectAnswer());
		dataPending.put("id", pending.getId());
		dataPending.put("questions", pending.getQuestions());
		dataPending.put("userName", pending.getUserName());
		ApiFuture<WriteResult> result = docRef.set(dataPending);
		try {
			System.out.println("Update time : " + result.get().getUpdateTime());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// [END fs_add_data_1]
	}

}
