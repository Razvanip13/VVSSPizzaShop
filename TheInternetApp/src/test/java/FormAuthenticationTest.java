import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class FormAuthenticationTest {

    private String username = "tomsmith";
    private String password = "SuperSecretPassword!";
    private By usernameField = By.id("username");
    private By statusAlert = By.id("flash");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("#login button");

    @Test
    public void testSuccessfulFormAuthentication() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // open page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);

        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();

        String text = driver.findElement(statusAlert).getText();

        assertTrue(text.contains("You logged into a secure area!"));

        driver.quit();
    }

    @Test
    public void testUnsuccessfuluFormAuthentication() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // open page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);

        driver.findElement(usernameField).sendKeys(username + "2");
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();

        String text = driver.findElement(statusAlert).getText();

        assertTrue(text.contains("Your username is invalid!"));

        driver.quit();


    }
}
