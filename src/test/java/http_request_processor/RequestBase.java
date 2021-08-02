package http_request_processor;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*
 * This is Base class for all of the classes which will handle http requests (GET,POST, DELETE)
 * This class and the the derived classes are wrapper to restassured API
 * */
public class RequestBase {
	
	public Response response;
	public RestAssured restAssured;
	public RequestSpecification httpRequest;
	
	public RequestBase(){
		this.restAssured = new RestAssured();
		this.restAssured.baseURI = "https://jsonplaceholder.typicode.com";
		this.httpRequest = restAssured.given();
	}

}
