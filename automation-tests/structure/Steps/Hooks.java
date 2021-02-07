package structure.Steps;
import cucumber.runtime.Utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import cucumber.api.Scenario;
import cucumber.api.java.*;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import structure.Framework.Driver.DriverSetup;
import structure.Framework.Utils.ConfigReader;

public class Hooks {

    private BaseTestSteps base;
    public Hooks(BaseTestSteps base)
    {
        this.base=base;
    }
    WebDriver driver;


    @Before
    public void Initialize()
    {
        driver =  DriverSetup.GetDriver(ConfigReader.GetValue("browser"));
        Reporter.log("Opening the Browser"+ ConfigReader.GetValue("browser"));
        base.driver = driver;
    }

    @After
    public void TearDown(Scenario scenario)
    {
        if (scenario.isFailed()){
            if (driver instanceof TakesScreenshot) {
                TakesScreenshot camera = (TakesScreenshot) driver;
                byte[] screenshot = camera.getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
        }
        scenario.write("url: " + driver.getCurrentUrl());
        driver.quit();
    }

}
