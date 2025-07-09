package wrappers;

import helpers.ConfigReader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserLogin {
    public static String getAuthToken () {
        String str = "{\"email\":"+ConfigReader.get("email")+",\"password\":"+ConfigReader.get("password")+"}";
        System.out.println(str);
        Response response =
                given()
                        .contentType("application/json")
                        .body("{\"email\":\""+ConfigReader.get("email")+"\",\"password\":\""+ConfigReader.get("password")+"\"}")

                        .when()
                        .post("auth")

                        .then()
                        .statusCode(200)
                        .extract().response();

       return response.path("token");
    }
}
