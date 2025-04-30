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
        .removeBanners()
        .setFirstName("Petr")
        .setLastName("Petrov")
        .setEmail("petrovpetr@mail.ru")
        .setGender("Male")
        .setUserNumber("1234567890")
        .setDateOfBirth("29", "May", "1997")
        .setSubject("English")
        .setHobbies("Music")
        .setPicture("example.jpg")
        .setCurrentAddress("Krasnoyarsk region, Krasnoyarsk city")
        .setStateAndCity("Uttar Pradesh", "Agra")
        .clickSubmitButton();

        // Проверки
        registrationPage.checkResult("Student Name" ,"Petr Petrov")
        .checkResult("Student Email", "petrovpetr@mail.ru")
        .checkResult("Gender", "Male")
        .checkResult("Mobile","1234567890")
        .checkResult("Date of Birth", "29 May,1997")
        .checkResult("Subjects", "English")
        .checkResult("Hobbies","Music" )
        .checkResult("Picture","example.jpg" )
        .checkResult("Address", "Krasnoyarsk region, Krasnoyarsk city")
        .checkResult("State and City", "Uttar Pradesh Agra");
       }

    @Test
    @DisplayName("Успешная отправка формы регистрации только с обязательными полями")
    public void successSubmitRegFormWithRequiredFieldsTest(){
        registrationPage.openPage()
                .removeBanners()
                .setFirstName("Petr")
                .setLastName("Petrov")
                .setGender("Male")
                .setUserNumber("1234567890")
                .clickSubmitButton();
        registrationPage.
                checkResult("Student Name" ,"Petr Petrov")
                .checkResult("Gender", "Male")
                .checkResult("Mobile","1234567890");
    }

    @Test
    @DisplayName("Негативная проверка - заполнены не все обязательные поля")
    public void testStudentRegistrationWithMissingRequiredFields(){
        registrationPage.openPage()
                .removeBanners()
                .setFirstName("Petr")
                .setGender("Male")
                .setUserNumber("1234567890")
                .clickSubmitButton();
        registrationPage.assertModalIsNotVisible();
    }
}

