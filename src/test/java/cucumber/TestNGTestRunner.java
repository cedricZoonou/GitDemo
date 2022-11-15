package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="FirstAutomation.stepDefinition", monochrome=true, tags = "@Regression", plugin= {"html:Reports/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

	
}
