package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import org.example.steps.serenity.LoginSteps;

@RunWith(SerenityRunner.class)
public class SearchByKeywordsLogin {

    @Managed(uniqueSession = true, driver = "chrome")
    public WebDriver webdriver;

    @Steps
    public LoginSteps loginSteps;

    @Issue("#Valid-1")
    @Test
    public void validLoginTest() {
        loginSteps.isLoaded();
        loginSteps.computeSteps("performance_glitch_user", "secret_sauce");
        loginSteps.checkSuccessMessage("PRODUCTS");

    }

    @Test
    public void invalidLoginTest() {
        loginSteps.isLoaded();
        loginSteps.computeSteps("empty", "secret_sauce");
        loginSteps.checkErrorMessage("Epic sadface: Username and password do not match any user in this service");
    }

} 