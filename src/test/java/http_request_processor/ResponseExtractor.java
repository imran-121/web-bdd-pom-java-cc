package http_request_processor;

import java.util.ArrayList;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import helper.Common.Formatter;
import io.restassured.response.Response;


/*
 * This class extracts responses from the "io.restassured.response.Response"
 * Extracted data is returned in the form required data structures
 * */

public class ResponseExtractor {

	private static Logger logger = LogManager.getLogger("http_request_processor");

	// This method returns user id for the requested user in parameter
	public static int getUserId(Response response, String userName)
	{
		try {
			ArrayList<Integer> userIdList= response.path("findAll{it.username == '"+userName+"'}.id");

			if(userIdList.size() > 0){
				return userIdList.get(0);
			}
			else {
				return 0;
			}
		}catch (Exception e)
		{
			logger.error(Formatter.getExceptMessage(e));
			return 0;
		}

	}

	// This method returns list of all of post id for the requested 
	public static ArrayList<Integer> getPostIdsList(Response response){
		ArrayList<Integer> postIds= response.path("id");
		return postIds;
	}


	// This method returns unique email list in the form of HashSet
	public static HashSet<String> getUiniqueEmailList(Response response){

		ArrayList<String> emailList = response.path("email");
		HashSet<String> emailSet= new HashSet<>(emailList);
		return emailSet;

	}

	
	// This method extracts Status line from the response
	public static String getStatusLine(Response response)
	{
		return response.getStatusLine();
	}

	// This method extracts content type from the response
	public static String getContentType(Response response)
	{
		return response.getContentType();
	}



}
