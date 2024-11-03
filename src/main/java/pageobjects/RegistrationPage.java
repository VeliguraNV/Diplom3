package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    public final WebDriver driver;

    public RegistrationPage(WebDriver webDriver) {
        this.driver = webDriver;
    }
    public final By regTitle = By.xpath(".//h2[text()='Регистрация']");
    public final By inputs = By.xpath(".//input[@class='text input__textfield text_type_main-default']");
    public final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    public final By errorMessage = By.xpath(".//p[text()='Некорректный пароль']");
    public final By authButton = By.xpath(".//a[@class='Auth_link__1fOlj']");
    public final By registerText = By.xpath(".//div/h2[text()='Регистрация']");


    @Step("Заполнение имени")
    public void setName(String name) {
        driver.findElements(inputs).get(0).sendKeys(name);
    }
    @Step("Заполнение email")
    public void setEmail(String email) {
        driver.findElements(inputs).get(1).sendKeys(email);
    }
    @Step("Заполнение password")
    public void setPassword(String password) {
        driver.findElements(inputs).get(2).sendKeys(password);
    }

    @Step("Нажатие на кнопку регистрации")
    public void clickRegisterButton() {
       // waitButtonIsClickable();
        driver.findElement(registerButton).click();
    }

    private void waitButtonIsClickable() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOf(driver.findElement(regTitle)));
    }
    public void waitFormSubmitted(String expectedTitle) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.textToBe(regTitle, expectedTitle));
    }
    public void waitErrorIsVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(driver.findElement(errorMessage)));
    }
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void registration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }
    public void waitForLoadRegisterPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(registerText));
    }
}
