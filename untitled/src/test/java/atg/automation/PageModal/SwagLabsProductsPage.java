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
     * Function to verify current product page title with the expected one
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

    /**
     * Function to verify value in Active sort field
     *
     * @param expectedActiveSort expected active sort text
     * @return return object SwagLabsProductsPage to be able to continue call other functions in test case class
     */
    public SwagLabsProductsPage verifyProductPageActiveSortIs(String expectedActiveSort) {
        waitForElementVisible(swagLabsProductPage_SortContainerActiveOptions);
        String actualActiveSort = getText(swagLabsProductPage_SortContainerActiveOptions);
        assertEqual("Sort Active Option", expectedActiveSort, actualActiveSort);
        return this;
    }

    /**
     * Function to check value Image source by index in the container
     *
     * @param expectedImageSource value of expected image source
     * @param containerIndex      index of the product in the product container
     * @return return object SwagLabsProductsPage to be able to continue call other functions in test case class
     */
    public SwagLabsProductsPage verifyProductPageImageSourceByIndex(String expectedImageSource, String containerIndex) {
        String imageElementXPathToCheck = swagLabsProductPage_InventoryImageNameByIndex.formatted(containerIndex);
        waitForElementVisible(imageElementXPathToCheck);
        String actualImageSource = getTextByAttribute(imageElementXPathToCheck, "src");
        assertEqual("Image source", expectedImageSource, actualImageSource);
        return this;
    }

    /**
     * Function to check Product Name by product index in page container
     *
     * @param expectedItemName expected value of product name
     * @param containerIndex   index of the product in the product container
     * @return return object SwagLabsProductsPage to be able to continue call other functions in test case class
     */
    public SwagLabsProductsPage verifyProductPageItemNameByIndex(String expectedItemName, String containerIndex) {
        String itemElementXPathToCheck = swagLabsProductPage_InventoryItemNameByIndex.formatted(containerIndex);
        waitForElementVisible(itemElementXPathToCheck);
        String actualItemName = getText(itemElementXPathToCheck);
        assertEqual("Item name", expectedItemName, actualItemName);
        return this;
    }

    /**
     * Function to check product price by product index in page container
     *
     * @param expectedItemPrice expected value of product price
     * @param containerIndex    index of the product in the product container
     * @return return object SwagLabsProductsPage to be able to continue call other functions in test case class
     */
    public SwagLabsProductsPage verifyProductPageItemPriceByIndex(String expectedItemPrice, String containerIndex) {
        String itemElementXPathToCheck = swagLabsProductPage_InventoryItemPriceByIndex.formatted(containerIndex);
        waitForElementVisible(itemElementXPathToCheck);
        String actualItemPrice = getText(itemElementXPathToCheck);
        assertEqual("Item Price", expectedItemPrice, actualItemPrice);
        return this;
    }

    /**
     * Function to click on the action sort dropdown box then select the value in param
     *
     * @param sortName name of the sort user want to select
     * @return return object SwagLabsProductsPage to be able to continue call other functions in test case class
     */
    public SwagLabsProductsPage changeProductPageSortingToValue(String sortName) {
        //Based on the sort name provided switch to value name of option elements in the dropdown
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

    /**
     * Function to perform click action on Shopping Cart button
     *
     * @return return object SwagLabsProductsPage to be able to continue call other functions in test case class
     */
    public SwagLabsProductsPage clickShoppingCartButton() {
        waitForElementClickable(swagLabsProductPage_ShoppingCartContainer);
        click(swagLabsProductPage_ShoppingCartContainer);
        return this;
    }

    /**
     * Function to randomly add number of product provided in param to the shopping cart
     *
     * @param numberOfProducts total number of random products user want to add to shopping cart (maximum is 6)
     * @return return object SwagLabsProductsPage to be able to continue call other functions in test case class
     */
    public SwagLabsProductsPage randomlyAddNumberOfProductsInCart(int numberOfProducts) {
        //if number provided bigger than 6 set to 6
        numberOfProducts = Math.min(numberOfProducts, 6);
        //Save total number of products added to cart for later check
        TestContext.setAttribute("numberOfAddedProduct", String.valueOf(numberOfProducts));
        //Create a list then shuffle to get random order of value in list then select number of products from left to right
        Integer[] productIndexArray = {1, 2, 3, 4, 5, 6};
        List<Integer> productIndexList = new ArrayList<>(Arrays.asList(productIndexArray));
        Collections.shuffle(productIndexList);
        List<Integer> randomProductIndexList = productIndexList.subList(0, numberOfProducts);
        //Create index variable to save to testcontext object as key
        AtomicInteger productIndexWhenSaveToTestContext = new AtomicInteger();
        //Go through each index in shorten list to add product to cart and also save product name, price to testcontext for later check
        randomProductIndexList.forEach(product -> {
            String addToCartProduct = swagLabsProductPage_AddToCartButtonByIndex.formatted(product.toString());
            waitForElementClickable(addToCartProduct);
            click(addToCartProduct);
            ArrayList<String> addedProductNameAndPriceInCart = new ArrayList<String>();
            addedProductNameAndPriceInCart.add(getText(swagLabsProductPage_InventoryItemNameByIndex.formatted(product.toString())));
            addedProductNameAndPriceInCart.add(getText(swagLabsProductPage_InventoryItemPriceByIndex.formatted(product.toString())));
            productIndexWhenSaveToTestContext.getAndIncrement();
            TestContext.setContextObjectsWithName(productIndexWhenSaveToTestContext.toString(), addedProductNameAndPriceInCart);
        });
        return this;
    }
}
