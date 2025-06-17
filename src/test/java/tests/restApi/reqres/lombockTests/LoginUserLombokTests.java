package tests.restApi.reqres.lombockTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.restApi.reqres.TestSettingsReqresTests;
import tests.restApi.reqres.lombokModels.LoginBodyModel;
import tests.restApi.reqres.lombokModels.LoginResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.UsersSpecs.loginUserRequestSpec;
import static specs.UsersSpecs.loginUserResponseSpec200;
import static specs.UsersSpecs.loginUserResponseSpec400;

@Tag("ReqresTests")
public class LoginUserLombokTests extends TestSettingsReqresTests {
    @Test
    @DisplayName("Успешная авторизация")
    void loginUserSuccessfullyLombokTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");
        LoginResponseModel response = step("Make request", () ->
                given(loginUserRequestSpec)
                        .body(authData)
                .when()
                        .post(LOGIN_END_POINT)
                .then()
                        .spec(loginUserResponseSpec200)
                        .extract().as(LoginResponseModel.class));
        step("Check response", () ->
        assertEquals("QpwL5tke4Pnpja7X4",response.getToken()));
    }

    @Test
    @DisplayName("Ошибка авторизации: пароль отсутствует")
    void loginUserWithMissingPasswordLombokTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        LoginResponseModel response = step("Make request", () ->
                given(loginUserRequestSpec)
                        .body(authData)
                .when()
                        .post(LOGIN_END_POINT)
                .then()
                        .spec(loginUserResponseSpec400)
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
                given(loginUserRequestSpec)
                        .body(authData)
                .when()
                        .post(LOGIN_END_POINT)
                .then()
                        .spec(loginUserResponseSpec400)
                        .extract().as(LoginResponseModel.class));
        step("Check response", () ->
        assertEquals("Missing email or username",response.getError()));
    }
}
