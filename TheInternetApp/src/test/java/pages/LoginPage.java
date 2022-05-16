package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass{

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By buttonLogin = By.id("login-button");
    private By title = By.className("title");
    private By error = By.cssSelector("h3");

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void setUsernameField(String username){
        driver.findElement(usernameField).sendKeys(username);

    }

    public void setPasswordField(String password){
        driver.findElement(passwordField).sendKeys(password);

    }

    public MainPage clickLoginButton(){
        driver.findElement(buttonLogin).click();
        return new MainPage();
    }

    public String getErrorMessage(){
        return driver.findElement(error).getText();
    }

}
