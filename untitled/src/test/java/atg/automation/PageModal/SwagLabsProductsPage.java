package atg.automation.PageModal;

import atg.automation.selenium.ActionManager;
import atg.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SwagLabsProductsPage extends ActionManager {
    public SwagLabsProductsPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String swagLabsProductPage_PageTitle = "xpath=//div[contains(@id,'header_container')]//span[contains(@class,'title')]";
    private final String swagLabsProductPage_SortContainerActiveOptions = "xpath=//div[contains(@id,'header_container')]//span[contains(@class,'active_option')]";
    private final String swagLabsProductPage_SortContainer = "xpath=//div[contains(@id,'header_container')]//select[contains(@class,'product_sort_container')]";
    private final String swagLabsProductPage_ShoppingCartContainer = "id=shopping_cart_container";
    private final String swagLabsProductPage_InventoryContainerXPath = "//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]";
    private final String swagLabsProductPage_InventoryImageNameByIndex = "xpath=(" + swagLabsProductPage_InventoryContainerXPath + "//div[contains(@class,'inventory_item_img')])[%s]//img";
    private final String swagLabsProductPage_InventoryItemNameByIndex = "xpath=(" + swagLabsProductPage_InventoryContainerXPath + "//div[contains(@class,'inventory_item_name')])[%s]";
    private final String swagLabsProductPage_InventoryItemPriceByIndex = "xpath=(" + swagLabsProductPage_InventoryContainerXPath + "//div[contains(@class,'inventory_item_price')])[%s]";
    private final String swagLabsProductPage_AddToCartButtonByIndex = "xpath=(//div[contains(@class,'inventory_item_price')]//following::button)[%s]";

    //Function

    /**
     * function to verify current product page title with the expected one
     *
     * @param expectedPageTitle expected title to compare with the actual one
     * @return return object SwagLabsProductsPage to be able to continue call other functions in test case class
     */
    public SwagLabsProductsPage verifyProductPageTitleIs(String expectedPageTitle) {
        waitForElementVisible(swagLabsProductPage_PageTitle);
        String actualPageTitle = getText(swagLabsProductPage_PageTitle);
        assertEqual("Page Title", expectedPageTitle, actualPageTitle);
        return this;
    }

    public SwagLabsProductsPage verifyProductPageActiveSortIs(String expectedActiveSort) {
        waitForElementVisible(swagLabsProductPage_SortContainerActiveOptions);
        String actualActiveSort = getText(swagLabsProductPage_SortContainerActiveOptions);
        assertEqual("Sort Active Option", expectedActiveSort, actualActiveSort);
        return this;
    }

    public SwagLabsProductsPage verifyProductPageImageSourceByIndex(String expectedImageSource, String containerIndex) {
        String imageElementXPathToCheck = swagLabsProductPage_InventoryImageNameByIndex.formatted(containerIndex);
        waitForElementVisible(imageElementXPathToCheck);
        String actualImageSource = getTextByAttribute(imageElementXPathToCheck, "src");
        assertEqual("Image source", expectedImageSource, actualImageSource);
        return this;
    }

    public SwagLabsProductsPage verifyProductPageItemNameByIndex(String expectedItemName, String containerIndex) {
        String itemElementXPathToCheck = swagLabsProductPage_InventoryItemNameByIndex.formatted(containerIndex);
        waitForElementVisible(itemElementXPathToCheck);
        String actualItemName = getText(itemElementXPathToCheck);
        assertEqual("Item name", expectedItemName, actualItemName);
        return this;
    }

    public SwagLabsProductsPage verifyProductPageItemPriceByIndex(String expectedItemPrice, String containerIndex) {
        String itemElementXPathToCheck = swagLabsProductPage_InventoryItemPriceByIndex.formatted(containerIndex);
        waitForElementVisible(itemElementXPathToCheck);
        String actualItemPrice = getText(itemElementXPathToCheck);
        assertEqual("Item Price", expectedItemPrice, actualItemPrice);
        return this;
    }

    public SwagLabsProductsPage changeProductPageSortingToValue(String sortName) {
        String sortValue = switch (sortName) {
            case "Name (A to Z)" -> "az";
            case "Name (Z to A)" -> "za";
            case "Price (low to high)" -> "lohi";
            case "Price (high to low)" -> "hilo";
            default -> "";
        };
        waitForElementClickable(swagLabsProductPage_SortContainer);
        selectDropDownFieldWithValue(swagLabsProductPage_SortContainer, sortValue);
        return this;
    }

    public SwagLabsProductsPage clickShoppingCartButton() {
        waitForElementClickable(swagLabsProductPage_ShoppingCartContainer);
        click(swagLabsProductPage_ShoppingCartContainer);
        return this;
    }

    public SwagLabsProductsPage randomlyAddNumberOfProductsInCart(int numberOfProducts) {
        //if number provided bigger than 6 set to 6
        numberOfProducts = Math.min(numberOfProducts, 6);
        TestContext.setAttribute("numberOfAddedProduct", String.valueOf(numberOfProducts));
        Integer[] productIndexArray = {1, 2, 3, 4, 5, 6};
        List<Integer> productIndexList = new ArrayList<>(Arrays.asList(productIndexArray));
        Collections.shuffle(productIndexList);
        List<Integer> randomProductIndexList = productIndexList.subList(0, numberOfProducts);
        AtomicInteger productIndexWhenSaveToTestContext = new AtomicInteger();
        randomProductIndexList.forEach(product->{
            String addToCartProduct = swagLabsProductPage_AddToCartButtonByIndex.formatted(product.toString());
            waitForElementClickable(addToCartProduct);
            click(addToCartProduct);
            ArrayList<String> addedProductNameAndPriceInCart = new ArrayList<String>();
            addedProductNameAndPriceInCart.add(getText(swagLabsProductPage_InventoryItemNameByIndex.formatted(product.toString())));
            addedProductNameAndPriceInCart.add(getText(swagLabsProductPage_InventoryItemPriceByIndex.formatted(product.toString())));
            productIndexWhenSaveToTestContext.getAndIncrement();
            TestContext.setContextObjectsWithName(productIndexWhenSaveToTestContext.toString(),addedProductNameAndPriceInCart);
        });
        return this;
    }
}
