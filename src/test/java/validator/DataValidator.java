package validator;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.HashSet;

import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

import helper.Common.Formatter;

public class DataValidator {
	private static Logger logger = LogManager.getLogger("validator");

	public static boolean  validateEmailFormatInSet(HashSet<String> emailSet) {
		try {
			if(emailSet.size()==0) {
				return false;
			}else {
				String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

				for(String email : emailSet) {
					if(email.matches(regex)==false) {
						return false;
					}
				}
				return true;
			}
		}catch(Exception e)
		{
			logger.error(Formatter.getExceptMessage(e));
			return false;
		}
	}

	public static boolean  validateJsonSchema(Response response, String jsonSchemaPtah) {
		try {
			String respBody = response.asString();

			if(respBody != "" && jsonSchemaPtah != "") {
				MatcherAssert.assertThat(respBody,matchesJsonSchemaInClasspath(jsonSchemaPtah));
				return true;
			}
			return false;
		}catch(Exception e) {
			logger.error(Formatter.getExceptMessage(e));
			return false;
		}

	}

}
