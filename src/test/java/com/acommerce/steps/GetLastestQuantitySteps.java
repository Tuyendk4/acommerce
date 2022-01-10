package com.acommerce.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import com.acommerce.core.webservice.APIConstants.ApiMethods;
import com.acommerce.core.webservice.RestAPI;
import com.acommerce.pojo.Product;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import java.util.Arrays;

public class GetLastestQuantitySteps {
  private static ResponseOptions<Response> response;

  @Given("I perform GET operation for {string} with token {string}")
  public void iPerformGETOperationFor(String url, String tokenId) {
    RestAPI restApi = new RestAPI(url, ApiMethods.GET, tokenId);
    response = restApi.send();
  }

  @Then("I should see number of quantity less than or equal to {string}")
  public void iShouldSeeNumberOfQuantityAs(String numberOfQuantity) {
    var products = Arrays.asList(response.getBody().as(Product[].class));
    assertThat(products.size(), lessThanOrEqualTo(Integer.parseInt(numberOfQuantity)));
  }

  @Then("I should see {string} in product list")
  public void iShouldSeeInProductList(String sku) {
    boolean foundProduct = false;
    var products = Arrays.asList(response.getBody().as(Product[].class));
    for(Product product: products) {
      if(product.getSku().equals(sku)) {
        System.out.println(product.toString());
        foundProduct = true;
      }
      assertThat(foundProduct, equalTo("true"));
    }
  }

  @Then("I should see response status code as {string}")
  public void iShouldSeeResponseStatusCodeAs(String statusCode) {
    int actualStatusCode = response.getStatusCode();
    assertThat(actualStatusCode, equalTo(Integer.parseInt(statusCode)));
  }

  @Given("I perform POST operation for {string} with token {string}")
  public void iPerformPOSTOperationForWithToken(String url, String tokenId) {
    RestAPI restApi = new RestAPI(url, ApiMethods.POST, tokenId);
    response = restApi.send();
  }
}
