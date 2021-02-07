package structure.Framework.base;
import org.openqa.selenium.*;
import org.testng.Assert;
import structure.Framework.Utils.ConfigReader;
import structure.Framework.Utils.SeleniumWrapper;

import java.util.concurrent.TimeUnit;

public abstract class BasePageObject {
	
	private WebDriver driver;
	protected SeleniumWrapper seleniumWrapper;
	
	protected BasePageObject(WebDriver _driver){
		this.driver= _driver;
		this.seleniumWrapper = new SeleniumWrapper(_driver);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(ConfigReader.GetValue("pageload")), TimeUnit.SECONDS);
	}

	public BasePageObject(){
	}

	public WebDriver GetDriver()
	{
		return driver;
	}

	protected void ValidateField(String label ,String expected, String actual)
	{
		Assert.assertTrue(actual.contains(expected),
				"The "+label+" in the summary box does not match, expected: "+expected+", actual: "+actual);
	}

	protected void ValidatePage(By _element, String page)
	{
		seleniumWrapper.Assert_ElementVisibility(_element, "The "+page+" page is not displayed, page displayed is url: "+ GetDriver().getCurrentUrl());
	}

}
