package StepDefinition;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.datatable.DataTable;

public class HtmlTableValidationPage {

	private final WebDriver driver;

	public HtmlTableValidationPage(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyHtmlTableData(DataTable dataTable) {
		WebElement htmlTableElement = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/table"));
		
		List<WebElement> rowElements = htmlTableElement.findElements(By.xpath(".//tr")); //get all of the row WebElements from the table
		rowElements.remove(0); //remove the "header" row from the list of row WebElements

		List<List<String>> dataTableRows = dataTable.asLists(); //outer List<> is rows, inner List<> is cells
		for (List<String> row : dataTableRows) { //loop through every row in the DataTable input
			int rowIdx = dataTableRows.indexOf(row);
			WebElement rowElem = rowElements.get(rowIdx); //get the row WebElement based on the index of the current row in the DataTable
			List<WebElement> cellElements = rowElem.findElements(By.xpath(".//td")); //get all of the cells from the row WebElement

			for (String expectedCell : row) { //loop through every cell in the current DataTable row
				int cellIdx = row.indexOf(expectedCell);
				String actualCell = cellElements.get(cellIdx).getText();

				/*
                System.out.println for demonstration purposes
				 */
				System.out.println("DataTable row " + rowIdx + ", cell " + cellIdx + ": " + expectedCell);
				System.out.println("Actual value on the page: " + actualCell);

				Assert.assertEquals("Expected value of cell should match actual value of cell",
						expectedCell, actualCell);
			}
		}
	}

}
