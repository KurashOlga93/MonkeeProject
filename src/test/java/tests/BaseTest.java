package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import config.TestConfig;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.EntryListPage;
import pages.EntryPage;
import pages.LoginPage;
import steps.EntrySteps;
import steps.LoginSteps;
import steps.SettingsSteps;
import utils.PropertyReader;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@Listeners(TestListener.class)
public class BaseTest implements ITestConstants {

    public static String USER = System.getProperty("user");
    //public static String USER = TestConfig.getEnvConfig().user();
    public static String PASSWORD = System.getProperty("password");
    //public static String PASSWORD = TestConfig.getEnvConfig().password();
    public static String LOGIN_URL = PropertyReader.getProperty("loginUrl");
    public static String ENTRIES_URL = PropertyReader.getProperty("entriesUrl");

    protected LoginSteps loginSteps;
    protected LoginPage loginPage;
    protected EntryListPage entryListPage;
    protected EntryPage entryPage;
    protected EntrySteps entrySteps;
    protected SettingsSteps settingsSteps;

    public void initPages() {
        loginSteps = new LoginSteps();
        loginPage = new LoginPage();
        entryListPage = new EntryListPage();
        entryPage = new EntryPage();
        entrySteps = new EntrySteps();
        settingsSteps = new SettingsSteps();
    }

    @BeforeMethod
    public void initTest() {

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        options.addArguments("--disable-popup-blocking");
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        setWebDriver(driver);

        Configuration.browser = "chrome";
        Configuration.timeout = 15000;
        Configuration.headless = false;
        Configuration.browserSize = "1024x768";
        initPages();
    }

    @AfterMethod(alwaysRun = true)
    public void endTest() {
        open(ENTRIES_URL);
        if (!entryListPage.getEntriesList().isEmpty()) {
            entryListPage.deleteAllEntries();
        }
        WebDriverRunner.closeWebDriver();
    }
}