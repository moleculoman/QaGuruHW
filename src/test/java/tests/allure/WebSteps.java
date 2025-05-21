package tests.allure;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Открытие главной страницы")
    public void openMainPage (){
        open("https://github.com");
    }
    @Step ("Поиск репозитория")
    public void searchForRepository(String repo){
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").setValue(repo).pressEnter();
    }
    @Step ("Клик на ссылку репозитория")
    public void chooseRepository(String repo){
        $(linkText(repo)).click();
    }
    @Step ("Проверка названия Issue в репозитории")
    public void issueCheck(){
        $("[data-content=Issues]").shouldHave(text("Issues"));
    }
}
