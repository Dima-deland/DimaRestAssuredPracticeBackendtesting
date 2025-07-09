package tests;

import helpers.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class BaseTest {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = ConfigReader.get("URL")+"api/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

}
