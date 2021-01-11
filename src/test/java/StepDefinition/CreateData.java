package StepDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateData {

	@Given("statements has been created$")
	public void insertData(DataTable dataTable) {

		List<Map<String, String>> statements = dataTable.asMaps(String.class, String.class);

		for(int i = 0; i < statements.size(); i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			HashMap<String, Object> statement = new HashMap<String, Object>();
			statement.put("account_id",statements.get(i).get("account_id"));
			statement.put("amount", statements.get(i).get("amount"));
			statement.put("currency", statements.get(i).get("currency"));
			statement.put("date", statements.get(i).get("date"));

			map.put("statement", statement);

			RequestSpecification request = RestAssured.given()
					.header("Content-Type", "application/json")
					.contentType(ContentType.JSON)
					.body(map);

			Response response = request.post("http://localhost:9999/statements");

			int statusCode = response.getStatusCode();
			Assert.assertEquals(204, statusCode);

		}

	}

}
