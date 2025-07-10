package tests;

import helpers.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    static String url = "http://92.205.106.232/";


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigReader.get("URL") + "api/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

}