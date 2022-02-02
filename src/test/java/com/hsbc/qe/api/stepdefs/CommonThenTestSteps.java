//package com.hsbc.qe.api.stepdefs;
//
//import com.hsbc.qe.api.enums.ApiContext;
//import com.hsbc.qe.api.httpservicemanager.HttpRequestManager;
//import com.hsbc.qe.api.httpservicemanager.HttpServiceAssertion;
//import com.hsbc.qe.api.httpservicemanager.RestRequestManager;
//import com.hsbc.qe.api.util.api.ApiUtilManager;
//import com.hsbc.qe.utils.JsonUtil;
//import io.cucumber.datatable.DataTable;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Then;
//
//import java.io.IOException;
//import java.util.Map;
//
//public class CommonThenTestSteps {
//
//  HttpServiceAssertion httpServiceAssertion;
//  TestManagerContext testManagerContext;
//  RestRequestManager restRequestManager;
//  HttpRequestManager httpRequestManager;
//  ApiUtilManager apiUtilManager;
//
//  public CommonThenTestSteps(TestManagerContext context) {
//    testManagerContext = context;
//    this.httpServiceAssertion = new HttpServiceAssertion(testManagerContext.getHttpResponse());
//    restRequestManager = testManagerContext.getRestRequest();
//    httpRequestManager = testManagerContext.getHttpRequest();
//    apiUtilManager = new ApiUtilManager();
//  }
//
//  @Then("I verify response code is {int}")
//  public void iVerifyReponseCodeIs(int statusCode) {
//    httpServiceAssertion.statusCodeIs(statusCode);
//  }
//
//  @And("I verify fields in response")
//  public void iVerifyFieldsInResponse(DataTable table) {
//    table
//        .asMap(String.class, String.class)
//        .entrySet()
//        .stream()
//        .skip(1)
//        .forEach(
//            (entry) -> {
//              httpServiceAssertion.bodyContainsPropertyWithValue(
//                  (String) entry.getKey(),
//                  JsonUtil.getNodeValue(
//                      testManagerContext.getScenarioContext().getContext(ApiContext.REQUEST_BODY).toString(),
//                      (String) entry.getValue()));
//            });
//  }
//
//  @And("I verify {string} in Response")
//  public void iVerifyResponse(String customer) {
//    String request = (String) testManagerContext.getScenarioContext().getContext(ApiContext.REQUEST_BODY);
//    Map<String, Object> map = apiUtilManager.getSchema(customer, (String) testManagerContext.getScenarioContext().getContext(ApiContext.API_NAME));
//    map.forEach((key, value) -> {
//        httpServiceAssertion.bodyContainsPropertyWithValue(key,
//                JsonUtil.getNodeValue(request, (String) value));
//    });
//  }
//
//
//  @And("I clear the request body")
//  public void iClearTheRequestBody() {
//    restRequestManager.clearRequestBody();
//    httpRequestManager.body("");
//  }
//
//  @And("I clear the request headers")
//  public void iClearTheRequestHeaders() throws IOException {
//    restRequestManager.clearRequestHeader();
//  }
//
//  @And("I clear the query parameters")
//  public void iClearTheQueryParameters() {
//    restRequestManager.clearRequestParam();
//  }
//
//}
