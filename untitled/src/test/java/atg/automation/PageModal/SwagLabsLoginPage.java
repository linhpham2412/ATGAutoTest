package atg.automation.PageModal;

import atg.automation.config.ConfigLoader;
import atg.automation.selenium.ActionManager;
import atg.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

public class SwagLabsLoginPage extends ActionManager {
    public SwagLabsLoginPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
    }

    //Locator
    private final String swagLabsLoginPage_UserNameTextField = "id=user-name";
    private final String swagLabsLoginPage_PasswordTextField = "id=password";
    private final String swagLabsLoginPage_LoginButton = "id=login-button";
    private final String swagLabsLoginPage_ErrorMessagePanel = "xpath=//h3[contains(@data-test,'error')]";

    /**
     * Function to open the environment url to login page of the application
     *
     * @return return object SwagLabsLoginPage to be able to continue call other functions in test case class
     */
    //Function
    public SwagLabsLoginPage openLoginPage() {
        openUrl(ConfigLoader.getEnvironment("url"));
        return this;
    }

    /**
     * Function to input param value to UserName Text Field
     *
     * @param userName text value of username
     * @return return object SwagLabsLoginPage to be able to continue call other functions in test case class
     */
    public SwagLabsLoginPage inputUserNameTextFieldWithValue(String userName) {
        waitForElementVisible(swagLabsLoginPage_UserNameTextField);
        clearText(swagLabsLoginPage_UserNameTextField);
        sendKeys(swagLabsLoginPage_UserNameTextField, userName);
        return this;
    }

    /**
     * Function to input param value to password Text Field
     *
     * @param password text value of password
     * @return return object SwagLabsLoginPage to be able to continue call other functions in test case class
     */
    public SwagLabsLoginPage inputPasswordTextFieldWithValue(String password) {
        waitForElementVisible(swagLabsLoginPage_PasswordTextField);
        clearText(swagLabsLoginPage_PasswordTextField);
        sendKeys(swagLabsLoginPage_PasswordTextField, password);
        return this;
    }

    /**
     * Function to perform click to Login button
     *
     * @return return object SwagLabsLoginPage to be able to continue call other functions in test case class
     */
    public SwagLabsLoginPage clickLoginButton() {
        waitForElementClickable(swagLabsLoginPage_LoginButton);
        click(swagLabsLoginPage_LoginButton);
        return this;
    }

    /**
     * Function to check the correction from expected error from param with actual error from UI
     *
     * @param expectedErrorMessage text value of error message user want to check
     * @return return object SwagLabsLoginPage to be able to continue call other functions in test case class
     */
    public SwagLabsLoginPage verifyErrorMessageDisplayValue(String expectedErrorMessage) {
        waitForElementVisible(swagLabsLoginPage_ErrorMessagePanel);
        String actualErrorMessage = getText(swagLabsLoginPage_ErrorMessagePanel);
        assertEqual("Error message", expectedErrorMessage, actualErrorMessage);
        return this;
    }
}
