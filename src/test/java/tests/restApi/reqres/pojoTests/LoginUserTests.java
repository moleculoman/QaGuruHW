package tests.restApi.reqres.pojoTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.restApi.reqres.TestSettingsReqresTests;
import tests.restApi.reqres.pojoModels.LoginBodyPojoModel;
import tests.restApi.reqres.pojoModels.LoginResponsePojoModel;
import tests.restApi.reqres.pojoModels.RegisterResponsePojoModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginUserTests extends TestSettingsReqresTests {
    @Test
    @DisplayName("Успешная авторизация")
    void loginUserSuccessfullyTest() {
        LoginBodyPojoModel authData = new LoginBodyPojoModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");
        RegisterResponsePojoModel response =
                given()
                        .header(API_KEY_NAME, API_KEY)
                        .body(authData)
                        .contentType(JSON)
                        .log().uri()
                        .when()
                        .post(LOGIN_END_POINT)
                        .then()
                        .log().all()
                        .statusCode(HTTP_OK)
                        .extract().as(RegisterResponsePojoModel.class);
        assertEquals("QpwL5tke4Pnpja7X4",response.getToken());
    }

    @Test
    @DisplayName("Ошибка авторизации: пароль отсутствует")
    void registerUserWithMissingPasswordTest() {
        LoginBodyPojoModel authData = new LoginBodyPojoModel();
        authData.setEmail("eve.holt@reqres.in");
        LoginResponsePojoModel response =
                given()
                        .header(API_KEY_NAME, API_KEY)
                        .body(authData)
                        .contentType(JSON)
                        .log().uri()
                        .when()
                        .post(LOGIN_END_POINT)
                        .then()
                        .log().all()
                        .statusCode(HTTP_BAD_REQUEST)
                        .extract().as(LoginResponsePojoModel.class);
        assertEquals("Missing password",response.getError());
    }
    @Test
    @DisplayName("Ошибка авторизации: емайл отсутствует")
    void registerUserWithMissingEmailTest() {
        LoginBodyPojoModel authData = new LoginBodyPojoModel();
        authData.setPassword("pistol");
        LoginResponsePojoModel response =
                given()
                        .header(API_KEY_NAME, API_KEY)
                        .body(authData)
                        .contentType(JSON)
                        .log().uri()
                        .when()
                        .post(LOGIN_END_POINT)
                        .then()
                        .log().all()
                        .statusCode(HTTP_BAD_REQUEST)
                        .extract().as(LoginResponsePojoModel.class);
        assertEquals("Missing email or username",response.getError());

    }
}
