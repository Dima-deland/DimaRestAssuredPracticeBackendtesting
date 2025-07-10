package tests;

import dto.Experience;
import helpers.ExperienceData;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import wrappers.ExperienceApiWrapper;
import wrappers.UserApiWrappers;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExperienceTests extends BaseTest {

    static String token = "";
    static Experience addedExperience;
    static int experienceId;
    static int notExistedExperienceId = 99999;
    static Experience experienceToAdd;

    @BeforeAll
    public static void beforeAll() {
        token = UserApiWrappers.getAuthToken(0);
    }

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        if (testInfo.getTags().contains("SkipBeforeAfterEach")) {
            return;
        }
        experienceToAdd = ExperienceData.createExpirienceData();
        addedExperience = ExperienceApiWrapper.addProfileExperience(token, experienceToAdd);
        experienceId = addedExperience.getId();
    }

    @AfterEach
    public void afterEach(TestInfo testInfo) {
        if (testInfo.getTags().contains("SkipBeforeAfterEach")) {
            return;
        }
        ExperienceApiWrapper.deleteProfileExperience(token, experienceId);
    }

    @Test
    @DisplayName("Check adding experience with valid fields and receiving Successful response")
    @Owner("Dima")
    void addProfileExperience() {
        Assertions.assertEquals(experienceToAdd, addedExperience);
    }

    @Test
    @Tag("SkipBeforeAfterEach")
    @DisplayName("Check adding experience with empty Title field and receiving Error message")
    @Owner("Dima")
    void addProfileExperienceExpectingBadRequestError() {
        Experience experienceToAdd = ExperienceData.createExperienceDataWithEmptyTitleField();
        String responseWithError = ExperienceApiWrapper.addProfileExperienceExpectingBadRequestError(token, experienceToAdd);
        Assertions.assertEquals("Title is required", responseWithError);
    }

    @Test
    @Tag("SkipBeforeAfterEach")
    @DisplayName("Check adding experience when From date is Later than To date and receiving Error message")
    @Owner("Dima")
    void addProfileExperienceWhenFromIsBiggerThanToExpectingBadRequestError() {
        Experience experienceToAdd = ExperienceData.createExperienceDataWhenFromIsBiggerThanTo();
        String responseWithError = ExperienceApiWrapper.addProfileExperienceExpectingBadRequestError(token, experienceToAdd);
        Assertions.assertEquals("From date should be earlier than To date", responseWithError);
    }

    @Test
    @DisplayName("Check receiving experience")
    @Owner("Dima")
    void retrieveExperienceById() {
        Experience retrievedExperience = ExperienceApiWrapper.retrieveExperienceById(token, experienceId);
        Assertions.assertEquals(addedExperience, retrievedExperience);
        System.out.println(experienceId);
    }

    @Test
    @Tag("SkipBeforeAfterEach")
    @DisplayName("Check receiving experience by not existing Id and receiving Error message")
    @Owner("Dima")
    void retrieveExperienceByIdExpectingNotFoundError() {
        Experience retrievedExperience = ExperienceApiWrapper.retrieveExperienceByIdExpectingNotFoundError(token, notExistedExperienceId);
        Assertions.assertNull(retrievedExperience);
    }

    @Test
    @DisplayName("Check updating experience")
    @Owner("Dima")
    void putProfileExperience() {
        Experience experienceToUpdate = ExperienceData.putExperienceData();
        Response response = ExperienceApiWrapper.putProfileExperience(token, experienceId, experienceToUpdate);
        Assertions.assertEquals(204, response.statusCode());
        Experience retrievedExperience = ExperienceApiWrapper.retrieveExperienceById(token, experienceId);
        Assertions.assertEquals(experienceToUpdate, retrievedExperience);
    }

    @Test
    @Tag("SkipBeforeAfterEach")
    @DisplayName("Check updating experience by not existing Id and receiving Error message")
    @Owner("Dima")
    void putProfileExperienceExpectingNotFoundError() {
        Experience experienceToAdd = ExperienceData.putExperienceData();
        Response response = ExperienceApiWrapper.putProfileExperienceExpectingNotFoundError(token, notExistedExperienceId, experienceToAdd);
        Assertions.assertEquals(204, response.statusCode());
    }

    @Test
    @DisplayName("Check partly updating experience")
    @Owner("Dima")
    void patchProfileExperience() {
        Experience experienceToPatch = ExperienceData.patchExperienceData();
        Response response = ExperienceApiWrapper.patchProfileExperience(token, experienceId, experienceToPatch);
        Assertions.assertEquals(204, response.statusCode());
        Experience retrievedExperience = ExperienceApiWrapper.retrieveExperienceById(token, experienceId);
        Assertions.assertEquals(experienceToPatch, retrievedExperience);
    }

    @Test
    @DisplayName("Check partly updating experience with empty Title field and receiving Error message")
    @Owner("Dima")
    void patchProfileExperienceExpectingBadRequestError() {
        Experience experienceToAdd = ExperienceData.patchExperienceDataWithEmptyTitleField();
        Response response = ExperienceApiWrapper.patchProfileExperienceExpectingBadRequestError(token, experienceId, experienceToAdd);
        Assertions.assertEquals(500, response.statusCode());
    }

    @Test
    @DisplayName("Check deleting experience")
    @Owner("Dima")
    void deleteProfileExperience() {
        Response response = ExperienceApiWrapper.deleteProfileExperience(token, experienceId);
        Assertions.assertEquals(200, response.statusCode());
        Experience retrievedExperience = ExperienceApiWrapper.retrieveExperienceByIdExpectingNotFoundError(token, experienceId);
        Assertions.assertNull(retrievedExperience);
    }

    @Test
    @Tag("SkipBeforeAfterEach")
    @DisplayName("Check deleting experience by not existing Id and receiving Error message")
    @Owner("Dima")
    void deleteProfileExperienceExpectingNotFoundError() {
        Response response = ExperienceApiWrapper.deleteProfileExperienceExpectingNotFoundError(token, notExistedExperienceId);
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @DisplayName("Check deleting experience by user w/o permissions and receiving Error message")
    @Owner("Dima")
    void deleteProfileExperienceByOtherUser() {
        int experienceIdByOtherUser = addedExperience.getId();
        token = UserApiWrappers.getAuthToken(1);

        Response response = ExperienceApiWrapper.deleteProfileExperienceByOtherUser(token, experienceIdByOtherUser);
        Assertions.assertEquals(403, response.statusCode(), "User should receive 403 error code for unauthorized requests");
    }
}