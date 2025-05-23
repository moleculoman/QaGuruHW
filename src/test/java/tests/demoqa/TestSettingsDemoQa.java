package tests.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.Attach;

import java.util.Map;

public class TestSettingsDemoQa {
    @BeforeAll
    static void settingsForBrowserDemoQa() {
        Configuration.browserSize = System.getProperty("browser.size", "1920x1080");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browser.version", "128.0");
        Configuration.pageLoadStrategy = "eager";
        String SELENOID_URL = System.getProperty("SELENOID_URL");
        String SELENOID_LOGIN = System.getProperty("SELENOID_LOGIN");
        String SELENOID_PASSWORD = System.getProperty("SELENOID_PASSWORD");

        Configuration.remote = "https://" + SELENOID_LOGIN + ":" + SELENOID_PASSWORD + "@" + SELENOID_URL + "/wd/hub";
        Configuration.baseUrl = "https://demoqa.com";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.holdBrowserOpen = false;

        System.out.println("baseUrl: " + Configuration.baseUrl);
        System.out.println("remote: " + Configuration.remote);
        System.out.println("browser: " + Configuration.browser);
        System.out.println("browserVersion: " + Configuration.browserVersion);
        System.out.println("Current URL: " + WebDriverRunner.getWebDriver().getCurrentUrl());
        System.out.println("Is browser open? " + WebDriverRunner.hasWebDriverStarted());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
