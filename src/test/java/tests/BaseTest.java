package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.EntriesPage;
import pages.LoginPage;
import steps.LoginSteps;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)

public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    EntriesPage entriesPage;
    LoginSteps loginSteps;


    @BeforeMethod
    public void initTest() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        initPages();

    }

    public void initPages() {
        loginPage = new LoginPage(driver);
        entriesPage = new EntriesPage(driver);
        loginSteps = new LoginSteps(driver);

    }

    @AfterMethod
    public void endTest() {
        driver.quit();
    }
}
