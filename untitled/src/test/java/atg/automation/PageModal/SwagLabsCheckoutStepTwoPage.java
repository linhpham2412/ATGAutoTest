package atg.automation.PageModal;

import atg.automation.selenium.ActionManager;
import atg.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

import java.text.DecimalFormat;
import java.util.List;

public class SwagLabsCheckoutStepTwoPage extends ActionManager {
    public SwagLabsCheckoutStepTwoPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String swagLabsCheckoutStepTwoPage_PageTitle = "xpath=//div[contains(@id,'header_container')]//span[contains(@class,'title')]";
    private final String swagLabsCheckoutStepTwoPage_CheckoutItemNameByIndex = "xpath=(//div[contains(@class,'cart_list')]//div[contains(@class,'inventory_item_name')])[%s]";
    private final String swagLabsCheckoutStepTwoPage_CheckoutItemPriceByIndex = "xpath=(//div[contains(@class,'inventory_item_price')])[%s]";
    private final String swagLabsCheckoutStepTwoPage_ItemsTotalLabel = "xpath=//div[contains(@class,'summary_subtotal_label')]";
    private final String swagLabsCheckoutStepTwoPage_TotalTaxLabel = "xpath=//div[contains(@class,'summary_tax_label')]";
    private final String swagLabsCheckoutStepTwoPage_TotalCheckoutLabel = "xpath=//div[contains(@class,'summary_info_label summary_total_label')]";
    private final String swagLabsCheckoutStepTwoPage_CancelButton = "id=cancel";
    private final String swagLabsCheckoutStepTwoPage_FinishButton = "id=finish";

    /**
     * Function to check page title from param with the actual from UI
     *
     * @param expectedPageTitle text value to check for the page title
     * @return return object SwagLabsCheckoutStepTwoPage to be able to continue call other functions in test case class
     */
    //Function
    public SwagLabsCheckoutStepTwoPage verifyCheckoutStepTwoPageTitleIs(String expectedPageTitle) {
        waitForElementVisible(swagLabsCheckoutStepTwoPage_PageTitle);
        String actualPageTitle = getText(swagLabsCheckoutStepTwoPage_PageTitle);
        assertEqual("Page Title", expectedPageTitle, actualPageTitle);
        return this;
    }

