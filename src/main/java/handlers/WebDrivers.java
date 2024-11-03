package handlers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class WebDrivers {

    public static WebDriver getWebDriver (String browserName){
               System.setProperty("webdriver.chrome.driver", "/WebDriver/bin/chromedriver1.exe");
            ChromeOptions options = new ChromeOptions();
        switch (browserName) {
        case "chrome":
            return new ChromeDriver(options);

        case "yandex":
            return new ChromeDriver(options.setBinary("/Users/Veliguranv/AppData/Local/Yandex/YandexBrowser/Application/browser.exe"));

        default:
            throw new RuntimeException("Incorrect browser name");
    }

}
}
