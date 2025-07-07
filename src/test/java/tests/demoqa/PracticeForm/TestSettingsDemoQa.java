package tests.demoqa.PracticeForm;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.LoggerFactory;
import helpers.Attach;
import org.slf4j.Logger;
import tests.demoqa.PracticeForm.config.web.WebConfig;

public class TestSettingsDemoQa {
    private static final Logger logger = LoggerFactory.getLogger(TestSettingsDemoQa.class);
    private static final WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser().toString();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.pageLoadStrategy = "eager";
        if (webConfig.isRemote()) {
            Configuration.remote = webConfig.remoteUrl();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            //capabilities.setCapability("enableVNC", true);
            //capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;

            if (webConfig.remoteUrl() == null || webConfig.remoteUrl().isEmpty()) {
                throw new IllegalStateException("Remote URL is not configured or is empty");
            }
        }
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }

    @BeforeEach
    void addListener(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}