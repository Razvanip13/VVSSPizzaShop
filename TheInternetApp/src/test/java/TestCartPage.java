import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.MainPage;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static pages.BaseClass.*;
import static pages.BaseClass.prop;

public class TestCartPage {


    LoginPage loginPage;
    MainPage mainPage;
    CartPage cartPage;

    @Test
    public void buyProducts(){
        loginPage.setUsernameField(prop.getProperty("username"));
        loginPage.setPasswordField(prop.getProperty("password"));
        MainPage mainPage = loginPage.clickLoginButton();
        mainPage.addBackapackToCart();

        CartPage cartPage = mainPage.clickCartButton();

        cartPage.chekoutOrder();
        cartPage.fillData();
        cartPage.continueOrder();
        cartPage.finishOrder();

        assertTrue(cartPage.orderCompleted());
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
