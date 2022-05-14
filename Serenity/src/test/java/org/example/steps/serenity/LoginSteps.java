package org.example.steps.serenity;

import org.example.pages.SwagLoginPage;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class LoginSteps {

    SwagLoginPage loginPage;

    @Step
    public void typeCredentials(String username, String password) {
        loginPage.enterCredentials(username, password);
    }

    @Step
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Step
    public void checkSuccessMessage(String definition) {
        assertThat(loginPage.getSuccessMessage(), containsString(definition));
    }

    @Step
    public void checkErrorMessage(String definition) {
        assertThat(loginPage.getErrorMessage(), containsString(definition));
    }

    @Step
    public void isLoaded() {
        loginPage.open();
    }

    @Step
    public void computeSteps(String username, String password) {
        typeCredentials(username, password);
        clickLoginButton();
    }
}