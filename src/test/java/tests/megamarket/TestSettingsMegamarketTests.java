package tests.megamarket;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.open;

public class TestSettingsMegamarketTests {
    @BeforeAll
    static void settingsForBrowserMegaMarketTests() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://megamarket.ru/";
        Configuration.pageLoadStrategy = "eager";
    }
    public TestSettingsMegamarketTests openPage() {
        open("");
        return this;
    }
}
