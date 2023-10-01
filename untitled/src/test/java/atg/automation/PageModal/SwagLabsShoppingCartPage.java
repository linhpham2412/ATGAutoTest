package atg.automation.PageModal;

import atg.automation.selenium.ActionManager;
import atg.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class SwagLabsShoppingCartPage extends ActionManager {
    public SwagLabsShoppingCartPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String swagLabsShoppingCartPage_PageTitle = "xpath=//div[contains(@id,'header_container')]//span[contains(@class,'title')]";
    private final String swagLabsShoppingCartPage_CheckoutButton = "id=checkout";
    private final String swagLabsShoppingCartPage_CheckoutItemNameByIndex = "xpath=(//div[contains(@class,'cart_list')]//div[contains(@class,'inventory_item_name')])[%s]";
    private final String swagLabsShoppingCartPage_CheckoutItemPriceByIndex = "xpath=(//div[contains(@class,'inventory_item_price')])[%s]";
    private final String swagLabsShoppingCartPage_RemoveButtonByIndex = "xpath=(//div[contains(@class,'inventory_item_price')]//following::button)[%s]";

    /**
     * Function to check the param with the actual page title on UI
     *
     * @param expectedPageTitle expected page title user want to check
     * @return return object SwagLabsShoppingCartPage to be able to continue call other functions in test case class
     */
    //Function
    public SwagLabsShoppingCartPage verifyShoppingCartPageTitleIs(String expectedPageTitle) {
        waitForElementVisible(swagLabsShoppingCartPage_PageTitle);
        String actualPageTitle = getText(swagLabsShoppingCartPage_PageTitle);
        assertEqual("Page Title", expectedPageTitle, actualPageTitle);
        return this;
    }

    /**
     * Function to perform click on Checkout button
     *
     * @return return object SwagLabsShoppingCartPage to be able to continue call other functions in test case class
     */
    public SwagLabsShoppingCartPage clickCheckoutButton() {
        waitForElementClickable(swagLabsShoppingCartPage_CheckoutButton);
        click(swagLabsShoppingCartPage_CheckoutButton);
        return this;
    }

    /**
     * Function to compare list of name and price of added products with actual list of products in shopping cart page
     *
     * @return return object SwagLabsShoppingCartPage to be able to continue call other functions in test case class
     */
    public SwagLabsShoppingCartPage verifyAddedProductNameAndPrice() {
        waitForElementVisible(swagLabsShoppingCartPage_CheckoutItemNameByIndex.formatted("1"));
        //get max number of products added in product page
        int itemCount = Integer.parseInt(TestContext.getAttributeByName("numberOfAddedProduct"));
        //loop to max product to compare name and price base on product index
        for (int x = 0; x < itemCount; x++) {
            int actualProductIndex = x + 1;
            List<String> expectedProductStringList = (List<String>) TestContext.getContextObjectsWithName(String.valueOf(actualProductIndex));
            String expectedProductName = expectedProductStringList.get(0);
            String expectedProductPrice = expectedProductStringList.get(1);
            String actualProductName = getText(swagLabsShoppingCartPage_CheckoutItemNameByIndex.formatted(String.valueOf(actualProductIndex)));
            String actualProductPrice = getText(swagLabsShoppingCartPage_CheckoutItemPriceByIndex.formatted(String.valueOf(actualProductIndex)));
            assertEqual("Added Product No " + actualProductIndex + " name", expectedProductName, actualProductName);
            assertEqual("Added Product No " + actualProductIndex + " price", expectedProductPrice, actualProductPrice);
        }
        return this;
    }

    /**
     * Function to perform click on Remove button of all products in shopping cart page
     *
     * @return return object SwagLabsShoppingCartPage to be able to continue call other functions in test case class
     */
    public SwagLabsShoppingCartPage clearAllItemsInShoppingCart() {
        //get max number of products added in product page
        int itemCount = Integer.parseInt(TestContext.getAttributeByName("numberOfAddedProduct"));
        String removeButton = swagLabsShoppingCartPage_RemoveButtonByIndex.formatted("1");
        //loop to max product to click on remove button of the first product each time
        for (int x = 0; x < itemCount; x++) {
            waitForElementClickable(removeButton);
            click(removeButton);
        }
        return this;
    }
}
