package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By inputEmail = By.xpath(".//input[@name='name']");
    private final By inputPassword = By.xpath(".//input[@name='Пароль']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerButton = By.xpath(".//a[text()='Зарегистрироваться']");
    public final By entrance = By.xpath(".//main/div/h2[text()='Вход']");
    private final By forgotPasswordButton = By.xpath(".//a[@href='/forgot-password' and text()='Восстановить пароль']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    public final By logo = By.xpath(".//div/a[@href='/']");

    public void setEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }

    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickOnRegister() {
        driver.findElement(registerButton).click();
    }

    public void clickOnForgotPasswordLink() {
        driver.findElement(forgotPasswordButton).click();
            }
    public void authorization(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickOnLoginButton();
    }
    public void waitForLoadEntrance() { // ожидание загрузки страницы "вход"
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(entrance));
    }
    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
    }
    public void clickOnLogo() {
        driver.findElement(logo).click();
    }
}
