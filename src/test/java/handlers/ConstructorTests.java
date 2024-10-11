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

import static handlers.WebDrivers.getWebDriver;

@RunWith(Parameterized.class)
public class ConstructorTests {
    private WebDriver driver;
    private String browserName;
    @Parameterized.Parameters(name="Browser {0}")
    public static Object[][] initParams() {
        return new Object[][]{
                {"chrome"},
                {"yandex"},
        };
    }
    public ConstructorTests(String browserName){
        this.browserName = browserName;
    }
    @Before
        @Step("Запуск браузера")
        public void startUp() {
            driver = getWebDriver(browserName);
            driver.get(Endpoints.URL_MAIN_PAGE);
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

