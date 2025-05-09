package tests.megamarket;

import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class MegamarketTests extends TestSettingsMegamarketTests {
    @BeforeEach
    void setUp() {
        openPage();
    }

    @ValueSource(strings = {
            "vr"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должны возвращаться результаты")
    @Tag("BLOCKER")
    @DisplayName("TC_1: Проверка на то, что строка поиска работает и возвращает результаты по запросу")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $$("div.search-tab__description-text")
                .findBy(text("Найти товары"))
                .shouldBe(visible)
                .click();

        $("textarea[placeholder='Найти товары']")
                .setValue(searchQuery)
                .pressEnter();

        $$("div.catalog-listing-content")
                .shouldBe(sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "vr для телефона, Очки виртуальной реальности для смартфона",
    })

    @ParameterizedTest(name = "Для поискового запроса {0} в категории должно быть значение {1}")
    @Tag("MAJOR")
    @DisplayName("TC_2: Проверка поиска определенной версии VR")

    void searchResultsShouldContainExpectedVRVersions(String searchQuery, String vrName) {
        $$("div.search-tab__description-text")
                .findBy(text("Найти товары"))
                .shouldBe(visible)
                .click();
        $("textarea[placeholder='Найти товары']")
                .setValue(searchQuery)
                .pressEnter();

        //проверка на то, что категории вообще есть
        $("div.catalog-collections-selector__title").shouldHave(text("Категория"));
        //проверка уже на то, что есть конкретная категория
        $("div.catalog-collections-selector__list")
                .shouldHave(text(vrName));

    }

    @CsvFileSource(resources = "/testdata/searchResultsShouldContainExpectedVRs")
    @ParameterizedTest(name = "Для поискового запроса {0} в товарах должно быть значение {1}")
    @Tag("MINOR")
    @DisplayName("TC_3: Проверка поиска определенной модели VR-шлемов")
    void searchResultsShouldContainExpectedBooks(String searchQuery, String vrModelName) {
        $$("div.search-tab__description-text")
                .findBy(text("Найти товары"))
                .shouldBe(visible)
                .click();
        $("textarea[placeholder='Найти товары']")
                .setValue(searchQuery)
                .pressEnter();
        $("div.r")
                .shouldHave(partialText(vrModelName));
    }


}