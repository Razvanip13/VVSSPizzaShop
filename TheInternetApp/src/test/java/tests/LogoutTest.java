package tests;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

import static org.testng.AssertJUnit.assertTrue;
import static pages.BaseClass.*;

public class LogoutTest{


    LoginPage loginPage;

    @Test
    public void testSuccessfulLogout() {

        loginPage.setUsernameField(prop.getProperty("username"));
        loginPage.setPasswordField(prop.getProperty("password"));
        MainPage mainPage = loginPage.clickLoginButton();
        loginPage = mainPage.clickLogoutButton();

        assertTrue(loginPage.assertExistsText("Accepted usernames are:"));

    }

    @Test
    public void testUnSuccessfulLogout() {

        loginPage.setUsernameField(prop.getProperty("wrong-username"));
        loginPage.setPasswordField(prop.getProperty("password"));
        MainPage mainPage = loginPage.clickLoginButton();
        try {
            loginPage = mainPage.clickLogoutButton();
        } catch (NoSuchElementException e) {
            assertTrue(loginPage.assertExistsText("Accepted usernames are:"));
        }
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
