package tests.megamarket;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.Attach;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

public class TestSettingsMegamarketTests {
    @BeforeAll
    static void settingsForBrowserMegaMarketTests() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://megamarket.ru/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }
    public TestSettingsMegamarketTests openPage() {
        open("");
        return this;
    }
}