    /**
     * Function to get list of saved product in product page to compare to list of product listed out on UI of this page
     *
     * @return return object SwagLabsCheckoutStepTwoPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutStepTwoPage verifyAddedProductNameAndPrice() {
        waitForElementVisible(swagLabsCheckoutStepTwoPage_CheckoutItemNameByIndex.formatted("1"));
        //get number of product added to cart from testcontext
        int itemCount = Integer.parseInt(TestContext.getAttributeByName("numberOfAddedProduct"));
        //loop to the number of product added to cart to compare the name and price from saved product with the actual one on UI
        for (int x = 0; x < itemCount; x++) {
            int actualProductIndex = x + 1;
            List<String> expectedProductStringList = (List<String>) TestContext.getContextObjectsWithName(String.valueOf(actualProductIndex));
            String expectedProductName = expectedProductStringList.get(0);
            String expectedProductPrice = expectedProductStringList.get(1);
            String actualProductName = getText(swagLabsCheckoutStepTwoPage_CheckoutItemNameByIndex.formatted(String.valueOf(actualProductIndex)));
            String actualProductPrice = getText(swagLabsCheckoutStepTwoPage_CheckoutItemPriceByIndex.formatted(String.valueOf(actualProductIndex)));
            assertEqual("Added Product No " + actualProductIndex + " name", expectedProductName, actualProductName);
            assertEqual("Added Product No " + actualProductIndex + " price", expectedProductPrice, actualProductPrice);
        }
        return this;
    }

    /**
     * Function to get all added product price and calculate to subtotal price then save to testcontext
     *
     * @return return object SwagLabsCheckoutStepTwoPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutStepTwoPage calculateItemsTotalNumber() {
        //get number of product added to cart from testcontext
        int itemCount = Integer.parseInt(TestContext.getAttributeByName("numberOfAddedProduct"));
        double totalItemPrice = 0.0;
        //loop through all added product to sum up product price
        for (int x = 0; x < itemCount; x++) {
            int actualProductIndex = x + 1;
            List<String> expectedProductStringList = (List<String>) TestContext.getContextObjectsWithName(String.valueOf(actualProductIndex));
            double actualProductPrice = Double.parseDouble(expectedProductStringList.get(1).replace("$", ""));
            totalItemPrice += actualProductPrice;
        }
        //save subtotal price to testcontext
        TestContext.setAttribute("totalItemPrice", String.valueOf(totalItemPrice));
        return this;
    }

    /**
     * Function to compare the calculated subtotal price with the one on UI
     *
     * @return return object SwagLabsCheckoutStepTwoPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutStepTwoPage verifyCalculatedItemsTotalNumberWithActual() {
        waitForElementVisible(swagLabsCheckoutStepTwoPage_ItemsTotalLabel);
        String expectedTotalItemText = "Item total: $" + TestContext.getAttributeByName("totalItemPrice");
        String actualTotalItemText = getText(swagLabsCheckoutStepTwoPage_ItemsTotalLabel);
        assertEqual("Sub Total Text", expectedTotalItemText, actualTotalItemText);
        return this;
    }

    /**
     * Function to get the subtotal price and multiply with param to get tax price and save to testcontext
     *
     * @param percentNumber double number of tax percentage use to calculate tax price
     * @return return object SwagLabsCheckoutStepTwoPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutStepTwoPage calculateTotalTaxValueWithPercent(double percentNumber) {
        waitForElementVisible(swagLabsCheckoutStepTwoPage_TotalTaxLabel);
        //create decimal format to round tax result to only display 2 digits after the dot
        DecimalFormat twoDigitFormat = new DecimalFormat("#.##");
        double totalItemPrice = Double.parseDouble(TestContext.getAttributeByName("totalItemPrice"));
        double taxPercentage = percentNumber / 100;
        double taxValue = Double.parseDouble(twoDigitFormat.format(totalItemPrice * taxPercentage));
        TestContext.setAttribute("taxPrice", String.valueOf(taxValue));
        return this;
    }

    /**
     * Function to compare calculated tax price with actual one display on UI
     *
     * @return return object SwagLabsCheckoutStepTwoPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutStepTwoPage verifyCalculatedItemsTaxNumberWithActual() {
        waitForElementVisible(swagLabsCheckoutStepTwoPage_TotalTaxLabel);
        String expectedTaxItemText = "Tax: $" + TestContext.getAttributeByName("taxPrice");
        String actualTaxItemText = getText(swagLabsCheckoutStepTwoPage_TotalTaxLabel);
        assertEqual("Tax Text", expectedTaxItemText, actualTaxItemText);
        return this;
    }

    /**
     * Function to sum saved subtotal price with tax price to get total checkout price then save to testcontext
     *
     * @return return object SwagLabsCheckoutStepTwoPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutStepTwoPage calculateTotalCheckoutPrice() {
        double totalItemPrice = Double.parseDouble(TestContext.getAttributeByName("totalItemPrice"));
        double taxPrice = Double.parseDouble(TestContext.getAttributeByName("taxPrice"));
        double totalCheckoutPrice = totalItemPrice + taxPrice;
        TestContext.setAttribute("totalCheckoutPrice", String.valueOf(totalCheckoutPrice));
        return this;
    }

    /**
     * Function to compare saved total checkout price with the actual one on UI
     *
     * @return return object SwagLabsCheckoutStepTwoPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutStepTwoPage verifyCalculatedTotalCheckoutPriceNumberWithActual() {
        waitForElementVisible(swagLabsCheckoutStepTwoPage_TotalCheckoutLabel);
        String expectedTotalCheckoutText = "Total: $" + TestContext.getAttributeByName("totalCheckoutPrice");
        String actualTotalCheckoutText = getText(swagLabsCheckoutStepTwoPage_TotalCheckoutLabel);
        assertEqual("Tax Text", expectedTotalCheckoutText, actualTotalCheckoutText);
        return this;
    }

    /**
     * Function to perform click on Cancel button
     *
     * @return return object SwagLabsCheckoutStepTwoPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutStepTwoPage clickCancelButton() {
        waitForElementClickable(swagLabsCheckoutStepTwoPage_CancelButton);
        click(swagLabsCheckoutStepTwoPage_CancelButton);
        return this;
    }

    /**
     * Function to perform click on Finish button
     *
     * @return return object SwagLabsCheckoutStepTwoPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutStepTwoPage clickFinishButton() {
        waitForElementClickable(swagLabsCheckoutStepTwoPage_FinishButton);
        click(swagLabsCheckoutStepTwoPage_FinishButton);
        return this;
    }
}
