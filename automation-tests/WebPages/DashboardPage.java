package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import structure.Framework.base.BasePageObject;

public class DashboardPage extends BasePageObject {
    public DashboardPage(WebDriver _driver) {
        super(_driver);
    }

    private String _callsScheduledToday = ".//p[contains(@class,'performance__title') and contains(text(),'#TEXT#')]/following-sibling::p";
    private By indentifier = By.cssSelector("div.dashboard-panel");
    private By _viewCallSchedule = By.cssSelector("a.dashboard-calls__link");

    public String GetValueFromDashboard(String value)
    {
        return seleniumWrapper.WaitAndFind(By.xpath(_callsScheduledToday.replace("#TEXT#", value))).getText();
    }

    public void Validate_DashboardDisplayed()
    {
        seleniumWrapper.Assert_ElementVisibility(indentifier, "The Dashboard Page");
    }

    public void ClickScheduleCall()
    {
        seleniumWrapper.ClickElement(_viewCallSchedule);
    }
}
