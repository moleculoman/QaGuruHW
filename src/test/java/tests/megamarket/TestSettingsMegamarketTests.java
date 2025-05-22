package tests.megamarket;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.open;

public class TestSettingsMegamarketTests {
    @BeforeAll
    static void settingsForBrowserMegaMarketTests() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://megamarket.ru/";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    }
    public TestSettingsMegamarketTests openPage() {
        open("");
        return this;
    }
}
