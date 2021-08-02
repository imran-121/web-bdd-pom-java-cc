package steps_binder;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import helper.Common.Formatter;
import http_request_processor.GetReqGenerator;
import http_request_processor.ResponseExtractor;
import validator.DataValidator;


/*
 * This class is the steps file for the feature "comment.feature"
 * This class binds the features in "/AutomationBot/src/test/resources/features" to java world
 * Each feature file will have a separate steps file
 * 
 * Idea here is to keep the code minimal here by leveraging the following modules(packages)
 * 	1)Helpers
 * 	2)http_request Generators
 * 	3)starter
 * 	4)validator
 * 
 *  This class uses the services from all of the above modules
 * 
 * */
public class CommentSteps {
    // Creating variable is used for logging the exceptions specific to supplied package "steps_binder"
	private static Logger logger = LogManager.getLogger("steps_binder");

	private GetReqGenerator getReqGen = new GetReqGenerator();


	// Below variables with "store" prefix are used for persisting data, so that we can feed linked steps

	private String storeUserName;
	private Integer storeUserId;
	private ArrayList<Integer> storePostIds;
	private HashSet<String> storeEmail;
	private String storeEndPoint;
	private Response storeResponse;

	// Constructing variables with default values
	public CommentSteps() {
		this.storeUserName = "";
		this.storePostIds = new ArrayList<Integer>();
		this.storeEmail = new HashSet<String>();
		this.storeEndPoint="";
	}


	//###############################################################
	// Steps for Scenario # 1
	//################################################################
	@Given("User has a valid end point {string}")
	public void user_has_a_valid_end_point(String endPoint) {
		this.storeEndPoint = endPoint;

	}
	@When("User sends get request to given end point")
	public void user_sends_get_request_to_given_end_point() {
		try {
			this.storeResponse = this.getReqGen.sendHttpGetReq(storeEndPoint);
		}catch(Exception e)
		{
			logger.error(Formatter.getExceptMessage(e));
		}
	}
	@Then("verify the status line and tontent type should be {string} and {string}")
	public void verify_the_status_line_and_tontent_type_should_be_and(String expStatusLine, String expContentType) {
		try {
			String actContType = ResponseExtractor.getContentType(this.storeResponse);
			String actStatusLine = ResponseExtractor.getStatusLine(this.storeResponse);
			assertTrue(actStatusLine.equals(expStatusLine) && actContType.equals(expContentType));
		}catch(Exception e)
		{
			logger.error(Formatter.getExceptMessage(e));
			assert(false);
		}

	}

	//##################################################################
	// Steps for scenario # 2 (Given and When are in scenario 1)
	//##################################################################

	@Then("verify json schema of given endpoint should be {string}")
	public void verify_json_schema_of_given_endpoint_should_be(String sourceSchemaPath) {
		try {
			// All of the required schema json files are stored at "/AutomationBot/src/test/resources/testData/json"
			boolean result = DataValidator.validateJsonSchema(this.storeResponse, sourceSchemaPath);
			assert(result);
		}
		catch(Exception e) {
			logger.error(Formatter.getExceptMessage(e));
			assert(false);
		}
	}


	//##################################################################
	// Steps for scenario # 3
	//##################################################################
	@Given("With a valid user with user name {string}")
	public void we_have_a_valid_user_with_user_name(String userName) {
		this.storeUserName = userName;
	}

	@Given("Filter and store user id using end point {string} on the basis of user name")
	public void filter_and_store_user_id_using_end_point_on_the_basis_of_user_name(String endPoint) {
		try {
			Response response = this.getReqGen.sendHttpGetReq(endPoint);
			this.storeUserId = ResponseExtractor.getUserId(response, this.storeUserName);
		}catch(Exception e)
		{
			logger.error(Formatter.getExceptMessage(e));
			assert(false);
		}
	}

	@Given("Filter and store all of the post ids using end point {string} on the basis of user id")
	public void filter_and_store_all_of_the_post_using_end_point_on_the_basis_of_user_id(String endPoint) {
		try {
			//Response rep = httpGetReq.sendHttpGetReq("/posts","userId:9");
			String queryPara = "userId:"+ this.storeUserId;
			Response response = this.getReqGen.sendHttpGetReq(endPoint,queryPara);
			this.storePostIds = ResponseExtractor.getPostIdsList(response);
		}catch(Exception e) {
			logger.error(Formatter.getExceptMessage(e));
			assert(false);
		}
	}

	@When("Filter and store all of email values from comments using {string} on the basis of post ids")
	public void filter_and_store_all_of_comments_using_on_the_basis_of_post_ids(String endPoint) {
		try {
			if(this.storePostIds.isEmpty() == false) {
				String pathPara = "";
				Response response;
				HashSet<String> tempEmailList;
				for(Integer postId : this.storePostIds){
					// "post_id:83"
					pathPara = "post_id:" + postId;
					response = this.getReqGen.sendHttpGetReq(endPoint, pathPara, false);
					tempEmailList = ResponseExtractor.getUiniqueEmailList(response);
					if(tempEmailList.isEmpty()==false)
					{
						this.storeEmail.addAll(tempEmailList);
					}
				}

			}
		}catch (Exception e){
			logger.error(Formatter.getExceptMessage(e));
			assert(false);
		}
	}

	@Then("verify all of the email values should be in valid format")
	public void verify_email_value_formatting_after_extracting_from_response() {
		try {
			boolean result = DataValidator.validateEmailFormatInSet(this.storeEmail);
			assertEquals(true,result);
		}catch(Exception e)
		{
			logger.error(Formatter.getExceptMessage(e));
			assert(false);
		}

	}
















}
