package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BaseClass{


    private final By buttonCheckout  = By.id("checkout");
    private final By buttonContinue  = By.id("continue");
    private final By buttonFinish  = By.id("finish");
    private final By fieldFirstName = By.id("first-name");
    private final By fieldLastName = By.id("last-name");
    private final By fieldPostalCode = By.id("postal-code");
    private final By divCompleteOrder = By.className("complete-text");


    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    public void chekoutOrder(){
        driver.findElement(buttonCheckout).click();
    }

    public void continueOrder(){
        driver.findElement(buttonContinue).click();
    }

    public void finishOrder(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonFinish));
        driver.findElement(buttonFinish).click();
    }

    public void fillData(String firstName, String lastName, String postalCode){
        driver.findElement(fieldFirstName).sendKeys(firstName);
        driver.findElement(fieldLastName).sendKeys(lastName);
        driver.findElement(fieldPostalCode).sendKeys(postalCode);
    }

    public String orderCompleted(){
        return driver.findElement(divCompleteOrder).getText();
    }
}
