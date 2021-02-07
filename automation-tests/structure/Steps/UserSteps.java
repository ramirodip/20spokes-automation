package structure.Steps;

import WebPages.DashboardPage;
import WebPages.LoginPage;
import WebPages.ProfileSelectionPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import structure.Framework.Utils.ConfigReader;

public class UserSteps {

    private BaseTestSteps base;
    public UserSteps(BaseTestSteps base)
    {
        this.base=base;
    }
    ProfileSelectionPage profileSelection;
    DashboardPage dashboard;
    LoginPage login;

    @Given("^I want to login as \"([^\"]*)\" profile$")
    public void ValidateTheCheckoutPageWebview(String profile) throws Throwable {
        profileSelection = new ProfileSelectionPage(base.driver);
        profileSelection.SelectProfile(profile);
        base.stepInfo = profile;
    }

    @When("^I perform the login with valid credentials$")
    public void IloginWithValidCredentials() throws Throwable {
        login = new LoginPage(base.driver);
        login.LoginWithCredentials(ConfigReader.GetValue("email"), ConfigReader.GetValue("password"));
    }

    @Then("^I should be able to see the \"([^\"]*)\" page")
    public void IShouldSeeThePage(String page) throws Throwable {
        dashboard = new DashboardPage(base.driver);
        dashboard.Validate_DashboardDisplayed();
    }


}
