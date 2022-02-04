package com.hsbc.qe.ui.testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (plugin = {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm", "pretty", "json:target/cucumber-report/report.json"},
                monochrome = true,
                features = "src/test/resources/ui/features",
                glue = {"com.hsbc.qe.ui.stepdefinitions"})

public class RunUITest {
}
