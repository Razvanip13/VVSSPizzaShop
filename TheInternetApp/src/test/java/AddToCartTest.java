import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class AddToCartTest {

    private final By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By buttonLogin = By.id("login-button");
    private final By cart = By.className("shopping_cart_link");
    private final By list = By.className("inventory_item_name");

    @Test
    public void testSuccessfulAddToCart() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://www.saucedemo.com/";
        driver.get(url);
        driver.findElement(usernameField).sendKeys("standard_user");
        driver.findElement(passwordField).sendKeys("secret_sauce");
        driver.findElement(buttonLogin).click();
        assertTrue(driver.findElement(addToCartButton).getText().contains("ADD TO CART"));
        driver.findElement(addToCartButton).click();
        driver.findElement(cart).click();
        assertTrue(driver.findElement(list).getText().contains("Sauce Labs Backpack"));

        driver.quit();
    }

    @Test(priority = 1)
    public void testUnsuccessfulAddToCart() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://www.saucedemo.com/";
        driver.get(url);
        driver.findElement(usernameField).sendKeys("standard_user");
        driver.findElement(passwordField).sendKeys("secret_sauce");
        driver.findElement(buttonLogin).click();
        assertTrue(driver.findElement(addToCartButton).getText().contains("ADD TO CART"));
        driver.findElement(addToCartButton).click();
        driver.findElement(cart).click();
        assertTrue(driver.findElement(list).getText().contains("Sauce Labs Backpack"));

        driver.quit();
    }

}
