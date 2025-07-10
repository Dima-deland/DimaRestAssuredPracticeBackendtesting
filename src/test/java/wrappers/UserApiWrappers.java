package wrappers;

import helpers.ConfigReader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiWrappers {
    public static String getAuthToken(int userRole) {
        String email = "email" + userRole;
        String password = "password" + userRole;
        Response response =
                given()
                        .contentType("application/json")
                        .body("{\"email\":\"" + ConfigReader.get(email) + "\",\"password\":\"" + ConfigReader.get(password) + "\"}")

                        .when()
                        .post("auth")

                        .then()
                        .statusCode(200)
                        .extract().response();

        return response.path("token");
    }
}
