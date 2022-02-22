package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.common.utils.db.MySQLDBUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrebuiltDBSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrebuiltBrowserSteps.class);

    @Given("I connect to the database")
    public void connectToDatabase() {
        MySQLDBUtils.connectToDB();
    }

    @When("I run the UPDATE SQL query: {string} to update the database table")
    public void runSQLQuery(String sqlQuery) {
        MySQLDBUtils.updateInTable(sqlQuery);
    }

    @Then("I run the SELECT SQL query: {string} to check that the table is updated with value {string}")
    public void iClickTheElementOnThePage(String sqlQuery, String columnName, String expectedValue) throws SQLException {
        ResultSet resultSet = MySQLDBUtils.selectFromTable(sqlQuery);
        String actualValue = resultSet.getString(columnName);
        assertThat("Table column data is not matching", actualValue, is(equalTo(expectedValue)));
    }

    @Given("I close the database connection")
    public void closeTheDatabaseConnection() {
        MySQLDBUtils.closeConnection();
    }
}
