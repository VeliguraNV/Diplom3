package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPassPage {
    private WebDriver driver;
    private final By inputEmail = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    private final By recoverButton = By.xpath(".//form/button[text()='Восстановить']");
    public final By recoverPassword = By.xpath(".//main/div/h2[text()='Восстановление пароля']");
    private final By authLink = By.xpath(".//a[starts-with(@class,'Auth_link')]");
    private final By modalOverlay = By.xpath(".//div[starts-with(@class, 'App_App')]/div[starts-with(@class, 'Modal_modal')]");
    private final By loginButton = By.xpath(".//div/p/a[@href = '/login' and text() = 'Войти']");
    public ForgotPassPage(WebDriver driver) {
        this.driver = driver;
    }
    public void setEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }
    public void clickOnRecoverButton() {
        driver.findElement(recoverButton).click();
            }
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
            }
    public void clickAuthLink() {
        waitButtonIsClickable();
        driver.findElement(authLink).click();
    }
    private void waitButtonIsClickable() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.invisibilityOf(driver.findElement(modalOverlay)));
    }
    public void recoverPassword(String email) {
        setEmail(email);
        clickOnRecoverButton();
    }
    public void waitForLoadedRecoverPassword() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(recoverPassword));
    }
}

