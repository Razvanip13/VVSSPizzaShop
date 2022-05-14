import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class ContextMenuTest {

    private By title = By.cssSelector("h3");
    private By divArea = By.id("hot-spot");

    @Test
    public void testSuccessfulContextMenu() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // open page
        String url = "https://the-internet.herokuapp.com/context_menu";
        driver.get(url);
        String titlu =  driver.findElement(title).getText();;
        assertTrue(titlu.contains("Context Menu"));
        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(divArea)).perform();

        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        assertTrue(message.contains("You selected a context menu"));

        driver.quit();
    }

    @Test
    public void testUnsuccessfulContextMenu() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // open page
        String url = "https://the-internet.herokuapp.com/context_menu";
        driver.get(url);
        String titlu =  driver.findElement(title).getText();
        assertTrue(titlu.contains("Context Menu"));
        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(divArea)).click();

        try {
            Alert alert = driver.switchTo().alert();
            String message = alert.getText();
            assertTrue(message.contains("You selected a context menu"));
        }
        finally {
            driver.quit();
        }
    }
}
