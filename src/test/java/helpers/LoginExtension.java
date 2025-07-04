package helpers;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import tests.demoqa.Bookshop.api.AuthorizationApi;
import tests.demoqa.Bookshop.models.LoginResponseModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static tests.demoqa.Bookshop.tests.TestData.*;


public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context){
        LoginResponseModel loginResponse = AuthorizationApi.login();
        step("Авторизация c @WithLogin", () ->
        open("/favicon.ico"));
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));

         userId = loginResponse.getUserId();
         TOKEN = loginResponse.getToken();
         EXPIRES = loginResponse.getExpires();
    }
}
