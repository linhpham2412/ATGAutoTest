package atg.automation.PageModal;

import atg.automation.selenium.ActionManager;
import atg.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

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

    //Function
    public SwagLabsCheckoutStepTwoPage verifyCheckoutStepTwoPageTitleIs(String expectedPageTitle){
        waitForElementVisible(swagLabsCheckoutStepTwoPage_PageTitle);
        String actualPageTitle = getText(swagLabsCheckoutStepTwoPage_PageTitle);
        assertEqual("Page Title",expectedPageTitle,actualPageTitle);
        return this;
    }
    public SwagLabsCheckoutStepTwoPage verifyAddedProductNameAndPrice() {
        waitForElementVisible(swagLabsCheckoutStepTwoPage_CheckoutItemNameByIndex.formatted("1"));
        int itemCount = Integer.parseInt(TestContext.getAttributeByName("numberOfAddedProduct"));
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
    public SwagLabsCheckoutStepTwoPage calculateItemsTotalNumber(){
        int itemCount = Integer.parseInt(TestContext.getAttributeByName("numberOfAddedProduct"));
        double totalItemPrice = 0.0;
        for (int x = 0; x < itemCount; x++) {
            int actualProductIndex = x + 1;
            List<String> expectedProductStringList = (List<String>) TestContext.getContextObjectsWithName(String.valueOf(actualProductIndex));
            double actualProductPrice = Double.parseDouble(expectedProductStringList.get(1).replace("$",""));
            totalItemPrice += actualProductPrice;
        }
        TestContext.setAttribute("totalItemPrice", String.valueOf(totalItemPrice));
        return this;
    }
    public SwagLabsCheckoutStepTwoPage verifyCalculatedItemsTotalNumberWithActual(){
        waitForElementVisible(swagLabsCheckoutStepTwoPage_ItemsTotalLabel);
        String expectedTotalItemText = "Item total: $"+TestContext.getAttributeByName("totalItemPrice");
        String actualTotalItemText = getText(swagLabsCheckoutStepTwoPage_ItemsTotalLabel);
        assertEqual("Sub Total Text", expectedTotalItemText,actualTotalItemText);
        return this;
    }
    public SwagLabsCheckoutStepTwoPage calculateTotalTaxValueWithPercent(double percentNumber){
        waitForElementVisible(swagLabsCheckoutStepTwoPage_TotalTaxLabel);
        DecimalFormat twoDigitFormat = new DecimalFormat("#.##");
        double totalItemPrice = Double.parseDouble(TestContext.getAttributeByName("totalItemPrice"));
        double taxPercentage = percentNumber/100;
        double taxValue = Double.parseDouble(twoDigitFormat.format(totalItemPrice*taxPercentage));
        TestContext.setAttribute("taxPrice", String.valueOf(taxValue));
        return this;
    }
    public SwagLabsCheckoutStepTwoPage verifyCalculatedItemsTaxNumberWithActual(){
        waitForElementVisible(swagLabsCheckoutStepTwoPage_TotalTaxLabel);
        String expectedTaxItemText = "Tax: $"+TestContext.getAttributeByName("taxPrice");
        String actualTaxItemText = getText(swagLabsCheckoutStepTwoPage_TotalTaxLabel);
        assertEqual("Tax Text", expectedTaxItemText,actualTaxItemText);
        return this;
    }
    public SwagLabsCheckoutStepTwoPage calculateTotalCheckoutPrice(){
        double totalItemPrice = Double.parseDouble(TestContext.getAttributeByName("totalItemPrice"));
        double taxPrice = Double.parseDouble(TestContext.getAttributeByName("taxPrice"));
        double totalCheckoutPrice = totalItemPrice+taxPrice;
        TestContext.setAttribute("totalCheckoutPrice", String.valueOf(totalCheckoutPrice));
        return this;
    }
    public SwagLabsCheckoutStepTwoPage verifyCalculatedTotalCheckoutPriceNumberWithActual(){
        waitForElementVisible(swagLabsCheckoutStepTwoPage_TotalCheckoutLabel);
        String expectedTotalCheckoutText = "Total: $"+TestContext.getAttributeByName("totalCheckoutPrice");
        String actualTotalCheckoutText = getText(swagLabsCheckoutStepTwoPage_TotalCheckoutLabel);
        assertEqual("Tax Text", expectedTotalCheckoutText,actualTotalCheckoutText);
        return this;
    }
    public SwagLabsCheckoutStepTwoPage clickCancelButton(){
        waitForElementClickable(swagLabsCheckoutStepTwoPage_CancelButton);
        click(swagLabsCheckoutStepTwoPage_CancelButton);
        return this;
    }
    public SwagLabsCheckoutStepTwoPage clickFinishButton(){
        waitForElementClickable(swagLabsCheckoutStepTwoPage_FinishButton);
        click(swagLabsCheckoutStepTwoPage_FinishButton);
        return this;
    }
}
