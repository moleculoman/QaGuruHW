import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {
    @BeforeAll
    static void settingsForBrowser() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    @DisplayName("Успешное заполнение всех полей формы")
    public void fillStudentRegistrationFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Petr");
        $("#lastName").setValue("Petrov");
        $("#userEmail").setValue("petrovpetr@mail.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");

        //Проверка формы введения даты рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__month-select").selectOption("May");
        $$(".react-datepicker__day--029").filterBy(not(cssClass("react-datepicker__day--outside-month"))).first().click();

        //Проверка списка учебных предметов
        $("#subjectsInput").setValue("English").pressEnter();
        $("#hobbiesWrapper").$(byText("Music")).click();
        //Загрузка фото
        $("#uploadPicture").uploadFromClasspath("example.jpg");

        //Проверка адреса
        $("#currentAddress").setValue("Krasnoyarsk region, Krasnoyarsk city").pressEnter();
        $("#react-select-3-input").setValue("Uttar Pradesh").pressEnter();
        $("#react-select-4-input").setValue("Agra").pressEnter();

        $("#submit").click();

        // Проверки
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Petr Petrov"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("petrovpetr@mail.ru"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("29 May,1997"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("English"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Music"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("example.jpg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Krasnoyarsk region, Krasnoyarsk city"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Uttar Pradesh Agra"));


    }



}
