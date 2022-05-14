package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.LoginSteps;
import org.example.steps.serenity.LogoutSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class SearchByKeywordsMain {

    @Managed(uniqueSession = true, driver = "chrome")
    public WebDriver webdriver;

    @Steps
    public LogoutSteps logoutSteps;

    @Steps
    public LoginSteps loginSteps;

    @Issue("#Valid-2")
    @Test
    public void validLogoutTest() {

        login();
        logoutSteps.isLoaded();
        logoutSteps.computeValidSteps();
        logoutSteps.checkCredentialsMessage("Accepted usernames are:");

    }

    @Test
    public void invalidLogoutTest() {
        logoutSteps.isLoaded();
        logoutSteps.computeValidSteps();
        logoutSteps.checkCredentialsMessage("Accepted usernames are:");
    }

    private void login() {
        loginSteps.isLoaded();
        loginSteps.typeCredentials("standard_user", "secret_sauce");
        loginSteps.clickLoginButton();
    }
}
