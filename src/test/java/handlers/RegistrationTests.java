package handlers;

import io.qameta.allure.Step;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegistrationPage;

import java.util.concurrent.TimeUnit;

import static handlers.WebDrivers.getWebDriver;


@RunWith(Parameterized.class)
public class RegistrationTests {
    private WebDriver driver;
    private String browserName;

    String NAME = "user_" + System.currentTimeMillis();
    String EMAIL = "mail_" + System.currentTimeMillis() + "@mail.ru";
    String PASSWORD = "12345678";
    String PASSWORD_FAILED = "12345";

    @Parameterized.Parameters(name="Browser {0}")
    public static Object[][] initParams() {
        return new Object[][]{
                {"chrome"},
                {"yandex"},
        };
    }
    public RegistrationTests(String browserName) {
        this.browserName = browserName;
    }
    @Before
    @Step("Запуск браузера")
    public void startUp() {
        driver = getWebDriver(browserName);
        driver.get(Endpoints.URL_MAIN_PAGE);
    }

    @Test
    @DisplayName("Успешная регистрация.")
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegister();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitForLoadRegisterPage();
        registrationPage.registration(NAME, EMAIL, PASSWORD);
        registrationPage.clickRegisterButton();
        loginPage.waitForLoadEntrance();

    }

    @Test
    @DisplayName("Ошибка для некорректного пароля. Минимальный пароль — шесть символов..")
    public void failRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegister();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitForLoadRegisterPage();
        registrationPage.registration(NAME, EMAIL, PASSWORD_FAILED);
        registrationPage.clickRegisterButton();
        registrationPage.waitErrorIsVisible();

    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
           new StellarBurgerClient().deleteTestUser(EMAIL, PASSWORD);
        }
        }


