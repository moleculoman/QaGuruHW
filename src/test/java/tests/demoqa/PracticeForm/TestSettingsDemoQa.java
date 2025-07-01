package tests.demoqa.PracticeForm;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.LoggerFactory;
import helpers.Attach;

import java.util.Map;
import org.slf4j.Logger;
import tests.demoqa.PracticeForm.config.ConfigReader;
import tests.demoqa.PracticeForm.config.ProjectConfiguration;
import tests.demoqa.PracticeForm.config.web.WebConfig;

import static com.codeborne.selenide.Selenide.open;

public class TestSettingsDemoQa {
    private static final Logger logger = LoggerFactory.getLogger(TestSettingsDemoQa.class);
    private static final WebConfig webConfig = ConfigReader.Instance.read();

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        ProjectConfiguration projectConfiguration = new ProjectConfiguration(webConfig);
        projectConfiguration.webConfig();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
