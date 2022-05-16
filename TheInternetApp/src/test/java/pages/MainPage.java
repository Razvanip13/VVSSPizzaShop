package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BaseClass{

    private By title= By.className("title");
    private By addButtonBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By addButtonBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private By removeButtonBikeLight = By.id("remove-sauce-labs-bike-light");
    private By basketCounter = By.className("shopping_cart_badge");
    private By buttonCart = By.className("shopping_cart_link");

    public MainPage() {
        PageFactory.initElements(driver, this);
    }
    public String getTitle(){
        return driver.findElement(title).getText();
    }

    public void addBackapackToCart(){
        driver.findElement(addButtonBackpack).click();
    }

    public void addBikeLightToCart(){
        driver.findElement(addButtonBikeLight).click();
    }

    public int getTotalBasketItems() {
        return Integer.parseInt(driver.findElement(basketCounter).getText());
    }

    public void deleteBightLightFromCart(){
        driver.findElement(removeButtonBikeLight).click();
    }

    public CartPage clickCartButton(){
        driver.findElement(buttonCart).click();
        return new CartPage();
    }

}
