package atg.automation.TestCase;

import atg.automation.PageModal.SwagLabsLoginPage;
import atg.automation.setup.WebDriverTestNGSetupBase;
import org.testng.annotations.Test;

public class Verify_Sorting_Functions_In_Product_Page extends WebDriverTestNGSetupBase {

    @Test(alwaysRun = true)
    public void TC_1_Login_And_Test_Sort_by_Name_A_to_Z(){
        SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver(),getTestContext());

        //Steps
        swagLabsLoginPage
                .openLoginPage()
                .inputUserNameTextFieldWithValue("standard_user")
                .inputPasswordTextFieldWithValue("secret_sauce")
                .clickLoginButton();
    }
}
