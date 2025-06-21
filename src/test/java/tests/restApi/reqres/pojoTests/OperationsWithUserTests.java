package tests.restApi.reqres.pojoTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.restApi.reqres.TestSettingsReqresTests;
import tests.restApi.reqres.pojoModels.LoginResponsePojoModel;
import tests.restApi.reqres.pojoModels.OperationsWithUsersPojoModel;
import tests.restApi.reqres.pojoModels.OperationsWithUsersResponsePojoModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OperationsWithUserTests extends TestSettingsReqresTests {
    int validUserId = 9;
    int notValidUserId = 15;

    @Test
    @DisplayName("Получение списка всех юзеров")
    void getUsersListTest(){
        given()
                .header(API_KEY_NAME, API_KEY)
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
                .header(API_KEY_NAME, API_KEY)
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
                .header(API_KEY_NAME, API_KEY)
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
        OperationsWithUsersPojoModel userJsonBody = new OperationsWithUsersPojoModel();
        userJsonBody.setName("StarKiller");
        userJsonBody.setJob("StarLord");
        OperationsWithUsersResponsePojoModel response = given()
                .header(API_KEY_NAME, API_KEY)
                .body(userJsonBody)
                .contentType(JSON)
                .log().all()
                .when()
                .post(USERS_END_POINT)
                .then()
                .statusCode(HTTP_CREATED)
                .log().all()
                .extract().as(OperationsWithUsersResponsePojoModel.class);
            assertEquals("StarLord",response.getJob());
            assertEquals("StarKiller",response.getName());
            assertNotNull(response.getCreatedAt());
    }

    @Test
    @DisplayName("Успешное удаление юзера")
    void deleteUserSuccessfullyTest() {
        given()
                .header(API_KEY_NAME, API_KEY)
                .contentType(JSON)
                .log().all()
                .when()
                .delete(USERS_END_POINT + validUserId)
                .then()
                .log().status()
                .statusCode(HTTP_NO_CONTENT);
    }

}
