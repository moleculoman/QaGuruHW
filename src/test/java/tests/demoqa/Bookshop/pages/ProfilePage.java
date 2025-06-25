package tests.demoqa.Bookshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private final SelenideElement tableNoData = $(".rt-noData");

    @Step("Открываем страницу профиля")
    public ProfilePage openPage() {
        open("/profile");
        return this;
    }

    @Step("Проверка, что таблица с книгами пуста")
    public ProfilePage checkEmptyTableWithoutBooks() {
        tableNoData.shouldBe(visible);
        return this;
    }
}
