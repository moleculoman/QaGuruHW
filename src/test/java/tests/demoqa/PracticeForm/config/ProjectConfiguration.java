package tests.demoqa.PracticeForm.config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.demoqa.PracticeForm.config.web.WebConfig;

public class ProjectConfiguration {
    static String SELENOID_URL = System.getProperty("selenoid.url");
    static String SELENOID_LOGIN = System.getProperty("selenoid.login");
    static String SELENOID_PASSWORD = System.getProperty("selenoid.password");
    private final WebConfig webConfig;

    public ProjectConfiguration(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void webConfig() {
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser();
        if (webConfig.browser() == null) {
            throw new IllegalStateException("Browser configuration is missing");
        }
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.pageLoadStrategy = "eager";

        if (webConfig.isRemote()) {
            Configuration.remote = webConfig.remoteUrl();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            //capabilities.setCapability("enableVNC", true);
            //capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }
        if (webConfig.isRemote() && webConfig.remoteUrl() == null || webConfig.remoteUrl().isEmpty()) {
            throw new IllegalStateException("Remote URL is not configured");
        }
    }
}