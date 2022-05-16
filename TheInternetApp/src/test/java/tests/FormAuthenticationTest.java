package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

import static org.testng.AssertJUnit.assertTrue;
import static pages.BaseClass.*;

public class FormAuthenticationTest {

    LoginPage loginPage;

    @Test
    public void testSuccessfulFormAuthentication() {

        loginPage.setUsernameField(prop.getProperty("username"));
        loginPage.setPasswordField(prop.getProperty("password"));
        MainPage mainPage = loginPage.clickLoginButton();
        assertTrue(mainPage.getTitle().contains("PRODUCTS"));

    }

    @Test
    public void testUnsuccessfulFormAuthentication() {

        loginPage.setUsernameField(prop.getProperty("wrong-username"));
        loginPage.setPasswordField(prop.getProperty("password"));
        loginPage.clickLoginButton();
        assertTrue(loginPage.getErrorMessage().contains("Epic sadface: Username and password do not match any user in this service"));

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
