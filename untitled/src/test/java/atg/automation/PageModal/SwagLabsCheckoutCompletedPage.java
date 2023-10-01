package atg.automation.PageModal;

import atg.automation.selenium.ActionManager;
import atg.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

public class SwagLabsCheckoutCompletedPage extends ActionManager {
    public SwagLabsCheckoutCompletedPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String swagLabsCheckoutCompletePage_PageTitle = "xpath=//div[contains(@id,'header_container')]//span[contains(@class,'title')]";
    private final String swagLabsCheckoutCompletePage_CompleteHeader = "xpath=//h2[contains(@class,'complete-header')]";
    private final String swagLabsCheckoutCompletePage_CompleteText = "xpath=//div[contains(@class,'complete-text')]";
    private final String swagLabsCheckoutCompletePage_BackToProductButton = "id=back-to-products";

    /**
     * Function to compare param with actual page title on UI
     *
     * @param expectedPageTitle text value user want to compare
     * @return return object SwagLabsCheckoutCompletedPage to be able to continue call other functions in test case class
     */
    //Function
    public SwagLabsCheckoutCompletedPage verifyCheckoutCompletedPageTitleIs(String expectedPageTitle) {
        waitForElementVisible(swagLabsCheckoutCompletePage_PageTitle);
        String actualPageTitle = getText(swagLabsCheckoutCompletePage_PageTitle);
        assertEqual("Page Title", expectedPageTitle, actualPageTitle);
        return this;
    }

    /**
     * Function to compare param with page header value on UI
     *
     * @param expectedPageHeader text value user want to compare
     * @return return object SwagLabsCheckoutCompletedPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutCompletedPage verifyCheckoutCompletedHeaderIs(String expectedPageHeader) {
        waitForElementVisible(swagLabsCheckoutCompletePage_CompleteHeader);
        String actualPageHeader = getText(swagLabsCheckoutCompletePage_CompleteHeader);
        assertEqual("Page Header", expectedPageHeader, actualPageHeader);
        return this;
    }

    /**
     * Function to compare param with page text message on UI
     *
     * @param expectedPageText text value user want to compare
     * @return return object SwagLabsCheckoutCompletedPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutCompletedPage verifyCheckoutCompletedTextIs(String expectedPageText) {
        waitForElementVisible(swagLabsCheckoutCompletePage_CompleteText);
        String actualPageText = getText(swagLabsCheckoutCompletePage_CompleteText);
        assertEqual("Page Text", expectedPageText, actualPageText);
        return this;
    }

    /**
     * Function to perform click on Back To Products button
     *
     * @return return object SwagLabsCheckoutCompletedPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutCompletedPage clickBackToProductsButton() {
        waitForElementClickable(swagLabsCheckoutCompletePage_BackToProductButton);
        click(swagLabsCheckoutCompletePage_BackToProductButton);
        return this;
    }
}
