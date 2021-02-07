package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import structure.Framework.base.BasePageObject;

public class ForgotPasswordPage extends BasePageObject {
    public ForgotPasswordPage(WebDriver _driver) {
        super(_driver);
    }

    private By _emailInput = By.id("patient_email");
    private By _resetPasswordButton = By.xpath(".//input[@type='submit']");

    public void ResetPassword(String email)
    {
        seleniumWrapper.WriteInput(_emailInput, email);
    }

    public void ClickResetPasswordButton()
    {
        seleniumWrapper.ClickElement(_resetPasswordButton);
    }
}
