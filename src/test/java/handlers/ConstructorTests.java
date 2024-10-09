package handlers;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.MainPage;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class ConstructorTests {
    private WebDriver driver;
    private String driverType;
    private MainPage mainPage;

    public ConstructorTests(String driverType) {
        this.driverType = driverType;
    }
    @Before
    public void startUp() {
        if (driverType.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "/WebDriver/bin/chromedriver1.exe");
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.navigate().to("https://stellarburgers.nomoreparties.site/");
        } else if (driverType.equals("yandex")) {
            System.setProperty("webdriver.chrome.driver", "/WebDriver/bin/chromedriver1.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("/Users/Veliguranv/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.navigate().to("https://stellarburgers.nomoreparties.site/");
        }
    }

    @Parameterized.Parameters()
    public static Object[][] getDataDriver() {
        return new Object[][]{
                {"chrome"},
                {"yandex"},
        };
    }
    @Test
    @DisplayName("Переход в раздел 'Булки'")
    public void transitionToBunsInConstructorTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnSaucesButton();
        mainPage.clickOnBunsButton();
        mainPage.checkToppingBun();
    }

    @Test
    @DisplayName("Переход в раздел 'Соусы'.")
    public void transitionToSaucesInConstructorTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnSaucesButton();
        mainPage.checkToppingSauce();
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки'.")
    public void transitionToFillingsInConstructorTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnFillingButton();
        mainPage.checkToppingFillings();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

