package tests.restApi.reqres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.*;

@Tag("ReqresTests")

public class ReqresTests extends TestSettingsReqresTests {

    int validUserId = 9;
    int notValidUserId = 15;

    @Test
    @DisplayName("Получение списка всех юзеров")
    void getUsersListTest(){
        given()
            .header(FREE_API_KEY_NAME,FREE_API_KEY_VALUE)
            .log().uri()
        .when()
            .get(USERS_END_POINT+ "?page=2")
        .then()
            .statusCode(200)
            .body("page", equalTo(2))
            .body("data", hasSize(greaterThan(0)))
            .log().all();
    }

    @Test
    @DisplayName("Поиск существующего юзера")
    void getExistUserTest(){
        given()
            .header(FREE_API_KEY_NAME,FREE_API_KEY_VALUE)
            .log().uri()
        .when()
            .get(USERS_END_POINT + validUserId)
        .then()
            .statusCode(200)
            .log().all();
    }

    @Test
    @DisplayName("Поиск несуществующего юзера")
    void getNonExistUserTest() {
        given()
            .header(FREE_API_KEY_NAME, FREE_API_KEY_VALUE)
            .log().uri()
        .when()
            .get(USERS_END_POINT + notValidUserId)
        .then()
            .statusCode(HTTP_NOT_FOUND)
            .log().all();
    }

    @Test
    @DisplayName("Успешное создание юзера")
    void сreateUserSuccessfullyTest() {
        String userJsonBody = "{\"name\": \"StarKiller\", \"job\": \"StarLord\"}";
        given()
            .header(FREE_API_KEY_NAME, FREE_API_KEY_VALUE)
            .body(userJsonBody)
            .contentType(JSON)
            .log().all()
        .when()
            .post(USERS_END_POINT)
        .then()
            .statusCode(HTTP_CREATED)
            .body("name", is("StarKiller"))
            .body("job", is("StarLord"))
            .log().all();
    }

    @Test
    @DisplayName("Успешное удаление юзера")
    void deleteUserSuccessfullyTest() {
        given()
            .header(FREE_API_KEY_NAME, FREE_API_KEY_VALUE)
            .contentType(JSON)
            .log().all()
        .when()
            .delete(USERS_END_POINT + validUserId)
        .then()
            .log().status()
            .statusCode(HTTP_NO_CONTENT);
    }

    @Test
    @DisplayName("Успешная регистрация")
    void registerUserSuccessfullyTest() {
        String userCredentialJson = "{ \"email\": \"" + TEST_USER_EMAIL + "\", \"password\": \"" + TEST_USER_PASS + "\" }";

        given()
            .header(FREE_API_KEY_NAME, FREE_API_KEY_VALUE)
            .body(userCredentialJson)
            .contentType(JSON)
            .log().uri()
        .when()
            .post(REGISTER_END_POINT)
        .then()
            .statusCode(HTTP_OK)
        .log().all();
    }

    @Test
    @DisplayName("Ошибка регистрации: пароль отсутствует")
    void registerUserWithMissingPasswordTest() {
        String userCredentialJson = "{ \"email\": \"" + TEST_USER_EMAIL + "\"}";

        given()
            .header(FREE_API_KEY_NAME, FREE_API_KEY_VALUE)
            .body(userCredentialJson)
            .contentType(JSON)
            .log().uri()
        .when()
            .post(REGISTER_END_POINT)
        .then()
            .statusCode(HTTP_BAD_REQUEST)
            .body("error", is("Missing password"))
            .log().all();
    }
}