package StepDefinition;


import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.net.Urls;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class BrowserSteps {

	public WebDriver driver;
	
	
	@Before
	public WebDriver openBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\paul_\\eclipse-workspace\\ExamProject\\src\\test\\resources\\drivers\\chromedriver.exe");
		if(driver==null)
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;

	}

	@After
	public void closeBrowser() {
		driver.close();
		driver.quit();
		driver=null;
	}

	@Given("user goes to website {string}")
	public void user_goes_to_website(String url) {
		driver.get(url);
	}
	

}
