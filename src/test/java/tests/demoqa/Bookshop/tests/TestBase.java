package tests.demoqa.Bookshop.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    public static final String ALL_BOOKS_END_POINT = "/BookStore/v1/Books";
    public static final String SINGLE_BOOK_END_POINT = "/BookStore/v1/Book";
    public static final String LOGIN_END_POINT = "/Account/v1/Login";


    private static final String SELENOID_URL = System.getProperty("SELENOID_URL");
    private static final String SELENOID_LOGIN = System.getProperty("SELENOID_LOGIN");
    private static final String SELENOID_PASSWORD = System.getProperty("SELENOID_PASSWORD");

    @BeforeAll
    public static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browser.version", "");
        Configuration.browserSize = System.getProperty("browser.size", "1920x1080");

        Configuration.remote = "https://" + SELENOID_LOGIN + ":" + SELENOID_PASSWORD + "@" + SELENOID_URL + "/wd/hub";        DesiredCapabilities capabilities = new DesiredCapabilities();
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

    @AfterEach
    void shutDown() {
        closeWebDriver();
    }



}