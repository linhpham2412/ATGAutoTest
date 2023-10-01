package atg.automation.TestCase;

import atg.automation.PageModal.SwagLabsLoginPage;
import atg.automation.PageModal.SwagLabsProductsPage;
import atg.automation.config.ConfigLoader;
import atg.automation.setup.WebDriverTestNGSetupBase;
import org.testng.annotations.Test;

public class Verify_Sorting_Functions_In_Product_Page_ extends WebDriverTestNGSetupBase {

    @Test(alwaysRun = true)
    public void TC_1_Login_And_Test_Sort_by_Name_A_to_Z() {
        SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver(), getTestContext());
        SwagLabsProductsPage swagLabsProductsPage = new SwagLabsProductsPage(getDriver(), getTestContext());

        //Steps
        /*
        Input valid username and password then login
         */
        swagLabsLoginPage
                .openLoginPage()
                .inputUserNameTextFieldWithValue("standard_user")
                .inputPasswordTextFieldWithValue("secret_sauce")
                .clickLoginButton();
        /*
        Verify sort by Product Name from A to Z
         */
        swagLabsProductsPage
                .verifyProductPageTitleIs("Products")
                .verifyProductPageActiveSortIs("Name (A to Z)")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/sauce-backpack-1200x1500.0a0b85a3.jpg", "1")
                .verifyProductPageItemNameByIndex("Sauce Labs Backpack","1")
                .verifyProductPageItemPriceByIndex("$29.99","1")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/bike-light-1200x1500.37c843b0.jpg", "2")
                .verifyProductPageItemNameByIndex("Sauce Labs Bike Light","2")
                .verifyProductPageItemPriceByIndex("$9.99","2")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/bolt-shirt-1200x1500.c2599ac5.jpg", "3")
                .verifyProductPageItemNameByIndex("Sauce Labs Bolt T-Shirt","3")
                .verifyProductPageItemPriceByIndex("$15.99","3")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/sauce-pullover-1200x1500.51d7ffaf.jpg", "4")
                .verifyProductPageItemNameByIndex("Sauce Labs Fleece Jacket","4")
                .verifyProductPageItemPriceByIndex("$49.99","4")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/red-onesie-1200x1500.2ec615b2.jpg", "5")
                .verifyProductPageItemNameByIndex("Sauce Labs Onesie","5")
                .verifyProductPageItemPriceByIndex("$7.99","5")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/red-tatt-1200x1500.30dadef4.jpg", "6")
                .verifyProductPageItemNameByIndex("Test.allTheThings() T-Shirt (Red)","6")
                .verifyProductPageItemPriceByIndex("$15.99","6");
    }
    @Test(alwaysRun = true)
    public void TC_2_Login_And_Test_Sort_by_Name_Z_to_A(){
        SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver(), getTestContext());
        SwagLabsProductsPage swagLabsProductsPage = new SwagLabsProductsPage(getDriver(), getTestContext());

        //Steps
        /*
        Input valid username and password then login
         */
        swagLabsLoginPage
                .openLoginPage()
                .inputUserNameTextFieldWithValue("standard_user")
                .inputPasswordTextFieldWithValue("secret_sauce")
                .clickLoginButton();
        /*
        Verify sort by Product Name from Z to A
         */
        swagLabsProductsPage
                .changeProductPageSortingToValue("Name (Z to A)")
                .verifyProductPageActiveSortIs("Name (Z to A)")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/sauce-backpack-1200x1500.0a0b85a3.jpg", "6")
                .verifyProductPageItemNameByIndex("Sauce Labs Backpack","6")
                .verifyProductPageItemPriceByIndex("$29.99","6")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/bike-light-1200x1500.37c843b0.jpg", "5")
                .verifyProductPageItemNameByIndex("Sauce Labs Bike Light","5")
                .verifyProductPageItemPriceByIndex("$9.99","5")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/bolt-shirt-1200x1500.c2599ac5.jpg", "4")
                .verifyProductPageItemNameByIndex("Sauce Labs Bolt T-Shirt","4")
                .verifyProductPageItemPriceByIndex("$15.99","4")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/sauce-pullover-1200x1500.51d7ffaf.jpg", "3")
                .verifyProductPageItemNameByIndex("Sauce Labs Fleece Jacket","3")
                .verifyProductPageItemPriceByIndex("$49.99","3")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/red-onesie-1200x1500.2ec615b2.jpg", "2")
                .verifyProductPageItemNameByIndex("Sauce Labs Onesie","2")
                .verifyProductPageItemPriceByIndex("$7.99","2")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/red-tatt-1200x1500.30dadef4.jpg", "1")
                .verifyProductPageItemNameByIndex("Test.allTheThings() T-Shirt (Red)","1")
                .verifyProductPageItemPriceByIndex("$15.99","1");
    }
    @Test(alwaysRun = true)
    public void TC_3_Login_And_Test_Sort_by_Price_high_to_low(){
        SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver(), getTestContext());
        SwagLabsProductsPage swagLabsProductsPage = new SwagLabsProductsPage(getDriver(), getTestContext());

        //Steps
        /*
        Input valid username and password then login
         */
        swagLabsLoginPage
                .openLoginPage()
                .inputUserNameTextFieldWithValue("standard_user")
                .inputPasswordTextFieldWithValue("secret_sauce")
                .clickLoginButton();
        /*
        Verify sort by Product Price from high to low
         */
        swagLabsProductsPage
                .changeProductPageSortingToValue("Price (high to low)")
                .verifyProductPageActiveSortIs("Price (high to low)")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/sauce-backpack-1200x1500.0a0b85a3.jpg", "2")
                .verifyProductPageItemNameByIndex("Sauce Labs Backpack","2")
                .verifyProductPageItemPriceByIndex("$29.99","2")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/bike-light-1200x1500.37c843b0.jpg", "5")
                .verifyProductPageItemNameByIndex("Sauce Labs Bike Light","5")
                .verifyProductPageItemPriceByIndex("$9.99","5")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/bolt-shirt-1200x1500.c2599ac5.jpg", "3")
                .verifyProductPageItemNameByIndex("Sauce Labs Bolt T-Shirt","3")
                .verifyProductPageItemPriceByIndex("$15.99","3")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/sauce-pullover-1200x1500.51d7ffaf.jpg", "1")
                .verifyProductPageItemNameByIndex("Sauce Labs Fleece Jacket","1")
                .verifyProductPageItemPriceByIndex("$49.99","1")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/red-onesie-1200x1500.2ec615b2.jpg", "6")
                .verifyProductPageItemNameByIndex("Sauce Labs Onesie","6")
                .verifyProductPageItemPriceByIndex("$7.99","6")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/red-tatt-1200x1500.30dadef4.jpg", "4")
                .verifyProductPageItemNameByIndex("Test.allTheThings() T-Shirt (Red)","4")
                .verifyProductPageItemPriceByIndex("$15.99","4");
    }
    @Test(alwaysRun = true)
    public void TC_4_Login_And_Test_Sort_by_Price_low_to_high(){
        SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver(), getTestContext());
        SwagLabsProductsPage swagLabsProductsPage = new SwagLabsProductsPage(getDriver(), getTestContext());

        //Steps
        /*
        Input valid username and password then login
         */
        swagLabsLoginPage
                .openLoginPage()
                .inputUserNameTextFieldWithValue("standard_user")
                .inputPasswordTextFieldWithValue("secret_sauce")
                .clickLoginButton();
        /*
        Verify sort by Product Price from low to high
         */
        swagLabsProductsPage
                .changeProductPageSortingToValue("Price (low to high)")
                .verifyProductPageActiveSortIs("Price (low to high)")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/sauce-backpack-1200x1500.0a0b85a3.jpg", "5")
                .verifyProductPageItemNameByIndex("Sauce Labs Backpack","5")
                .verifyProductPageItemPriceByIndex("$29.99","5")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/bike-light-1200x1500.37c843b0.jpg", "2")
                .verifyProductPageItemNameByIndex("Sauce Labs Bike Light","2")
                .verifyProductPageItemPriceByIndex("$9.99","2")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/bolt-shirt-1200x1500.c2599ac5.jpg", "3")
                .verifyProductPageItemNameByIndex("Sauce Labs Bolt T-Shirt","3")
                .verifyProductPageItemPriceByIndex("$15.99","3")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/sauce-pullover-1200x1500.51d7ffaf.jpg", "6")
                .verifyProductPageItemNameByIndex("Sauce Labs Fleece Jacket","6")
                .verifyProductPageItemPriceByIndex("$49.99","6")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/red-onesie-1200x1500.2ec615b2.jpg", "1")
                .verifyProductPageItemNameByIndex("Sauce Labs Onesie","1")
                .verifyProductPageItemPriceByIndex("$7.99","1")
                .verifyProductPageImageSourceByIndex(ConfigLoader.getEnvironment("url") + "static/media/red-tatt-1200x1500.30dadef4.jpg", "4")
                .verifyProductPageItemNameByIndex("Test.allTheThings() T-Shirt (Red)","4")
                .verifyProductPageItemPriceByIndex("$15.99","4");
    }
}
