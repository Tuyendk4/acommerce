package com.acommerce.core.webservice;

import com.acommerce.constants.Constants;
import com.acommerce.core.webservice.APIConstants.ApiMethods;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class RestAPI {
  private RequestSpecBuilder builder = new RequestSpecBuilder();
  private String method;
  public String url;

  public RestAPI(String uri, String method, String token) {
    this.url = Constants.HOST + uri;
    this.method = method;

    if(token != null) {
      builder.addHeader("X-Subject-Token", token);
    }
  }

  public ResponseOptions<Response> send() {
    RequestSpecification requestSpecification = builder.build();
    RequestSpecification request = RestAssured.given();
    request.contentType(ContentType.JSON);
    request.spec(requestSpecification);

    if(this.method.equalsIgnoreCase(ApiMethods.POST)) {
      return request.post(this.url);
    } else if(this.method.equalsIgnoreCase(ApiMethods.PUT)) {
      return request.put(this.url);
    } else if(this.method.equalsIgnoreCase(ApiMethods.GET)) {
      return request.get(this.url);
    } else if(this.method.equalsIgnoreCase(ApiMethods.DELETE)) {
      return request.delete(this.url);
    }
    return null;
  }
}
