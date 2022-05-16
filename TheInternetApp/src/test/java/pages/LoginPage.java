package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BaseClass {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By buttonLogin = By.id("login-button");
    private final By error = By.cssSelector("h3");

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void setUsernameField(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public MainPage clickLoginButton() {
        driver.findElement(buttonLogin).click();
        return new MainPage();
    }

    public boolean assertExistsText(String text) {
        return driver.getPageSource().contains(text);
    }

    public String getErrorMessage() {
        return driver.findElement(error).getText();
    }

}
