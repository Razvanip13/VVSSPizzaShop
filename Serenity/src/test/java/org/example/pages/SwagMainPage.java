package org.example.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

@DefaultUrl("https://www.saucedemo.com/inventory.html")
public class SwagMainPage extends PageObject {

    @FindBy(id = "react-burger-menu-btn")
    private WebElementFacade listField;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;


    public void clickListDropdown() {
        listField.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public String getCredentialsText() {
        WebElementFacade message = find(By.id("login_credentials"));
        return message.getText();
    }

}