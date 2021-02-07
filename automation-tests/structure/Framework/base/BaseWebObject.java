package structure.Framework.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import structure.Framework.Utils.SeleniumWrapper;

public abstract class BaseWebObject {
	
	private WebDriver driver;
	protected SeleniumWrapper seleniumWrapper;
	private WebElement context;
	
	protected BaseWebObject(WebDriver driver, WebElement context){
		//Assert.assertTrue(context.isDisplayed(), "the WebObject is not visible");
		this.driver= driver;
		this.context = context;
		this.seleniumWrapper  = new SeleniumWrapper(driver);
	}
	
	protected WebElement FindElementInObject(By simpleBy) 
	{
		return seleniumWrapper.FindElementWithinContext(simpleBy, context);
	}

	public void SelectDropdownOptionByText(By _dropdown,By _dropdownMenuItems, String option)
	{
		FindElement(_dropdown).click();
		seleniumWrapper.SmallWait(1000);/**added this timeout cause sometimes dropdown items take longer than expected to load*/
		List<WebElement> dropdownMenu =  WaitAndFindElementsInObject(_dropdownMenuItems);
		seleniumWrapper.SmallWait(1000);/**added this timeout cause sometimes dropdown items take longer than expected to load*/
		dropdownMenu.stream().filter(x->x.getAttribute("innerText").contains(option)).findFirst().get().click();
	}
	
	protected WebElement FindElement(By simpleBy) 
	{
		return seleniumWrapper.FindElement(simpleBy);
	}
	
	protected void ClickElementInObject(By simpleBy)
	{
		seleniumWrapper.FindElementWithinContext(simpleBy, context).click();
	}	
	
	protected void SendKeysToElementInObject(By simpleBy, String keys) 
	{
		seleniumWrapper.WaitAndFind(simpleBy).sendKeys(keys);
	}
	
	protected List<WebElement> WaitAndFindElementsInObject(By simpleBy) 
	{
		return seleniumWrapper.WaitAndFindElements(simpleBy);
	} 
	
	public void KeyboardPress(By simpleBy, String keys)
	{
		seleniumWrapper.KeyboardPress(simpleBy, keys);
	}
	
	protected void ClickElement(By simpleBy)
	{
		seleniumWrapper.WaitAndFind(simpleBy).click();
	}
	
	protected Boolean ValidateElementVisibility(By simpleBy)
	{
		return seleniumWrapper.ValidateElementVisibility(simpleBy);
	}

	
	protected WebDriver GetDriver()
	{
		return seleniumWrapper.GetDriver();
	}

	protected WebElement GetContext()
	{
		return this.context;
	}

	protected void ValidateField(String label ,String expected, String actual)
	{
		Assert.assertTrue(actual.contains(expected),
				"The "+label+" in the summary box does not match, expected: "+expected+", actual: "+actual);
	}
	
}
