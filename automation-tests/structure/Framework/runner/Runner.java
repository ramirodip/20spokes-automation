package structure.Framework.runner;

import cucumber.api.*;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(features={"shared/webtests/features/set1/symphony", "shared/webtests/features/set2/elysium", "shared/webtests/features/set3/tms"}, plugin = {"pretty","json:target/cucumber.json", "html:target/html-report"}, glue = {"shared.webtests.Steps"})
public class Runner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}