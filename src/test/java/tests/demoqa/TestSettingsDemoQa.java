package tests.demoqa;

import com.codeborne.selenide.Configuration;
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
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browser.version", "128.0");
        Configuration.pageLoadStrategy = "eager";
        final String SELENOID_URL = System.getProperty("selenoid.url");
        final String SELENOID_LOGIN = System.getProperty("selenoid.login");;
        final String SELENOID_PASSWORD = System.getProperty("selenoid.password");
        Configuration.remote = "https://" + SELENOID_LOGIN + ":" + SELENOID_PASSWORD + "@" + SELENOID_URL + "/wd/hub";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.holdBrowserOpen = false;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }
}
