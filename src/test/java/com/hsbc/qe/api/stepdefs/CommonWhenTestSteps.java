package com.hsbc.qe.api.stepdefs;

import com.hsbc.qe.api.httpservicemanager.HttpResponseManager;
import com.hsbc.qe.api.httpservicemanager.RestRequestManager;
import com.hsbc.qe.api.enums.ApiContext;
import com.hsbc.qe.api.util.ApiParseJson;
import com.hsbc.qe.api.util.ApiUtilManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommonWhenTestSteps {

  HttpResponseManager httpResponseManager;
  TestManagerContext testManagerContext;
  RestRequestManager restRequestManager;
  static List<Object> Api_List = new ArrayList<Object>();

  public CommonWhenTestSteps(TestManagerContext context) {
    testManagerContext = context;
    httpResponseManager = testManagerContext.getHttpResponse();
    restRequestManager = testManagerContext.getRestRequest();
  }

  @When("^the client performs (.+) request on API \"(.+)\"$")
  public void perform_Http_Request(String httpMethod, String url) throws Throwable {
    httpResponseManager.setResponsePrefix("");
    ApiUtilManager apiUtilManager = new ApiUtilManager();
    httpResponseManager.setReponse(httpResponseManager.doRequest(httpMethod, apiUtilManager.getBasePath(url)));
  }

  @When("I call method {string}")
  public void iCallMethodPOST(String httpMethod) throws Exception {
    httpResponseManager.setResponsePrefix("");
    String basePath = (String) testManagerContext.getScenarioContext().getContext(ApiContext.BASE_PATH);
    System.out.println("basepath123 "+basePath);
    httpResponseManager.setReponse(httpResponseManager.doRequest(httpMethod, basePath));
  }

  @When("I Fetch the response value with key {string}")
  public void fetchKeyValue(String key) throws Exception {
    httpResponseManager.getResponse().asString();
    String body = httpResponseManager.getResponse().asString();
//    System.out.println(body.asString());

    JSONObject response_json = new JSONObject(body);

    ApiParseJson.getKey(response_json,key);
    Api_List=ApiParseJson.Api_List_Elements;
    System.out.println("Api list values "+Api_List);
  }

  @And("I get the response")
  public void iGetTheResponse() {
    System.out.println("response is "+httpResponseManager.getResponse().asString());
    testManagerContext
        .getScenarioContext()
        .setContext(ApiContext.RESPONSE_BODY, httpResponseManager.getResponse().asString());
  }

    @And("I save the initial response")
    public void iSaveTheInitialResponse() {
      testManagerContext
              .getScenarioContext()
              .setContext(ApiContext.INITIAL_RESPONSE_BODY, httpResponseManager.getResponse().asString());
    }
}
