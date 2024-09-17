package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import testdata.TestData;
import utils.WebDriverFactory;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        driver = WebDriverFactory.getDriver();
        driver.get(TestData.BASE_URL);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {
        loginPage.login(TestData.VALID_USERNAME, TestData.VALID_PASSWORD);
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login was not successful");
    }

    @Test
    public void invalidLoginTest() {
        loginPage.login(TestData.INVALID_USERNAME, TestData.INVALID_PASSWORD);
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"), "Error message not displayed correctly");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
