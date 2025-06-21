package tests.restApi.reqres.pojoTests;

import org.junit.jupiter.api.*;
import tests.restApi.reqres.TestSettingsReqresTests;
import tests.restApi.reqres.pojoModels.*;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterUserTests extends TestSettingsReqresTests {
    @Test
    @DisplayName("Успешная регистрация")
    void registerUserSuccessfullyTest() {
        RegisterBodyPojoModel regData = new RegisterBodyPojoModel();
        regData.setEmail("eve.holt@reqres.in");
        regData.setPassword("pistol");
        RegisterResponsePojoModel response =
                given()
                        .header(API_KEY_NAME, API_KEY)
                        .body(regData)
                        .contentType(JSON)
                        .log().uri()
                        .when()
                        .post(REGISTER_END_POINT)
                        .then()
                        .log().all()
                        .statusCode(HTTP_OK)
                        .extract().as(RegisterResponsePojoModel.class);
        assertEquals("QpwL5tke4Pnpja7X4",response.getToken());
    }

    @Test
    @DisplayName("Ошибка регистрации: пароль отсутствует")
    void registerUserWithMissingPasswordTest() {
        RegisterBodyPojoModel regData = new RegisterBodyPojoModel();
        regData.setEmail("eve.holt@reqres.in");
        RegisterResponsePojoModel response =
                given()
                .header(API_KEY_NAME, API_KEY)
                .body(regData)
                .contentType(JSON)
                .log().uri()
            .when()
                .post(REGISTER_END_POINT)
            .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                        .extract().as(RegisterResponsePojoModel.class);
        assertEquals("Missing password",response.getError());
    }
    @Test
    @DisplayName("Ошибка регистрации: емайл отсутствует")
    void registerUserWithMissingEmailTest() {
        RegisterBodyPojoModel regData = new RegisterBodyPojoModel();
        regData.setPassword("pistol");
        RegisterResponsePojoModel response =
            given()
                .header(API_KEY_NAME, API_KEY)
                .body(regData)
                .contentType(JSON)
                .log().uri()
            .when()
                .post(REGISTER_END_POINT)
            .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                    .extract().as(RegisterResponsePojoModel.class);
        assertEquals("Missing email or username",response.getError());

    }
}
