package atg.automation.TestCase;

import atg.automation.PageModal.*;
import atg.automation.selenium.Utils;
import atg.automation.setup.WebDriverTestNGSetupBase;
import org.testng.annotations.Test;

public class Verify_Checkout_Calculation_and_Proceed_ extends WebDriverTestNGSetupBase {
    /**
     * Test case to log in with valid user
     * Randomly add 3 products then save their name and price to testcontext
     * Open Shopping cart page and compare list of saved product with actual list of products on the UI
     * Open Checkout Step Two page and compare list of saved product with actual list of products on the UI
     * Go back to shopping cart page and clear all products in cart
     */
    @Test(alwaysRun = true)
    public void TC_1_AddToCart_Random_Product_Proceed_To_ShoppingCart_And_CheckoutTwoPage_To_Verify_Product_Added_Correctly() {
        SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver(), getTestContext());
        SwagLabsProductsPage swagLabsProductsPage = new SwagLabsProductsPage(getDriver(), getTestContext());
        SwagLabsShoppingCartPage swagLabsShoppingCartPage = new SwagLabsShoppingCartPage(getDriver(), getTestContext());
        SwagLabsCheckoutPage swagLabsCheckoutPage = new SwagLabsCheckoutPage(getDriver(), getTestContext());
        SwagLabsCheckoutStepTwoPage swagLabsCheckoutStepTwoPage = new SwagLabsCheckoutStepTwoPage(getDriver(), getTestContext());

        //Steps
        swagLabsLoginPage
                .openLoginPage()
                .inputUserNameTextFieldWithValue("standard_user")
                .inputPasswordTextFieldWithValue("secret_sauce")
                .clickLoginButton();

        swagLabsProductsPage
                .verifyProductPageTitleIs("Products")
                .randomlyAddNumberOfProductsInCart(3)
                .clickShoppingCartButton();

        swagLabsShoppingCartPage
                .verifyShoppingCartPageTitleIs("Your Cart")
                .verifyAddedProductNameAndPrice()
                .clickCheckoutButton();

        swagLabsCheckoutPage
                .verifyCheckoutPageTitleIs("Checkout: Your Information")
                .inputFirstNameFieldWithValue(Utils.generateRandomTestCharacters(10))
                .inputLastNameFieldWithValue(Utils.generateRandomTestCharacters(10))
                .inputPostalCodeFieldWithValue(Utils.generateRandomNumberInRange(1, 1000))
                .clickContinueButton();

        swagLabsCheckoutStepTwoPage
                .verifyCheckoutStepTwoPageTitleIs("Checkout: Overview")
                .verifyAddedProductNameAndPrice()
                .clickCancelButton();

        swagLabsProductsPage
                .verifyProductPageTitleIs("Products")
                .clickShoppingCartButton();

