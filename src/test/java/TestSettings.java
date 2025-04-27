import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestSettings {
    @BeforeAll
    static void settingsForBrowser() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

}
