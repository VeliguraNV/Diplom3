package handlers;


import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.ForgotPassPage;
import pageobjects.RegistrationPage;

import static handlers.WebDrivers.getWebDriver;

@RunWith(Parameterized.class)
    public class LoginTest {
        private WebDriver driver;
        private String browserName;
        private final static String EMAIL = "Veligura_33@gmail.com";
        private final static String PASSWORD = "12345678";

    @Parameterized.Parameters(name="Browser {0}")
        public static Object[][] initParams() {
            return new Object[][]{
                    {"chrome"},
                    {"yandex"},
            };
        }
    public LoginTest(String browserName) {
        this.browserName = browserName;
    }
        @Before
        @Step("Запуск браузера")
        public void startUp() {
            driver = getWebDriver(browserName);
            driver.get(Endpoints.URL_MAIN_PAGE);
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
