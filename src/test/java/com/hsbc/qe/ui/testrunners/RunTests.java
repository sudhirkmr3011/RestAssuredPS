package com.hsbc.qe.ui.testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm", "pretty", "json:target/cucumber-report/report.json"},
                monochrome = true,
                features = "src/test/resources/features",
                glue = {"com.ee.automation.ui.stepdefinitions"})

public class RunTests {
}
