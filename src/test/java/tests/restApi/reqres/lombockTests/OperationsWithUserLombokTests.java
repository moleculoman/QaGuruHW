package tests.restApi.reqres.lombockTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.restApi.reqres.TestSettingsReqresTests;
import tests.restApi.reqres.lombokModels.CrudOperationsWithUserResponseModel;
import tests.restApi.reqres.lombokModels.OperationsWithManyUsersResponseModel;
import tests.restApi.reqres.lombokModels.OperationsWithSingleUserResponseModel;
import tests.restApi.reqres.lombokModels.OperationsWithUsersModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.net.HttpURLConnection.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static tests.restApi.specs.UsersSpecs.*;


@Tag("ReqresTests")
public class OperationsWithUserLombokTests extends TestSettingsReqresTests {
    int validUserId = 9;
    int notValidUserId = 15;

    @Test
    @DisplayName("Получение списка всех юзеров")
    void getUsersListTest(){
        OperationsWithManyUsersResponseModel response = step("Make request", () ->
            given(userRequestSpec)
            .when()
                .get(USERS_END_POINT+ "?page=2")
            .then()
                .spec(responseSpec200)
            .extract().as(OperationsWithManyUsersResponseModel.class));
        step("Check response", () ->{
        assertThat(response.getPage()).isEqualTo("2");
        assertNotNull(response.getData());
    });
    }

    @Test
    @DisplayName("Поиск существующего юзера")
    void getExistUserTest(){

        OperationsWithSingleUserResponseModel response= step("Make request", () ->
            given(userRequestSpec)
            .when()
                .get(USERS_END_POINT + validUserId)
            .then()
                    .spec(responseSpec200)
            .extract().as(OperationsWithSingleUserResponseModel.class));
        step("Check response", () -> {
            assertThat(response.getData().getId()).isEqualTo("9");
            assertNotNull(response.getData());
        });
    }

    @Test
    @DisplayName("Поиск несуществующего юзера")
    void getNonExistUserTest() {
        OperationsWithSingleUserResponseModel response= step("Make request", () ->
            given(userRequestSpec)
            .when()
                .get(USERS_END_POINT + notValidUserId)
            .then()
                    .spec(responseSpec404)
            .extract().as(OperationsWithSingleUserResponseModel.class));
        step("Check response", () ->
        assertNull(response.getData()));
    }

    @Test
    @DisplayName("Успешное создание юзера")
    void сreateUserSuccessfullyTest() {
        OperationsWithUsersModel requestData = new OperationsWithUsersModel();
        requestData.setName("StarKiller");
        requestData.setJob("StarLord");
        CrudOperationsWithUserResponseModel response= step("Make request", () ->
            given(userRequestSpec)
                        .body(requestData)
            .when()
                .post(USERS_END_POINT)
            .then()
                .spec(responseSpec201)
            .extract().as(CrudOperationsWithUserResponseModel.class));
        step("Check response", () -> {
            assertEquals("StarKiller", response.getName());
            assertEquals("StarLord", response.getJob());
            assertNotNull(response.getCreatedAt());
            assertNotNull(response.getId());
        });
    }

    @Test
    @DisplayName("Успешное удаление юзера")
    void deleteUserSuccessfullyTest() {
        step("Delete user", () ->
            given(userRequestSpec)
            .when()
                .delete(USERS_END_POINT + validUserId)
            .then()
                .spec(responseSpec204));
    }

    @Test
    @DisplayName("Частичное обновление юзера - patch метод")
    void patchingUsersJobTest() {
        OperationsWithUsersModel requestData = new OperationsWithUsersModel();
        requestData.setJob("URANUS LEADER");
        CrudOperationsWithUserResponseModel response= step("Make request", () ->
            given()
                .header(API_KEY_NAME, API_KEY)
                .body(requestData)
                .contentType(JSON)
                .log().uri()
            .when()
                .patch(USERS_END_POINT + validUserId)
            .then()
                .statusCode(HTTP_OK)
                .log().all()
                .extract().as(CrudOperationsWithUserResponseModel.class));
        step("Check response", () -> {
            assertEquals("URANUS LEADER", response.getJob());
            assertNotNull(response.getUpdatedAt());
            assertNull(response.getName());
            assertNull(response.getId());
            assertNull(response.getCreatedAt());
        });
    }

    @Test
    @DisplayName("Полное обновление юзера - put метод")
    void updatingUsersInfoTest() {
        OperationsWithUsersModel requestData = new OperationsWithUsersModel();
        requestData.setName("StarKiller2");
        requestData.setJob("StarEmperor");
        CrudOperationsWithUserResponseModel response = step("Make request", () ->
                given(userRequestSpec)
                        .body(requestData)
                .when()
                        .put(USERS_END_POINT + validUserId)
                .then()
                        .spec(responseSpec200)
                        .extract().as(CrudOperationsWithUserResponseModel.class));
                step("Check response", () -> {
            assertEquals("StarEmperor", response.getJob());
            assertEquals("StarKiller2", response.getName());
            assertNotNull(response.getUpdatedAt());
            assertNull(response.getId());
            assertNull(response.getCreatedAt());
        });
    }
}