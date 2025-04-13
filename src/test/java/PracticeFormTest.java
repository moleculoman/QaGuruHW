import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
        $("#userEmail").setValue("petrovpetr@mail.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");

        //Проверка формы введения даты рождения
        $("#dateOfBirthInput").click();


    }



}
