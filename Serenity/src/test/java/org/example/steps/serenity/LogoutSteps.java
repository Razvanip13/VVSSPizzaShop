package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.SwagMainPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class LogoutSteps {

    SwagMainPage logoutPage;

    @Step
    public void clickLogoutButton() {
        logoutPage.clickLogoutButton();
    }

    @Step
    public void clickDropdownButton() {
        logoutPage.clickListDropdown();
    }

    @Step
    public void checkCredentialsMessage(String definition) {
        assertThat(logoutPage.getCredentialsText(), containsString(definition));
    }

    @Step
    public void isLoaded() {
        logoutPage.open();
    }

    @Step
    public void computeValidSteps() {
        clickDropdownButton();
        clickLogoutButton();
    }
}
