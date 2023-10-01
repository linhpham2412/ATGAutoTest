package atg.automation.TestCase;

import atg.automation.PageModal.*;
import atg.automation.selenium.Utils;
import atg.automation.setup.WebDriverTestNGSetupBase;
import org.testng.annotations.Test;

public class Verify_Error_Handling_In_LoginPage_and_CheckoutPage_ extends WebDriverTestNGSetupBase {

    /**
     * Test case to test error handling of required Username text field
     */
    @Test(alwaysRun = true)
    public void TC_1_Verify_UserName_Field_Error_Handling_In_Login_Page() {
        SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver(), getTestContext());

        //Steps
        swagLabsLoginPage
                .openLoginPage()
                .inputUserNameTextFieldWithValue("")
                .clickLoginButton()
                .verifyErrorMessageDisplayValue("Epic sadface: Username is required");
    }

    /**
     * Test case to test error handling of password blank test field
     */
    @Test(alwaysRun = true)
    public void TC_2_Verify_Password_Field_Error_Handling_In_Login_Page() {
        SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver(), getTestContext());

        //Steps
        /*
        Input blank password then click login and verify error message
         */
        swagLabsLoginPage
                .openLoginPage()
                .inputUserNameTextFieldWithValue("standard_user")
                .inputPasswordTextFieldWithValue("")
                .clickLoginButton()
                .verifyErrorMessageDisplayValue("Epic sadface: Password is required");
    }

    /**
     * Test case to test error handling of locked user log in
     */
    @Test(alwaysRun = true)
    public void TC_3_Verify_Locked_User_Error_Handling_In_Login_Page() {
        SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver(), getTestContext());

        //Steps
        swagLabsLoginPage
                .openLoginPage()
                .inputUserNameTextFieldWithValue("locked_out_user")
                .inputPasswordTextFieldWithValue("secret_sauce")
                .clickLoginButton()
                .verifyErrorMessageDisplayValue("Epic sadface: Sorry, this user has been locked out.");
    }

    /**
     * Test case to test error handling of FirstName, LastName and PostalCode fields in checkout page
     */
    @Test(alwaysRun = true)
    public void TC_4_Verify_Empty_Checkout_Fields_Error_Handling_In_Checkout_Page() {
        SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver(), getTestContext());
        SwagLabsProductsPage swagLabsProductsPage = new SwagLabsProductsPage(getDriver(), getTestContext());
        SwagLabsShoppingCartPage swagLabsShoppingCartPage = new SwagLabsShoppingCartPage(getDriver(), getTestContext());
        SwagLabsCheckoutPage swagLabsCheckoutPage = new SwagLabsCheckoutPage(getDriver(), getTestContext());
        SwagLabsCheckoutStepTwoPage swagLabsCheckoutStepTwoPage = new SwagLabsCheckoutStepTwoPage(getDriver(), getTestContext());

        //Steps
        /*
        Proceed to checkout page and leave firstname field blank to check error message
         */
        swagLabsLoginPage
                .openLoginPage()
                .inputUserNameTextFieldWithValue("standard_user")
                .inputPasswordTextFieldWithValue("secret_sauce")
                .clickLoginButton();

        swagLabsProductsPage
                .verifyProductPageTitleIs("Products")
                .clickShoppingCartButton();

        swagLabsShoppingCartPage
                .verifyShoppingCartPageTitleIs("Your Cart")
                .clickCheckoutButton();

        swagLabsCheckoutPage
                .verifyCheckoutPageTitleIs("Checkout: Your Information")
                .inputFirstNameFieldWithValue("")
                .clickContinueButton()
                .verifyErrorMessageDisplayValue("Error: First Name is required")
                .inputFirstNameFieldWithValue(Utils.generateRandomTestCharacters(10))
                .clickContinueButton()
                .verifyErrorMessageDisplayValue("Error: Last Name is required")
                .inputLastNameFieldWithValue(Utils.generateRandomTestCharacters(10))
                .clickContinueButton()
                .verifyErrorMessageDisplayValue("Error: Postal Code is required")
                .inputPostalCodeFieldWithValue(Utils.generateRandomNumberInRange(1, 1000))
                .clickContinueButton();

        swagLabsCheckoutStepTwoPage
                .verifyCheckoutStepTwoPageTitleIs("Checkout: Overview");
    }

    /**
     * Test case to test error handling of incorrect password blank test field
     */
    @Test(alwaysRun = true)
    public void TC_5_Verify_Incorrect_Password_Field_Error_Handling_In_Login_Page() {
        SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver(), getTestContext());

        //Steps
        swagLabsLoginPage
                .openLoginPage()
                .inputUserNameTextFieldWithValue("standard_user")
                .inputPasswordTextFieldWithValue("incorrect_password")
                .clickLoginButton()
                .verifyErrorMessageDisplayValue("Epic sadface: Username and password do not match any user in this service");
    }
}
