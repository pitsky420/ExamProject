package StepDefinition;

import static org.junit.Assert.assertFalse;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class StatementSteps {

	public WebDriver driver;

	public StatementSteps(BrowserSteps browserSteps) {
		driver = browserSteps.driver;
	}

	@When("user inputs ([^\\\"]*) on account id field$")
	public void user_inputs_account_id_on_account_id_field(String account_id) {
		driver.findElement(By.id("search_account_id")).sendKeys(account_id);
	}

	@When("user inputs {string} on from date")
	public void user_enter_from_date(String from_date) {
		driver.findElement(By.name("from_date")).sendKeys(from_date);
	}

	@When("user inputs {string} on to date")
	public void user_enters_to_date(String to_date) {
		driver.findElement(By.name("to_date")).sendKeys(to_date);
	}

	@When("user clicks search button")
	public void user_clicks_search_button() {
		driver.findElement(By.xpath("/html/body/div/form/button")).click();
	}

	@Then("no statements should be displayed")
	public void no_statements_should_be_displayed() {
		Boolean element = driver.findElements(By.xpath("//*[@id=\"statement_row_0\"]")).size() == 0;
		Assert.assertTrue("statement is displayed", element==true);

	}

	@Then("only ([^\\\"]*) and no ([^\\\"]*) statements should be displayed$")
	public void only_account_id_statements_should_be_displayed(String account_id, String other_account) {
		Assert.assertTrue(driver.getPageSource().contains(account_id));
		Assert.assertFalse(driver.getPageSource().contains(other_account));
	}

	@Then("statement for other dates$ should not be displayed")
	public void statement_for_other_dates$_should_not_be_displayed(DataTable testdata) {
		List<String> dates = testdata.asList(String.class);
		int count = dates.size();
		for(int i=0; i <= (count - 1) ; i++) {
			Assert.assertFalse(driver.getPageSource().contains(dates.get(i)));
		}
	}

	@Then("before balance should be {string}")
	public void before_balance_should_be(String before_balance) {
		Assert.assertTrue("Incorrect balance before is displayed",driver.getPageSource().contains("Balance before:" + " " + before_balance));
	}


	@Then("correct before balance {string} should be displayed")
	public void correct_should_be_displayed(String before_balance) {
		Assert.assertTrue("Incorrect balance before is displayed",driver.getPageSource().contains("Balance before:" + " " + before_balance));	
	}

	@Then("balance after should be {string}")
	public void balance_after_should_be(String after_balance) {
		Assert.assertTrue("Incorrect balance after is displayed",driver.getPageSource().contains("Balance after" + " " + after_balance));
	}

	@Then("correct balance after {string} should be displayed")
	public void correct_balance_after_should_be_displayed(String after_balance) {
		Assert.assertTrue("Incorrect balance after is displayed",driver.getPageSource().contains("Balance after" + " " + after_balance ));
	}
}