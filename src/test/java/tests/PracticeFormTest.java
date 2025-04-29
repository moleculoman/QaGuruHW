package tests;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest extends TestSettings{

    RegistrationPage registrationPage = new RegistrationPage();
    @Test
    @DisplayName("Успешное заполнение всех полей формы")
    public void fillStudentRegistrationFormTest() {
        registrationPage.openPage()
        .setFirstName("Petr")
        .setLastName("Petrov")
        .setEmail("petrovpetr@mail.ru")
        .setGender("Male")
        .setUserNumber("1234567890")
        //Проверка формы введения даты рождения
        .setDateOfBirth("29", "May", "1997")
        //Проверка списка учебных предметов и выбор хобби
        .setSubject("English")
        .setHobbies("Music")
        //Загрузка фото
        .setPicture("example.jpg")
        //Проверка адреса
        .setCurrentAddress("Krasnoyarsk region, Krasnoyarsk city")
        //Проверка Страны и Города
        .setStateAndCity("Uttar Pradesh", "Agra")
        //Отправка данных финальная
        .clickSubmitButton();



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
