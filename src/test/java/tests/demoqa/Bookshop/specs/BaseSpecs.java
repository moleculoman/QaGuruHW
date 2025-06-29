package tests.demoqa.Bookshop.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.*;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.*;

public class BaseSpecs {
    public static RequestSpecification authRequestSpec(String TOKEN) {
        return with()
                .filter(withCustomTemplates())
                .log().all()
                .header("Authorization", "Bearer " + TOKEN)
                .log().all()
                .contentType(JSON);
    }
    public static ResponseSpecification responseSpec(int expectedStatusCode) {
        return new ResponseSpecBuilder()
                .log(STATUS)
                .log(BODY)
                .expectStatusCode(expectedStatusCode)
                .build();
    }
}