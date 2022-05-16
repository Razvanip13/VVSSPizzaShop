package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

import static org.testng.AssertJUnit.assertEquals;
import static pages.BaseClass.*;
import static pages.BaseClass.prop;

public class AddToCartTest {


    LoginPage loginPage;
    MainPage mainPage;

    @Test
    public void addProductToBasket() {
        loginPage.setUsernameField(prop.getProperty("username"));
        loginPage.setPasswordField(prop.getProperty("password"));
        MainPage mainPage = loginPage.clickLoginButton();
        mainPage.addBackapackToCart();

        assertEquals(mainPage.getTotalBasketItems(), 1);
    }

    @Test
    public void deleteProductFromBasket() {
        loginPage.setUsernameField(prop.getProperty("username"));
        loginPage.setPasswordField(prop.getProperty("password"));
        MainPage mainPage = loginPage.clickLoginButton();
        mainPage.addBackapackToCart();
        mainPage.addBikeLightToCart();

        assertEquals(mainPage.getTotalBasketItems(), 2);

        mainPage.deleteBightLightFromCart();

        assertEquals(mainPage.getTotalBasketItems(), 1);
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
        mainPage = new MainPage();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
