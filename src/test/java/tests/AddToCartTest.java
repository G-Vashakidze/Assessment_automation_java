package tests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import testdata.TestData;
import utils.WebDriverFactory;


public class AddToCartTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;

    @BeforeMethod
    public void setup() {
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(TestData.BASE_URL);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        loginPage.login(TestData.VALID_USERNAME, TestData.VALID_PASSWORD);
    }

    @Test
    public void addAllProductsToCart() {
        int amountAdded = productPage.getAllAddToCartButtonsCount();
        productPage.addAllProductsToCart();
        Assert.assertEquals(productPage.getCartItemCount(), amountAdded, "Not all products were added to the cart");
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
