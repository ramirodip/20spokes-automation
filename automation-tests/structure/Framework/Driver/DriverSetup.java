package structure.Framework.Driver;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Reporter;
import structure.Framework.Utils.ConfigReader;

public class DriverSetup {

	public static SessionId sessionId ;
	public static WebDriver browser;

	public static String GetHostname()
	{
		String hostname = "Unknown";
		try
		{
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			hostname = addr.getHostName();
		}
		catch (UnknownHostException ex)
		{
			System.out.println("Hostname can not be resolved");
		}
		return hostname;
	}

	public static WebDriver GetDriver(String driver)
	{
		String hostname = GetHostname();
		switch(driver) {
			case "grid":
				if (hostname.equals("devci.logis9.net")) {
					Reporter.log("Setting up the remote driver");
					try{
						browser = GetRemoteDriver();
					}
					catch (MalformedURLException e){e.printStackTrace();}
				}
				else
				{
					Reporter.log("Setting up Chrome Driver");
					browser = GetChromeDriver(false);
					break;
				}
				break;
			case "chrome":
				Reporter.log("Setting up Chrome Driver");
				browser = GetChromeDriver(false);
				break;
			case "headless-chrome":
				Reporter.log("Setting up Headless Chrome Driver");
				browser = GetChromeDriver(true);
				break;
			case "firefox":
				Reporter.log("Setting up Firefox Driver");
				browser= GetFirefoxDriver();
				break;
			case "stack":
				String USERNAME = "ramyasarveshwar3";
				String AUTOMATE_KEY = "it6im4kdd8yRu1dmu8Kp";
				String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("browser", "Firefox");
				caps.setCapability("browser_version", "64.0");
				caps.setCapability("os", "OS X");
				caps.setCapability("os_version", "High Sierra");
				caps.setCapability("resolution", "1024x768");
				try
				{
					//setBrowser(new RemoteWebDriver(new URL(URL), caps));
					browser = new RemoteWebDriver(new URL(URL), caps);
					sessionId  = ((RemoteWebDriver) browser).getSessionId();
				}
				catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(-1);
				}
				break;
		}
		return ConfigureWaits(browser);
	}
	
    private static WebDriver GetFirefoxDriver()
    {
    	FirefoxOptions options = new FirefoxOptions();
    	String strFFBinaryPath = "C:\\Program Files (x86)\\Mozilla Firefox/firefox.exe";
    	options.setBinary(strFFBinaryPath);
    	return new FirefoxDriver(options);
    }
    
    private static WebDriver GetChromeDriver(boolean headless)
    {
		String path = "";
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")){
			path = "C:\\webDrivers\\chromedriver.exe";
		}
		else if (os.contains("mac os x")){
			path = "/Users/opus9/webdrivers/chromedriver";
		}
		else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
			path ="/home/webdrivers/chromedriver";
		}
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("start-maximized");
    	if(headless) {
			options.addArguments("--headless");
			System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		}
		else {
			System.setProperty("webdriver.chrome.driver", path);
		}
    	return new ChromeDriver(options);
    }

    private static WebDriver GetRemoteDriver() throws MalformedURLException {
		System.out.println("running test case on grid");
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName("chrome");
		capability.setCapability("screenResolution", "1366x768");/**768p to have support on jenkins job*/
		capability.setCapability("recordVideo", false);
		capability.setCapability("idleTimeout", 150);
		return new RemoteWebDriver (new URL(ConfigReader.GetValue("grid")), capability);
	}
    
    private static WebDriver ConfigureWaits(WebDriver driver)
    {
    	driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(ConfigReader.GetValue("pageload")), TimeUnit.SECONDS);
    	driver.manage().deleteAllCookies();
    	return driver;
    }

}
