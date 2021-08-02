package http_request_processor;

import java.util.Map;

import helper.Common.TypeConverter;
import io.restassured.response.Response;

/*
 * This class is derived from RequestBase and is working as Requset Generator
 * It only generates the GET requests and returns response of type "io.restassured.response.Response"
 * */

public class GetReqGenerator extends RequestBase{

		
		public Response sendHttpGetReq(String endpoint ){
			
			return sendHttpGetRequestProcessor(endpoint,"","");
			
		}
		
		public Response sendHttpGetReq(String endpoint, String queryPara){
			
			return sendHttpGetRequestProcessor(endpoint,"",queryPara);
			
		}
		
		public Response sendHttpGetReq(String endpoint, String pathParameters, boolean noQueryPara){
					
			return sendHttpGetRequestProcessor(endpoint,pathParameters,pathParameters="");
			
		}
		
		// This is core method of this class, It and is used in this class only
		// Its handles all of the Get requests
		private Response sendHttpGetRequestProcessor(String endpoint, String pathParameters, String queryPara)
		{
			Map<String,String> queryParasMap = TypeConverter.convertStringToLinkedMap(queryPara);
			Map<String,String> queryPathMap = TypeConverter.convertStringToLinkedMap(pathParameters);
			this.httpRequest.pathParams(queryPathMap);
			this.httpRequest.queryParams(queryParasMap);
			return this.httpRequest.get(endpoint);	
		}
		
}
