package EDMSRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/EDMS_DEV_Features",glue="stepDefinitionsForEDMS",
            plugin= {"pretty","json:target/cucumber.json"},
            monochrome=true
             ,dryRun=true
		)
public class TestEDMSRunner {

}
