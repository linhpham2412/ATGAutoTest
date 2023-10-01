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

    //Function
    public SwagLabsLoginPage openLoginPage(){
        openUrl(ConfigLoader.getEnvironment("url"));
        return this;
    }
    public SwagLabsLoginPage inputUserNameTextFieldWithValue(String userName){
        waitForElementVisible(swagLabsLoginPage_UserNameTextField);
        sendKeys(swagLabsLoginPage_UserNameTextField,userName);
        return this;
    }
    public SwagLabsLoginPage inputPasswordTextFieldWithValue(String password){
        waitForElementVisible(swagLabsLoginPage_PasswordTextField);
        sendKeys(swagLabsLoginPage_PasswordTextField,password);
        return this;
    }
    public SwagLabsLoginPage clickLoginButton(){
        waitForElementClickable(swagLabsLoginPage_LoginButton);
        click(swagLabsLoginPage_LoginButton);
        return this;
    }
}
