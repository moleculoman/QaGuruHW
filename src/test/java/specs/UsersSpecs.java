package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import tests.restApi.reqres.TestSettingsReqresTests;


import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;
public class UsersSpecs extends TestSettingsReqresTests {

    public static RequestSpecification loginUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .header(API_KEY_NAME, API_KEY)
            .contentType(JSON);

    public static RequestSpecification operationsWithUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .header(API_KEY_NAME, API_KEY)
            .contentType(JSON);

    public static ResponseSpecification loginUserResponseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();
    public static ResponseSpecification loginUserResponseSpec400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(ALL)
            .build();
    public static ResponseSpecification loginUserResponseSpec404 = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(ALL)
            .build();
    public static ResponseSpecification operationsWithUserResponseSpec404 = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(ALL)
            .build();
    public static ResponseSpecification operationsWithUserResponseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static ResponseSpecification registerUserResponseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
    public static ResponseSpecification registerUserResponseSpec400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification registerUserResponseSpec201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification operationsWithUserResponseSpec204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(STATUS)
            .build();
    public static ResponseSpecification operationsWithUserResponseSpec201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .build();

}
