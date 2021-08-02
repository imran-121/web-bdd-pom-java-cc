package helper;


import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/*
 * This class will contains all of the common helper/utility methods 
 * 
 * */

public class Common {
	private static Logger logger = LogManager.getLogger("helper");
	public static class TypeConverter{

		// This method is converting string like "key1:value,key2:value2" to Map data type
		public static Map<String,String> convertStringToLinkedMap(String queryParameters)
		{
			Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();
			try {
				if(queryParameters != "") {
					String[] pairs = queryParameters.split(",");
					for (int i=0;i<pairs.length;i++) {
						String pair = pairs[i];
						String[] keyValue = pair.split(":");
						linkedHashMap.put(keyValue[0], keyValue[1]);
					}
				}
				return linkedHashMap;
			}catch(Exception e){
				logger.error(Formatter.getExceptMessage(e));
				return linkedHashMap ;
			}
		}

	}


	public static class Formatter{

		// This method extracts class name, function name from exception so that we can store in logs 
		public static String getExceptMessage(Exception e)
		{	
			try {
				String className = e.getStackTrace()[0].getMethodName();
				String methodName = e.getStackTrace()[0].getMethodName();
				int lineNo = e.getStackTrace()[0].getLineNumber();
				String message = ">>ClassName = "+className+" >>MethodName = " +methodName+ " >>LineNumber = "+lineNo;
				return message;
			}catch(Exception E)
			{
				return "";
			}
		}

	}





}
