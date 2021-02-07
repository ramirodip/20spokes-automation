package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import structure.Framework.base.BasePageObject;

public class ProfileSelectionPage extends BasePageObject {

    public ProfileSelectionPage(WebDriver _driver) {
        super(_driver);
    }

    private String _profileSelectionButton = ".//a[contains(@class,'button') and contains(text(),'#TEXT#')]";

    public void SelectProfile(String profile)
    {
        seleniumWrapper.ClickElement(By.xpath(_profileSelectionButton.replace("#TEXT#", profile)));
    }
}
