package tests.restApi.reqres;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestSettingsReqresTests {
    protected static final String API_KEY_NAME = "x-api-key";
    protected static final String API_KEY = "reqres-free-v1";
    protected static final String USERS_END_POINT = "/users/";
    protected static final String REGISTER_END_POINT = "/register";
    protected static final String LOGIN_END_POINT = "/login";



    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }


}