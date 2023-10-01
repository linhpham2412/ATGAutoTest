package atg.automation.selenium;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class WebDriverCreator {

    private static final String webDriverLocation = "C:\\Users\\Admin\\OneDrive\\Documents\\LinhPham\\TShapeTraining\\Webdriver\\";

    public static WebDriver createLocalDriver(String browser) {
        WebDriver driver = null;
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", webDriverLocation + "chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized"); // open Browser in maximized mode
            options.addArguments("disable-infobars"); // disabling infobars
            options.addArguments("--disable-extensions"); // disabling extensions
            options.addArguments("--disable-gpu"); // applicable to Windows os only
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            options.addArguments("--no-sandbox"); // Bypass OS security model
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", webDriverLocation + "geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions().setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe").setAcceptInsecureCerts(true);
            driver = new FirefoxDriver(options);
        }
        System.out.println("Local driver and run on browser [" + browser + "]");
        return driver;
    }
}
