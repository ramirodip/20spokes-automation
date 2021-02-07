package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import structure.Framework.base.BasePageObject;

public class LoginPage extends BasePageObject {

    public LoginPage(WebDriver _driver) {
        super(_driver);
    }

    private By _emailInput = By.id("patient_email");
    private By _password = By.id("patient_password");
    private By _submit = By.xpath(".//input[@type='submit']");
    private By _forgotPasswordLink = By.xpath(".//a[contains(@class,'small-link') and contains(text(),'Forgot Password')]");
    private By _selectDifferentUser = By.xpath(".//a[contains(@class,'small-link') and contains(text(),'Login as a different type of user')]");

    //validations
    private By _emailError = By.xpath(".//input[@type='email']/following-sibling::p");
    private By _passwordError = By.xpath(".//input[@type='password']/following-sibling::p");

    public void LoginWithCredentials(String user, String password)
    {
        seleniumWrapper.WriteInput(_emailInput, user);
        seleniumWrapper.WriteInput(_password, password);
        seleniumWrapper.ClickElement(_submit);
    }

    public void ClickSelectDifferentUser()
    {
        seleniumWrapper.ClickElement(_selectDifferentUser);
    }

    public void ForgotPassword()
    {
        seleniumWrapper.ClickElement(_forgotPasswordLink);
    }

    public void Assert_EmailErrorDisplayed()
    {
        seleniumWrapper.Assert_ElementVisibility(_emailError, "The Email Error is not displayed");
    }

    public void Assert_PasswordErrorDisplayed()
    {
        seleniumWrapper.Assert_ElementVisibility(_passwordError, "The Password Error is not displayed");
    }
}
