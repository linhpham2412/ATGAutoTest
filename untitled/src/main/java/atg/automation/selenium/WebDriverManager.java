package atg.automation.selenium;

import atg.automation.config.ConfigLoader;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class WebDriverManager {
    private static WebDriver driver;

    public static void iniDriver(String browser) throws MalformedURLException {
        if (ConfigLoader.getConfiguration("testLocation").equalsIgnoreCase("local"))
            driver = WebDriverCreator.createLocalDriver(browser);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
