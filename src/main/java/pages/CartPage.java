package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartPage {
    WebDriver driver;

    // WebElements for cart items (name, price, and remove button)
    @FindBy(xpath = "//div[@class='cart_item']")
    List<WebElement> cartItems;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    List<WebElement> itemNames;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    List<WebElement> itemPrices;

    @FindBy(xpath = "//button[@id='checkout']")
    WebElement checkoutButton;

    @FindBy(id = "first-name")
    WebElement firstNameInput;

    @FindBy(id = "last-name")
    WebElement lastNameInput;

    @FindBy(id = "postal-code")
    WebElement postalCodeInput;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(xpath = "//h2[text()='Thank you for your order!']")
    WebElement successMessage;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // remove an item by its name
    public void removeItemByName(String itemName) {
        String itemNameFormatted = itemName.toLowerCase().replace(" ", "-");
        String xpath = "//button[contains(@id, '" + itemNameFormatted + "') and contains(@id, 'remove')]";
        WebElement removeButton = driver.findElement(By.xpath(xpath));
        removeButton.click();
    }

    // count items in the cart
    public int countItemsInCart() {
        return cartItems.size();
    }

    // find the most expensive item's name
    public String getMostExpensiveItemName() {
        double highestPrice = 0.0;
        String mostExpensiveItemName = "";

        for (int i = 0; i < itemPrices.size(); i++) {
            double price = Double.parseDouble(itemPrices.get(i).getText().replace("$", ""));
            if (price > highestPrice) {
                highestPrice = price;
                mostExpensiveItemName = itemNames.get(i).getText();
            }
        }
        return mostExpensiveItemName;
    }

    // proceed to checkout
    public void clickCheckout() {
        checkoutButton.click();
    }

    // enter checkout information
    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
    }

    // proceed to the next step in checkout
    public void clickContinue() {
        continueButton.click();
    }

    // finalize the checkout
    public void clickFinish() {
        finishButton.click();
    }

    // verify success
    public boolean isSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }
}
