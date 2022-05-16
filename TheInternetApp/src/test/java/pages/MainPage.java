package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BaseClass{

    private By title= By.className("title");
    private By buttonOptions = By.id("react-burger-menu-btn");
    private By buttonLogout = By.id("logout_sidebar_link");

    public MainPage() {
        PageFactory.initElements(driver, this);
    }
    public String getTitle(){
        return driver.findElement(title).getText();
    }

    public LoginPage clickLogoutButton(){
        driver.findElement(buttonOptions).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonLogout));

        driver.findElement(buttonLogout).click();

        return new LoginPage();
    }
}
