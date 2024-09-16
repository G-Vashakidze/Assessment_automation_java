package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import testdata.TestData;
import utils.WebDriverFactory;
import java.util.Arrays;
import java.util.List;

public class CheckoutTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeMethod
    public void setup(){
        driver = WebDriverFactory.getDriver();
        driver.get(TestData.BASE_URL);;
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        loginPage.login(TestData.VALID_USERNAME, TestData.VALID_PASSWORD);

    }


    @Test
    public void checkoutAndRemoveExpensiveProductTest(){
        List<String> productsToAdd = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Test.allTheThings() T-Shirt (Red)");
        productPage.addProductsToCartByName(productsToAdd);
        int amountOfProducts = productsToAdd.size();

        // Verify cart count is 3
        Assert.assertEquals(productPage.getCartItemCount(), amountOfProducts, "Cart item count is incorrect");

        // Go to cart page
        productPage.goToCart();

       // Remove the most expensive item
        cartPage.removeItemByName(cartPage.getMostExpensiveItemName());

        // Verify only 2 items remain
        Assert.assertEquals(cartPage.countItemsInCart(), amountOfProducts-1, "Cart item count after removal is incorrect");

        // Proceed to checkout
        cartPage.clickCheckout();

        // Enter checkout information and finish
        cartPage.enterCheckoutInformation("John", "Doe", "12345");
        cartPage.clickContinue();
        cartPage.clickFinish();

        // Verify success message
        Assert.assertTrue(cartPage.isSuccessMessageDisplayed(), "Success message not displayed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
