package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;
import utils.TestListener;

import java.time.Duration;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    public WebDriver driver;
    LoginPage loginPage;
    ProductsPage productPage;
    CartPage cartPage;
    String user;
    String password;

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--guest");
            //options.addArguments("headless");
            //options.addArguments("--windows-size=1920,1080");
            driver = new ChromeDriver(options);
            //browser.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);

        user = PropertyReader.getProperty("sausdemmo.admin_user");
        password = PropertyReader.getProperty("sausdemmo.password");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}