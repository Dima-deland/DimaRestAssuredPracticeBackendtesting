package wrappers;

import dto.Experience;
import helpers.CustomAllureListener;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ExperienceApiWrapper {

    public static Experience addProfileExperience(String token, Experience experienceData) {
        return given()
                .filter(CustomAllureListener.withCustomTemplates())
                .header("x-auth-token", token)
                .contentType("application/json")
                .body(experienceData)
                .when()
                .post("profile/experience")
                .then()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("schemas/PostExperience200.json"))
                .extract().jsonPath().getObject("experience[0]", Experience.class);
    }

    public static String addProfileExperienceExpectingBadRequestError(String token, Experience experienceData) {
        return given()
                .filter(CustomAllureListener.withCustomTemplates())
                .header("x-auth-token", token)
                .contentType("application/json")
                .body(experienceData)
                .when()
                .post("profile/experience")
                .then()
                .assertThat()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("schemas/PostExperience400.json"))
                .extract()
                .jsonPath()
                .getString("errors[0].msg");
    }

    public static Experience retrieveExperienceById(String token, int experienceId) {
        return given()
                .filter(CustomAllureListener.withCustomTemplates())
                .header("x-auth-token", token)
                .contentType("application/json")
                .when()
                .get("profile/experience/" + experienceId)
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("schemas/GetExperience200.json"))
                .extract()
                .response().jsonPath().getObject("", Experience.class);
    }

    public static Experience retrieveExperienceByIdExpectingNotFoundError(String token, int experienceId) {
        return given()
                .filter(CustomAllureListener.withCustomTemplates())
                .header("x-auth-token", token)
                .contentType("application/json")
                .when()
                .get("profile/experience/" + experienceId)
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                //body is null here
                .extract().as(Experience.class);
    }

    public static Response putProfileExperience(String token, int experienceId, Experience experienceData) {
        return given()
                .filter(CustomAllureListener.withCustomTemplates())
                .header("x-auth-token", token)
                .contentType("application/json")
                .body(experienceData)
                .when()
                .put("profile/experience/" + experienceId)
                .then()
                .assertThat()
                .statusCode(204)
                //body is null here
                .extract()
                .response();
    }

    public static Response putProfileExperienceExpectingNotFoundError(String token, int experienceId, Experience experienceData) {
        return given()
                .filter(CustomAllureListener.withCustomTemplates())
                .header("x-auth-token", token)
                .contentType("application/json")
                .body(experienceData)
                .when()
                .put("profile/experience/" + experienceId)
                .then()
                .assertThat()
                .statusCode(204)
                .extract()
                .response();
    }

    public static Response patchProfileExperience(String token, int experienceId, Experience experienceData) {
        return given()
                .filter(CustomAllureListener.withCustomTemplates())
                .header("x-auth-token", token)
                .contentType("application/json")
                .body(experienceData)
                .when()
                .patch("profile/experience/" + experienceId)
                .then()
                .assertThat()
                .statusCode(204)
                .extract()
                .response();
    }

    public static Response patchProfileExperienceExpectingBadRequestError(String token, int experienceId, Experience experienceData) {
        return given()
                .filter(CustomAllureListener.withCustomTemplates())
                .header("x-auth-token", token)
                .contentType("application/json")
                .body(experienceData)
                .when()
                .patch("profile/experience/" + experienceId)
                .then()
                .assertThat()
                .statusCode(500)
                .extract()
                .response();
    }

    public static Response deleteProfileExperience(String token, int experienceId) {
        return given()
                .filter(CustomAllureListener.withCustomTemplates())
                .header("x-auth-token", token)
                .contentType("application/json")
                .when()
                .delete("profile/experience/" + experienceId)
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("schemas/DeleteExperience200.json"))
                .extract()
                .response();
    }

    public static Response deleteProfileExperienceExpectingNotFoundError(String token, int experienceId) {
        return given()
                .filter(CustomAllureListener.withCustomTemplates())
                .header("x-auth-token", token)
                .contentType("application/json")
                .when()
                .delete("profile/experience/" + experienceId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }
    public static Response deleteProfileExperienceByOtherUser(String token, int experienceId) {
        return given()
                .filter(CustomAllureListener.withCustomTemplates())
                .header("x-auth-token", token)
                .contentType("application/json")
                .when()
                .delete("profile/experience/" + experienceId)
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .extract()
                .response();
    }
}

