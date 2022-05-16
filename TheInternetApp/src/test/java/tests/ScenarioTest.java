package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BaseClass;
import pages.CartPage;
import pages.LoginPage;
import pages.MainPage;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ScenarioTest extends BaseClass {

    LoginPage loginPage;

    @Test
    public void performOperations(){

        //login
        loginPage.setUsernameField(prop.getProperty("username"));
        loginPage.setPasswordField(prop.getProperty("password"));
        MainPage mainPage = loginPage.clickLoginButton();
        assertTrue(mainPage.getTitle().contains("PRODUCTS"));

        //add to cart
        mainPage.addBackapackToCart();
        assertEquals(mainPage.getTotalBasketItems(), 1);

        //checkout
        CartPage cartPage = mainPage.clickCartButton();
        cartPage.chekoutOrder();
        cartPage.fillData(prop.getProperty("username"), prop.getProperty("last-name"),
                prop.getProperty("postal-code"));
        cartPage.continueOrder();
        cartPage.finishOrder();
        assertTrue(cartPage.orderCompleted().contains("Your order has been dispatched, and will arrive just as fast as the pony can get there!"));

        //logout
        loginPage = mainPage.clickLogoutButton();
        assertTrue(loginPage.assertExistsText("Accepted usernames are:"));
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
