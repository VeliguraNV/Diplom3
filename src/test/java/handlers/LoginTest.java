package handlers;


import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.ForgotPassPage;

import java.util.concurrent.TimeUnit;

    @RunWith(Parameterized.class)
    public class LoginTest {
        private WebDriver driver;
        private String driverType;
        private final static String EMAIL = "Veligura_33@gmail.com";
        private final static String PASSWORD = "12345678";

        public LoginTest(String driverType) {
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
        @DisplayName("Вход по кнопке 'Войти в аккаунт'.")
        public void enterByLoginButtonTest() {
            MainPage mainPage = new MainPage(driver);
            mainPage.clickOnLoginButton();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.authorization(EMAIL, PASSWORD);
            mainPage.waitForLoadMainPage();
        }

        @Test
        @DisplayName("вход через кнопку «Личный кабинет»")
        public void enterByPersonalAccountButtonTest() {
            MainPage mainPage = new MainPage(driver);
            mainPage.clickOnAccountButton();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.authorization(EMAIL, PASSWORD);
            mainPage.waitForLoadMainPage();
        }

        @Test
        @DisplayName("вход через кнопку в форме регистрации,")
        public void enterByRegistrationFormTest() {
            MainPage mainPage = new MainPage(driver);
            mainPage.clickOnAccountButton();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.authorization(EMAIL, PASSWORD);
            mainPage.waitForLoadMainPage();
        }
        @Test
        @DisplayName("Вход через кнопку в форме восстановления пароля.")
        public void enterByPasswordRecoveryFormatTest() {
            MainPage mainPage = new MainPage(driver);
            mainPage.clickOnAccountButton();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickOnForgotPasswordLink();
            ForgotPassPage passwordPage = new ForgotPassPage(driver);
            passwordPage.waitForLoadedRecoverPassword();
            passwordPage.clickOnLoginButton();
            loginPage.authorization(EMAIL, PASSWORD);
            mainPage.waitForLoadMainPage();
        }

        @After
        public void teardown() {
            // Закрой браузер
            driver.quit();
        }
    }
