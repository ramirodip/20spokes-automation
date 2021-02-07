package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import structure.Framework.base.BasePageObject;

public class CallsCalendarPage extends BasePageObject {

    public CallsCalendarPage(WebDriver _driver) {
        super(_driver);
    }

    private By _scheduleCallButton = By.xpath(".//button[contains(@class, 'is-secondary') and contains(text(),'Schedule Call')]");

}
