package starter;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

/*
 * This the class from where execution of test cases starts
 * Here we can configure
 * 1) Reporting formats and destination
 * 2) Configuring features
 * 3) test case filtration on the bases of tag
 **/

@RunWith(Cucumber.class)
@CucumberOptions(
					features="src/test/resources/Features", 
					monochrome = true, 
					glue = {"steps_binder"}, // It will looks for step definition in "steps_binder" package
					tags = "@regression or @smoke or @sanity",
					plugin = {
								"pretty","html:target/test/reports/htmlFormat/test_report.html", 
								"pretty","json:target/test/reports/jsonFormat/test_report.json"
							 },
					publish = false
		         )

public class Runner {

}

