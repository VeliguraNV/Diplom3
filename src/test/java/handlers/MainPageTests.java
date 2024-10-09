package handlers;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.ProfilePage;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class MainPageTests {
    private WebDriver driver;
    private String driverType;
    private final static String EMAIL = "Veligura_33@gmail.com";
    private final static String PASSWORD = "12345678";

    public MainPageTests(String driverType) {
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
    @DisplayName("Переход в личный кабинет.")
    public void toProfilePageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        Assert.assertTrue("Страница авторизации не отобразилась", driver.findElement(loginPage.entrance).isDisplayed());
    }

    @Test
    @DisplayName("Переход в конструктор из личного кабинета.")
    public void fromProfileToKonstructorPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForInvisibilityLoadingAnimation();
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.clickOnConstructorButton();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Не удалось перейти", driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }

    @Test
    @DisplayName("Клик по логотипу 'Stellar Burgers' из личного кабинета.")
    public void toStellarBurgersFromProfilePageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.clickOnLogo();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Не удалось перейти", driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void exitFromProfileTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.authorization(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();
        mainPage.clickOnAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadProfilePage();
        profilePage.clickLogoutButton();
        mainPage.waitForInvisibilityLoadingAnimation();
        Assert.assertTrue("Не удалось выйти", driver.findElement(loginPage.entrance).isDisplayed());
    }

    @After
       public void teardown() {
            driver.quit();
    }
}
