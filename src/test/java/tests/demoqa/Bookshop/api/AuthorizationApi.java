package tests.demoqa.Bookshop.api;


import tests.demoqa.Bookshop.models.LoginRequestModel;
import tests.demoqa.Bookshop.models.LoginResponseModel;
import tests.demoqa.Bookshop.specs.BaseSpecs;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static tests.demoqa.Bookshop.tests.TestBase.LOGIN_END_POINT;
import static tests.demoqa.Bookshop.tests.TestData.*;


public class AuthorizationApi extends BaseSpecs {
    public static LoginResponseModel login() {
        LoginRequestModel authData = new LoginRequestModel();
        authData.setUserName(LOGIN);
        authData.setPassword(PASSWORD);

        return
            given()
                .body(authData)
                .contentType(JSON)
            .when()
                .post(LOGIN_END_POINT)
            .then()
                .spec(responseSpec(200))
                .extract().as(LoginResponseModel.class);
    }
}