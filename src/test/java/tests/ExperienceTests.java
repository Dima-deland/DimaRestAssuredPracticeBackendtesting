package tests;

import dto.Experience;
import helpers.ExperienceData;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import wrappers.ExperienceApiWrapper;
import wrappers.UserLogin;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExperienceTests extends BaseTest {

    static String token = "";
    static Experience addedExperience;
    static int experienceId;
    static int notExistedExperienceId = 99999;


    @BeforeAll
    public static void beforeAll() {
       token = UserLogin.getAuthToken();
    }

    @Test
    @Order(1)
    void addProfileExperience() {
        Experience experienceToAdd = ExperienceData.createExpirienceData();
        addedExperience = ExperienceApiWrapper.addProfileExperience(token, experienceToAdd);
        Assertions.assertEquals(experienceToAdd,addedExperience);
        experienceId = addedExperience.getId();
    }

    @Test
    @Order(2)
    void addProfileExperienceExpectingBadRequestError(){
        Experience experienceToAdd = ExperienceData.createExperienceDataWithEmptyTitleField();
        String addedExperienceWithEmptyFrom = ExperienceApiWrapper.addProfileExperienceExpectingBadRequestError(token, experienceToAdd);
        Assertions.assertEquals("Title is required",addedExperienceWithEmptyFrom);
    }

    @Test
    @Order(3)
    void retrieveExperienceById() {
        Experience retrievedExperience = ExperienceApiWrapper.retrieveExperienceById(token, experienceId);
        Assertions.assertEquals(addedExperience,retrievedExperience);
        System.out.println(experienceId);
    }

    @Test
    @Order(4)
    void retrieveExperienceByIdExpectingNotFoundError() {
        Experience retrievedExperience = ExperienceApiWrapper.retrieveExperienceByIdExpectingNotFoundError(token, notExistedExperienceId);
        Assertions.assertNull(retrievedExperience);
    }

    @Test
    @Order(5)
    void putProfileExperience() {
        Experience experienceToUpdate = ExperienceData.putExperienceData();
        Response response = ExperienceApiWrapper.putProfileExperience(token,experienceId,experienceToUpdate);
        Assertions.assertEquals(204,response.statusCode());
        Experience retrievedExperience = ExperienceApiWrapper.retrieveExperienceById(token, experienceId);
        Assertions.assertEquals(experienceToUpdate,retrievedExperience);
    }

    @Test
    @Order(6)
    void putProfileExperienceExpectingNotFoundError() {
        Experience experienceToAdd = ExperienceData.putExperienceData();
        Response response = ExperienceApiWrapper.putProfileExperienceExpectingNotFoundError(token, notExistedExperienceId, experienceToAdd);
        Assertions.assertEquals(204,response.statusCode());
    }

    @Test
    @Order(7)
    void patchProfileExperience() {
        System.out.println(experienceId);
        Experience experienceToPatch = ExperienceData.patchExperienceData();
        Response response = ExperienceApiWrapper.patchProfileExperience(token,experienceId,experienceToPatch);
        Assertions.assertEquals(204,response.statusCode());
        Experience retrievedExperience = ExperienceApiWrapper.retrieveExperienceById(token, experienceId);
        Assertions.assertEquals(experienceToPatch,retrievedExperience);
    }

    @Test
    @Order(8)
    void patchProfileExperienceExpectingBadRequestError() {
        System.out.println(experienceId);
        Experience experienceToAdd = ExperienceData.patchExperienceDataWithEmptyTitleField();
        Response response = ExperienceApiWrapper.patchProfileExperienceExpectingBadRequestError(token,experienceId, experienceToAdd);
        Assertions.assertEquals(500,response.statusCode());
    }

    @Test
    @Order(9)
    void deleteProfileExperience() {
        Response response = ExperienceApiWrapper.deleteProfileExperience(token,experienceId);
        Assertions.assertEquals(200,response.statusCode());
        Experience retrievedExperience = ExperienceApiWrapper.retrieveExperienceByIdExpectingNotFoundError(token, experienceId);
        Assertions.assertNull(retrievedExperience);
    }

    @Test
    @Order(10)
    void deleteProfileExperienceExpectingNotFoundError() {
        Response response = ExperienceApiWrapper.deleteProfileExperienceExpectingNotFoundError(token, notExistedExperienceId);
        Assertions.assertEquals(200,response.statusCode());
    }
}
