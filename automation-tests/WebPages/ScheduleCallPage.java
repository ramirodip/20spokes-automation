package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import structure.Framework.base.BasePageObject;

public class ScheduleCallPage extends BasePageObject {
    public ScheduleCallPage(WebDriver _driver) {
        super(_driver);
    }

    private By _searchPatient = By.cssSelector("div.form-select input[placeholder*='Search for patient']");
    private By _searchCareManager = By.cssSelector("div.form-select input[placeholder*='Search for care manager']");
    private By _date = By.xpath(".//div[contains(@class,'column is-4-desktop')]//label[contains(@class,'form-label') and contains(text(),'Date')]/following-sibling::div/input[@type='text']");
    private By _time = By.xpath(".//div[contains(@class,'column is-4-desktop')]//label[contains(@class,'form-label') and contains(text(),'Time')]/following-sibling::div/input[@type='text']");
    private By _serviceType = By.xpath(".//div[contains(@class,'column is-4-desktop')]//label[contains(@class,'form-label') and contains(text(),'Service Type')]/..//select");
    private By _submitButton = By.xpath(".//button[text()='Submit']");

    //Selects
    private String _selectPatient = ".//div[contains(@class,'column is-4-desktop')]//label[contains(text(),'Patient')]/following-sibling::div//div[contains(@class,'typeahead__results')]//*[contains(text(),'#TEXT#')]";
    private String _selectCareManager = ".//div[contains(@class,'column is-4-desktop')]//label[contains(text(),'Care Manager')]/following-sibling::div//div[contains(@class,'typeahead__results')]//*[contains(text(),'#TEXT#')]";

    public void ScheduleCall(String patient, String careManager, String date, String time, String serviceType)
    {
        SelectPatient(patient);
        SelectCareManager(careManager);
        SelectServiceType(serviceType);
        SelectDate(date);
        SelectTime(time);
        ClickSubmitButton();
    }

    public void SelectPatient(String patient)
    {
        seleniumWrapper.WriteInput(_searchPatient, patient);
        seleniumWrapper.ClickElement(By.xpath(_selectPatient.replace("#TEXT#", patient)));
    }

    public void SelectCareManager(String careManager)
    {
        seleniumWrapper.WriteInput(_searchCareManager, careManager);
        seleniumWrapper.ClickElement(By.xpath(_selectCareManager.replace("#TEXT#", careManager)));
    }

    public void SelectServiceType(String option)
    {
        seleniumWrapper.SelectDropdownOptions(_serviceType, option);
    }

    public void SelectDate(String date) {
        seleniumWrapper.WriteInput(_date, date);
    }

    public void SelectTime(String time)
    {
        seleniumWrapper.WriteInput(_time, time);
    }

    public void ClickSubmitButton()
    {
        seleniumWrapper.ClickElement(_submitButton);
    }
}
