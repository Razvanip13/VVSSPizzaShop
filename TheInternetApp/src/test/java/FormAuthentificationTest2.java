import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class FormAuthentificationTest2 {

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By buttonLogin = By.id("login-button");
    private By title = By.className("title");
    private By error = By.cssSelector("h3");

    @Test
    public void testSuccessfulFormAuthentication() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // open page
        String url = "https://www.saucedemo.com/";
        driver.get(url);

        driver.findElement(usernameField).sendKeys("standard_user");
        driver.findElement(passwordField).sendKeys("secret_sauce");
        driver.findElement(buttonLogin).click();

        String text = driver.findElement(title).getText();

        assertTrue(text.contains("PRODUCTS"));

        driver.quit();
    }

    @Test
    public void testUnsuccessfulFormAuthentication() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // open page
        String url = "https://www.saucedemo.com/";
        driver.get(url);

        driver.findElement(usernameField).sendKeys("standard_user");
        driver.findElement(passwordField).sendKeys("alahalah");
        driver.findElement(buttonLogin).click();

        String text = driver.findElement(error).getText();

        assertTrue(text.contains("Epic sadface: Username and password do not match any user in this service"));

        driver.quit();
    }
}
