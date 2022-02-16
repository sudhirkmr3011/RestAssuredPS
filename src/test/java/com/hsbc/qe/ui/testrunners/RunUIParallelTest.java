package com.hsbc.qe.ui.testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = "src/test/resources/ui/features",
        glue = {"stepdefinitions"},
        plugin = {"pretty", "html:Reports/CucumberReport/cucumber-reports.html", "io.qameta.allure.cucumber6jvm.AllureCucumber7Jvm"}
)
public class RunUIParallelTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
