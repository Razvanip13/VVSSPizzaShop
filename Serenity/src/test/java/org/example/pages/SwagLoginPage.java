package org.example.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import net.serenitybdd.core.pages.WebElementFacade;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

@DefaultUrl("https://www.saucedemo.com/")
public class SwagLoginPage extends PageObject {

    @FindBy(id = "password")
    private WebElementFacade passwordField;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "login-button")
    private WebElementFacade loginButton;

    public void enterCredentials(String username, String password) {
        passwordField.sendKeys(password);
        usernameField.sendKeys(username);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorMessage() {
        WebElementFacade message = find(By.tagName("h3"));
        return message.getText();
    }

    public String getSuccessMessage() {
        WebElementFacade message = find(By.className("title"));
        return message.getText();
    }
}