package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
   private final By profileNavLink = By.xpath(".//a[contains(@class, 'Account_link_active')]");
    private final By logOutLink = By.xpath(".//nav[starts-with(@class, 'Account_nav')]/ul/li/button");
    private final By modalOverlay = By.xpath(".//div[starts-with(@class, 'App_App')]/div/div[starts-with(@class, 'Modal_modal_overlay')]");
    public final By textOnProfilePage = By.xpath(".//nav/p[text()='В этом разделе вы можете изменить свои персональные данные']");
    public void waitAuthFormVisible() {
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(profileNavLink));
    }
    public void waitButtonIsClickable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOf(driver.findElement(modalOverlay)));
    }
    public void clickLogoutButton() {
        waitButtonIsClickable();
        driver.findElement(logOutLink).click();
    }
    public void waitForLoadProfilePage() {
              new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textOnProfilePage));
    }
}

