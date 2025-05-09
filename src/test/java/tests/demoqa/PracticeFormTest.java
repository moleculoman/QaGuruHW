package tests.demoqa;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;
import utils.DataFaker;

public class PracticeFormTest extends TestSettings{

    RegistrationPage registrationPage = new RegistrationPage();
    DataFaker dF = new DataFaker();

    String firstName = dF.getRandomFirstName();
    String lastName = dF.getRandomLastName();
    String email = dF.getRandomEmail();
    String gender = dF.getRandomGender();
    String phoneNumber = dF.getRandomPhoneNumber();
    String day = dF.getRandomBirthDay();
    String month = dF.getRandomBirthMonth();
    String year = dF.getRandomBirthYear();
    String subject = dF.getRandomSubject();
    String hobbies = dF.getRandomHobbies();
    String address = dF.getRandomAddress();
    String state = dF.getRandomState();
    String city = dF.getRandomCity(state);

    @Test
    @DisplayName("Успешное заполнение всех полей формы")
    public void fillStudentRegistrationFormTest() {
        registrationPage.openPage()
        .removeBanners()
        .setFirstName(firstName)
        .setLastName(lastName)
        .setEmail(email)
        .setGender(gender)
        .setUserNumber(phoneNumber)
        .setDateOfBirth(day, month, year)
        .setSubject(subject)
        .setHobbies(hobbies)
        .setPicture("example.jpg")
        .setCurrentAddress(address)
        .setStateAndCity(state, city)
        .clickSubmitButton();

        // Проверки
        registrationPage.checkResult("Student Name" , firstName + " " +lastName)
        .checkResult("Student Email", email)
        .checkResult("Gender", gender)
        .checkResult("Mobile", phoneNumber)
        .checkResult("Date of Birth", day + " " + month + "," + year)
        .checkResult("Subjects", subject)
        .checkResult("Hobbies", hobbies)
        .checkResult("Picture","example.jpg" )
        .checkResult("Address", address)
        .checkResult("State and City", state +" "+ city);
       }

    @Test
    @DisplayName("Успешная отправка формы регистрации только с обязательными полями")
    public void successSubmitRegFormWithRequiredFieldsTest(){
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .clickSubmitButton();
        registrationPage.
                checkResult("Student Name" , firstName + " " +lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber);
    }

    @Test
    @DisplayName("Негативная проверка - заполнены не все обязательные поля")
    public void testStudentRegistrationWithMissingRequiredFields(){
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .clickSubmitButton();
        registrationPage.assertModalIsNotVisible();
    }
}

