package handlers;

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


@RunWith(Parameterized.class)
public class RegistrationTests {
    private WebDriver driver;
    private String driverType;

    String NAME = "user_" + System.currentTimeMillis();
    String EMAIL = "mail_" + System.currentTimeMillis() + "@mail.ru";
    String PASSWORD = "12345678";
    String PASSWORD_FAILED = "12345";

    public RegistrationTests(String driverType) {
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


