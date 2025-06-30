package tests.restApi.reqres.lombokTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.restApi.reqres.TestSettingsReqresTests;
import tests.restApi.reqres.lombokModels.LoginBodyModel;
import tests.restApi.reqres.lombokModels.LoginResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static tests.restApi.specs.UsersSpecs.*;

@Tag("ReqresTests")
public class LoginUserLombokTests extends TestSettingsReqresTests {

    public int minTokenLength = 16;

    @Test
    @DisplayName("Успешная авторизация")
    void loginUserSuccessfullyLombokTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");
        LoginResponseModel response = step("Make request", () ->
                given(userRequestSpec)
                        .body(authData)
                .when()
                        .post(LOGIN_END_POINT)
                .then()
                        .spec(responseSpec200)
                        .extract().as(LoginResponseModel.class));
        step("Check response", () -> {
            String token = response.getToken();
            assertEquals("QpwL5tke4Pnpja7X4", token);
            assertNotNull(token);
            assertFalse(token.isBlank());
            assertThat(token.length()).isGreaterThanOrEqualTo(minTokenLength);
            assertThat(token).matches("^[a-zA-Z0-9]+$");
        });
    }

    @Test
    @DisplayName("Ошибка авторизации: пароль отсутствует")
    void loginUserWithMissingPasswordLombokTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        LoginResponseModel response = step("Make request", () ->
                given(userRequestSpec)
                        .body(authData)
                .when()
                        .post(LOGIN_END_POINT)
                .then()
                        .spec(responseSpec400)
                        .extract().as(LoginResponseModel.class));
        step("Check response", () ->
        assertEquals("Missing password",response.getError()));
    }
    @Test
    @DisplayName("Ошибка авторизации: емайл отсутствует")
    void loginUserWithMissingEmailLombokTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setPassword("pistol");
        LoginResponseModel response =step("Make request", () ->
                given(userRequestSpec)
                        .body(authData)
                .when()
                        .post(LOGIN_END_POINT)
                .then()
                        .spec(responseSpec400)
                        .extract().as(LoginResponseModel.class));
        step("Check response", () ->
        assertEquals("Missing email or username",response.getError()));
    }
}
