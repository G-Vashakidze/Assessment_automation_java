package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductPage {
    WebDriver driver;

    @FindBy(xpath = "//button[contains(text(), 'Add to cart')]")
    List<WebElement> allAddToCartButtons;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    WebElement cartItemCountSpan;

    @FindBy(id = "shopping_cart_container")
    WebElement cartIcon;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public int getAllAddToCartButtonsCount() {
        return allAddToCartButtons.size();
    }

    // add all products to the cart
    public void addAllProductsToCart() {
        for (WebElement addToCartButton : allAddToCartButtons) {
            addToCartButton.click();
        }
    }

    // add specific products by name
    public void addProductsToCartByName(List<String> productNamesToAdd) {
        for (String productName : productNamesToAdd) {
            String productNameFormatted = productName.toLowerCase().replace(" ", "-");
            String xpath = "//button[contains(@id, '" + productNameFormatted + "') and contains(@id, 'add-to-cart')]";
            WebElement addToCartButton = driver.findElement(By.xpath(xpath));
            addToCartButton.click();

        }
    }

    // get the count of items in the cart (from the cart badge)
    public int getCartItemCount() {
        String itemCountText = cartItemCountSpan.getText();
        return Integer.parseInt(itemCountText);
    }
    // navigate to the cart page
    public void goToCart() {
        cartIcon.click();
    }
}
