package structure.CommonUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import structure.Framework.base.BasePageObject;

public class GmailUI extends BasePageObject {

    public GmailUI(WebDriver _driver) {
        super(_driver);
    }

    private By _inputEmail = By.cssSelector("input[type=email]");

    private By _inputPassword = By.cssSelector("input[type=password]");

    private By _searchQuery = By.cssSelector("input[name=q]");

    private By _mailRows = By.xpath("(.//tbody//tr[@draggable='true']//descendant::div[@role='link'])[last()]");

    private By _buttonNext = By.id("identifierNext");

    private By _buttonPasswordNext = By.id("passwordNext");

    private By _searchbutton = By.cssSelector("button[aria-label='Search Mail']");

    private String _linkButton = ".//a[contains(text(),'#TEXT#')]";

    public void LoginGmail(String user, String pass)
    {
        seleniumWrapper.WriteInput(_inputEmail, user);
        seleniumWrapper.ClickElement(_buttonNext);
        seleniumWrapper.WriteInput(_inputPassword, pass);
        seleniumWrapper.ClickElement(_buttonPasswordNext);
        seleniumWrapper.Assert_ElementVisibility(_searchQuery, "The login did not return a inbox page");
    }

    public void SearchAndSelectEmail(String searchword)
    {
        seleniumWrapper.SmallWait(20000);
        seleniumWrapper.WriteInput(_searchQuery, searchword);
        seleniumWrapper.SmallWait(2000);
        seleniumWrapper.ClickElement(_searchbutton);
        seleniumWrapper.SmallWait(2000);
        seleniumWrapper.Assert_ElementVisibility(_mailRows, "There are no mails with that search: "+searchword);
        seleniumWrapper.ClickElement(_mailRows);
        seleniumWrapper.SmallWait(2000);
    }

    /** Author: Ramiro
     * this method does not click the button but rather grabs the href of the button desired and redirects the driver
     **/
    public void ClickLinkInsideEmail(String linkName)
    {
        seleniumWrapper.SmallWait(3000);
        String html = GetDriver().getPageSource();
        Document doc = Jsoup.parse(html);
        /**here is grabbing the href attribute of the button with the linkname specified by parameter in order to redirect to the page*/
        if (linkName.equalsIgnoreCase("View other available Opus9 loads")) {
            //seleniumWrapper.ClickElement(By.xpath(_linkButton.replace("#TEXT#",linkName)));
            String link = doc.select("a[href*='https://e.opus9.com/e2t/c/']")
                    .stream().filter(x -> x.text().contains(linkName)).findFirst().get().attr("href");
            GetDriver().navigate().to(link);
        }else{
            String link = doc.select("a[href*='https://e.opus9.com/e2t/c/'][style*=display]")
                    .stream().filter(x -> x.text().contains(linkName)).findFirst().get().attr("href");
            GetDriver().navigate().to(link);
        }
    }

    public boolean ValidateLoginDisplayed()
    {
        return seleniumWrapper.ValidateElementVisibility(_inputEmail);
    }
}
