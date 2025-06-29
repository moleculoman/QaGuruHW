package tests.restApi.reqres.lombockTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.restApi.reqres.TestSettingsReqresTests;
import tests.restApi.reqres.lombokModels.RegisterBodyModel;
import tests.restApi.reqres.lombokModels.RegisterResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.restApi.specs.UsersSpecs.*;


@Tag("ReqresTests")
public class RegisterUserLombokTests extends TestSettingsReqresTests {
    @Test
    @DisplayName("Успешная регистрация")
    void registerUserSuccessfullyTest() {
        RegisterBodyModel regData = new RegisterBodyModel();
        regData.setEmail("eve.holt@reqres.in");
        regData.setPassword("pistol");
            RegisterResponseModel response = step("Make request", () ->
                    given(userRequestSpec)
                            .body(regData)
                    .when()
                            .post(REGISTER_END_POINT)
                    .then()
                            .spec(responseSpec200)
                            .extract().as(RegisterResponseModel.class));
            step("Check response", () ->
                    assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
        }

    @Test
    @DisplayName("Ошибка регистрации: пароль отсутствует")
    void registerUserWithMissingPasswordTest() {
        RegisterBodyModel regData = new RegisterBodyModel();
        regData.setEmail("eve.holt@reqres.in");
        RegisterResponseModel response = step("Make request", () ->
                given(userRequestSpec)
                    .body(regData)
                .when()
                    .post(REGISTER_END_POINT)
                .then()
                        .spec(responseSpec400)
                        .extract().as(RegisterResponseModel.class));
        step("Check response", () ->
        assertEquals("Missing password",response.getError()));
    }
    @Test
    @DisplayName("Ошибка регистрации: емайл отсутствует")
    void registerUserWithMissingEmailTest() {
        RegisterBodyModel regData = new RegisterBodyModel();
        regData.setPassword("pistol");
        RegisterResponseModel response = step("Make request", () ->
            given(userRequestSpec)
                .body(regData)
            .when()
                .post(REGISTER_END_POINT)
            .then()
                    .spec(responseSpec400)
                    .extract().as(RegisterResponseModel.class));
        step("Check response", () ->
                assertEquals("Missing email or username",response.getError()));
    }
}