package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BaseClass {

    private final By title = By.className("title");
    private final By addButtonBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private final By addButtonBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private final By removeButtonBikeLight = By.id("remove-sauce-labs-bike-light");
    private final By basketCounter = By.className("shopping_cart_badge");
    private final By buttonCart = By.className("shopping_cart_link");
    private final By buttonOptions = By.id("react-burger-menu-btn");
    private final By buttonLogout = By.id("logout_sidebar_link");

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public void addBackapackToCart() {
        driver.findElement(addButtonBackpack).click();
    }

    public void addBikeLightToCart() {
        driver.findElement(addButtonBikeLight).click();
    }

    public int getTotalBasketItems() {
        return Integer.parseInt(driver.findElement(basketCounter).getText());
    }

    public void deleteBightLightFromCart() {
        driver.findElement(removeButtonBikeLight).click();
    }

    public CartPage clickCartButton() {
        driver.findElement(buttonCart).click();
        return new CartPage();
    }

    public LoginPage clickLogoutButton() {
        driver.findElement(buttonOptions).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonLogout));

        driver.findElement(buttonLogout).click();

        return new LoginPage();
    }
}
