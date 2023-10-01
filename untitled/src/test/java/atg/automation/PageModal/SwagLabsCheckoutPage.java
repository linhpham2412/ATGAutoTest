package atg.automation.PageModal;

import atg.automation.selenium.ActionManager;
import atg.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

public class SwagLabsCheckoutPage extends ActionManager {
    public SwagLabsCheckoutPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String swagLabsCheckoutPage_PageTitle = "xpath=//div[contains(@id,'header_container')]//span[contains(@class,'title')]";
    private final String swagLabsCheckoutPage_ContinueButton = "id=continue";
    private final String swagLabsCheckoutPage_FirstNameTextField = "id=first-name";
    private final String swagLabsCheckoutPage_LastNameTextField = "id=last-name";
    private final String swagLabsCheckoutPage_PostalCodeTextField = "id=postal-code";
    private final String swagLabsCheckoutPage_ErrorMessagePanel = "xpath=//h3[contains(@data-test,'error')]";

    /**
     * Function to compare param with actual page title
     *
     * @param expectedPageTitle expected pate title user want to check
     * @return return object SwagLabsCheckoutPage to be able to continue call other functions in test case class
     */
    //Function
    public SwagLabsCheckoutPage verifyCheckoutPageTitleIs(String expectedPageTitle) {
        waitForElementVisible(swagLabsCheckoutPage_PageTitle);
        String actualPageTitle = getText(swagLabsCheckoutPage_PageTitle);
        assertEqual("Page Title", expectedPageTitle, actualPageTitle);
        return this;
    }

    /**
     * Function to perform click on Continue button
     *
     * @return return object SwagLabsCheckoutPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutPage clickContinueButton() {
        waitForElementClickable(swagLabsCheckoutPage_ContinueButton);
        click(swagLabsCheckoutPage_ContinueButton);
        return this;
    }

    /**
     * Function to input param value to FirstName Text Field
     *
     * @param firstNameValue text value user want to input
     * @return return object SwagLabsCheckoutPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutPage inputFirstNameFieldWithValue(String firstNameValue) {
        waitForElementVisible(swagLabsCheckoutPage_FirstNameTextField);
        clearText(swagLabsCheckoutPage_FirstNameTextField);
        sendKeys(swagLabsCheckoutPage_FirstNameTextField, firstNameValue);
        return this;
    }

    /**
     * Function to input param to LastName Text Field
     *
     * @param lastNameValue text value user want to input
     * @return return object SwagLabsCheckoutPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutPage inputLastNameFieldWithValue(String lastNameValue) {
        waitForElementVisible(swagLabsCheckoutPage_LastNameTextField);
        clearText(swagLabsCheckoutPage_LastNameTextField);
        sendKeys(swagLabsCheckoutPage_LastNameTextField, lastNameValue);
        return this;
    }

    /**
     * Function to input param to PostalCode Text Field
     *
     * @param postalCodeValue text value user want to input
     * @return return object SwagLabsCheckoutPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutPage inputPostalCodeFieldWithValue(String postalCodeValue) {
        waitForElementVisible(swagLabsCheckoutPage_PostalCodeTextField);
        clearText(swagLabsCheckoutPage_PostalCodeTextField);
        sendKeys(swagLabsCheckoutPage_PostalCodeTextField, postalCodeValue);
        return this;
    }

    /**
     * Function to compare param with error message display on the UI
     *
     * @param expectedErrorMessage text error message user want to compare
     * @return return object SwagLabsCheckoutPage to be able to continue call other functions in test case class
     */
    public SwagLabsCheckoutPage verifyErrorMessageDisplayValue(String expectedErrorMessage) {
        waitForElementVisible(swagLabsCheckoutPage_ErrorMessagePanel);
        String actualErrorMessage = getText(swagLabsCheckoutPage_ErrorMessagePanel);
        assertEqual("Error message", expectedErrorMessage, actualErrorMessage);
        return this;
    }
}
