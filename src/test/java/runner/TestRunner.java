package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/features",
        glue = {"stepdefs"},
        tags = {"@success_test,@error"},
        plugin = {"html:target/cucumber-reports","rerun:target/rerun.txt","html:target/cucumber-reports/cucumber-pretty","json:target/cucumber-reports/CucumberTestReport.json","com.cucumber.listener.ExtentCucumberFormatter:target/ExtentReport.html"},
        strict = true,dryRun = false)
public class TestRunner extends AbstractTestNGCucumberTests {
    private cucumber.api.testng.TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new cucumber.api.testng.TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "carFeature", description = "Runs Cucumber Feature", dataProvider = "featuresData")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] featuresData() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }
}