        swagLabsShoppingCartPage
                .verifyShoppingCartPageTitleIs("Your Cart")
                .clearAllItemsInShoppingCart();
    }

    /**
     * Test case to log in with valid user
     * Randomly add 3 products then save their name and price to testcontext
     * Open Checkout Step Two page and calculate subtotal price, tax price, total checkout price
     * Compare calculated prices with actual text display on the screen
     * Go back to shopping cart page and clear all products in cart
     */
    @Test(alwaysRun = true)
    public void TC_2_AddToCart_Random_Product_Proceed_To_CheckoutPage_To_Verify_Tax_and_Total_Calculation() {
        SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver(), getTestContext());
        SwagLabsProductsPage swagLabsProductsPage = new SwagLabsProductsPage(getDriver(), getTestContext());
        SwagLabsShoppingCartPage swagLabsShoppingCartPage = new SwagLabsShoppingCartPage(getDriver(), getTestContext());
        SwagLabsCheckoutPage swagLabsCheckoutPage = new SwagLabsCheckoutPage(getDriver(), getTestContext());
        SwagLabsCheckoutStepTwoPage swagLabsCheckoutStepTwoPage = new SwagLabsCheckoutStepTwoPage(getDriver(), getTestContext());

        //Steps
        swagLabsLoginPage
                .openLoginPage()
                .inputUserNameTextFieldWithValue("standard_user")
                .inputPasswordTextFieldWithValue("secret_sauce")
                .clickLoginButton();

        swagLabsProductsPage
                .verifyProductPageTitleIs("Products")
                .randomlyAddNumberOfProductsInCart(3)
                .clickShoppingCartButton();

        swagLabsShoppingCartPage
                .verifyShoppingCartPageTitleIs("Your Cart")
                .clickCheckoutButton();

        swagLabsCheckoutPage
                .verifyCheckoutPageTitleIs("Checkout: Your Information")
                .inputFirstNameFieldWithValue(Utils.generateRandomTestCharacters(10))
                .inputLastNameFieldWithValue(Utils.generateRandomTestCharacters(10))
                .inputPostalCodeFieldWithValue(Utils.generateRandomNumberInRange(1, 1000))
                .clickContinueButton();

        swagLabsCheckoutStepTwoPage
                .verifyCheckoutStepTwoPageTitleIs("Checkout: Overview")
                .calculateItemsTotalNumber()
                .verifyCalculatedItemsTotalNumberWithActual()
                .calculateTotalTaxValueWithPercent(8)
                .verifyCalculatedItemsTaxNumberWithActual()
                .calculateTotalCheckoutPrice()
                .verifyCalculatedTotalCheckoutPriceNumberWithActual()
                .clickCancelButton();

        swagLabsProductsPage
                .verifyProductPageTitleIs("Products")
                .clickShoppingCartButton();

        swagLabsShoppingCartPage
                .verifyShoppingCartPageTitleIs("Your Cart")
                .clearAllItemsInShoppingCart();
    }

    /**
     * Test case to log in with valid user
     * Randomly add 3 products then save their name and price to testcontext
     * Proceed to check out successfully
     */
    @Test(alwaysRun = true)
    public void TC_3_AddToCart_Random_Product_Proceed_To_Checkout_Successfully() {
        SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver(), getTestContext());
        SwagLabsProductsPage swagLabsProductsPage = new SwagLabsProductsPage(getDriver(), getTestContext());
        SwagLabsShoppingCartPage swagLabsShoppingCartPage = new SwagLabsShoppingCartPage(getDriver(), getTestContext());
        SwagLabsCheckoutPage swagLabsCheckoutPage = new SwagLabsCheckoutPage(getDriver(), getTestContext());
        SwagLabsCheckoutStepTwoPage swagLabsCheckoutStepTwoPage = new SwagLabsCheckoutStepTwoPage(getDriver(), getTestContext());
        SwagLabsCheckoutCompletedPage swagLabsCheckoutCompletedPage = new SwagLabsCheckoutCompletedPage(getDriver(), getTestContext());

        //Steps
        swagLabsLoginPage
                .openLoginPage()
                .inputUserNameTextFieldWithValue("standard_user")
                .inputPasswordTextFieldWithValue("secret_sauce")
                .clickLoginButton();

        swagLabsProductsPage
                .verifyProductPageTitleIs("Products")
                .randomlyAddNumberOfProductsInCart(3)
                .clickShoppingCartButton();

        swagLabsShoppingCartPage
                .verifyShoppingCartPageTitleIs("Your Cart")
                .clickCheckoutButton();

        swagLabsCheckoutPage
                .verifyCheckoutPageTitleIs("Checkout: Your Information")
                .inputFirstNameFieldWithValue(Utils.generateRandomTestCharacters(10))
                .inputLastNameFieldWithValue(Utils.generateRandomTestCharacters(10))
                .inputPostalCodeFieldWithValue(Utils.generateRandomNumberInRange(1, 1000))
                .clickContinueButton();

        swagLabsCheckoutStepTwoPage
                .verifyCheckoutStepTwoPageTitleIs("Checkout: Overview")
                .clickFinishButton();

        swagLabsCheckoutCompletedPage
                .verifyCheckoutCompletedPageTitleIs("Checkout: Complete!")
                .verifyCheckoutCompletedHeaderIs("Thank you for your order!")
                .verifyCheckoutCompletedTextIs("Your order has been dispatched, and will arrive just as fast as the pony can get there!")
                .clickBackToProductsButton();

        swagLabsProductsPage
                .verifyProductPageTitleIs("Products");
    }
}
