package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BaseClass{


    private By buttonCheckout  = By.id("checkout");
    private By buttonContinue  = By.id("continue");
    private By buttonFinish  = By.id("finish");
    private By fieldFirstName = By.id("first-name");
    private By fieldLastName = By.id("last-name");
    private By fieldPostalCode = By.id("postal-code");
    private By divCompleteOrder = By.className("complete-text");


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

    public void fillData(){
        driver.findElement(fieldFirstName).sendKeys("Sheldon");
        driver.findElement(fieldLastName).sendKeys("Sheldon");
        driver.findElement(fieldPostalCode).sendKeys("123123");
    }

    public boolean orderCompleted(){
        return driver.findElement(divCompleteOrder).getText().equals("Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }
}
