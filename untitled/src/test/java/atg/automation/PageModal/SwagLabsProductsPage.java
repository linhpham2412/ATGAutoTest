package atg.automation.PageModal;

import atg.automation.selenium.ActionManager;
import atg.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

public class SwagLabsProductsPage extends ActionManager {
    public SwagLabsProductsPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String productPage_PageTitle = "xpath=//div[contains(@id,'header_container')]//span[contains(@class,'title')]";
    private final String productPage_SortContainerActiveOptions = "xpath=//div[contains(@id,'header_container')]//span[contains(@class,'active_option')]";
    private final String productPage_SortContainer = "xpath=//div[contains(@id,'header_container')]//select[contains(@class,'product_sort_container')]";
    private final String productPage_InventoryContainerXPath = "//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]";
    private final String productPage_InventoryImageNameByIndex = "xpath=("+productPage_InventoryContainerXPath+"//div[contains(@class,'inventory_item_img')])[%s]//img";
    private final String productPage_InventoryItemNameByIndex = "xpath=("+productPage_InventoryContainerXPath+"//div[contains(@class,'inventory_item_name')])[%s]";
    private final String productPage_InventoryItemPriceByIndex = "xpath=("+productPage_InventoryContainerXPath+"//div[contains(@class,'inventory_item_price')])[%s]";

    //Function
    /**
     * function to verify current product page title with the expected one
     * @param expectedPageTitle expected title to compare with the actual one
     * @return return object SwagLabsProductsPage to be able to continue call other functions in test case class
     */
    public SwagLabsProductsPage verifyProductPageTitleIs(String expectedPageTitle){
        waitForElementVisible(productPage_PageTitle);
        String actualPageTitle = getText(productPage_PageTitle);
        assertEqual("Page Title",expectedPageTitle,actualPageTitle);
        return this;
    }
    public SwagLabsProductsPage verifyProductPageActiveSortIs(String expectedActiveSort){
        waitForElementVisible(productPage_SortContainerActiveOptions);
        String actualActiveSort = getText(productPage_SortContainerActiveOptions);
        assertEqual("Sort Active Option",expectedActiveSort,actualActiveSort);
        return this;
    }
    public SwagLabsProductsPage verifyProductPageImageSourceByIndex(String expectedImageSource, String containerIndex){
        String imageElementXPathToCheck = productPage_InventoryImageNameByIndex.formatted(containerIndex);
        waitForElementVisible(imageElementXPathToCheck);
        String actualImageSource = getTextByAttribute(imageElementXPathToCheck,"src");
        assertEqual("Image source", expectedImageSource,actualImageSource);
        return this;
    }
    public SwagLabsProductsPage verifyProductPageItemNameByIndex(String expectedItemName, String containerIndex){
        String itemElementXPathToCheck = productPage_InventoryItemNameByIndex.formatted(containerIndex);
        waitForElementVisible(itemElementXPathToCheck);
        String actualItemName = getText(itemElementXPathToCheck);
        assertEqual("Item name",expectedItemName,actualItemName);
        return this;
    }
    public SwagLabsProductsPage verifyProductPageItemPriceByIndex(String expectedItemPrice, String containterIndex){
        String itemElementXPathToCheck = productPage_InventoryItemPriceByIndex.formatted(containterIndex);
        waitForElementVisible(itemElementXPathToCheck);
        String actualItemPrice = getText(itemElementXPathToCheck);
        assertEqual("Item Price",expectedItemPrice,actualItemPrice);
        return this;
    }
    public SwagLabsProductsPage changeProductPageSortingToValue(String sortName){
        String sortValue = "";
        switch (sortName){
            case "Name (A to Z)":
                sortValue = "az";
                break;
            case "Name (Z to A)":
                sortValue = "za";
                break;
            case "Price (low to high)":
                sortValue = "lohi";
                break;
            case "Price (high to low)":
                sortValue = "hilo";
                break;
        }
        waitForElementClickable(productPage_SortContainer);
        selectDropDownFieldWithValue(productPage_SortContainer,sortValue);
        return this;
    }
}
