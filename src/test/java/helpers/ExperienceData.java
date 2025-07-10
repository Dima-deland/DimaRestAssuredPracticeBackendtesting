package helpers;

import dto.Experience;

public class ExperienceData {

    public static Experience createExpirienceData() {
        Experience experience = new Experience();
        experience.setCompany("some test company");
        experience.setTitle("some test job title");
        experience.setLocation("some location");
        experience.setFrom("2025-07-06");
        experience.setTo("2025-07-09");
        experience.setCurrent(false);
        experience.setDescription("some job description");

        return experience;
    }

    public static Experience createExperienceDataWithEmptyTitleField() {
        Experience experience = ExperienceData.createExpirienceData();
        experience.setTitle("");
        return experience;
    }

    public static Experience createExperienceDataWhenFromIsBiggerThanTo() {
        Experience experience = ExperienceData.createExpirienceData();
        experience.setFrom("2025-07-09");
        experience.setTo("2025-07-06");
        return experience;
    }

    public static Experience putExperienceData() {
        Experience experience = ExperienceData.createExpirienceData();
        experience.setTitle("put some test job title");
        return experience;
    }

    public static Experience patchExperienceData() {
        Experience experience = ExperienceData.createExpirienceData();
        experience.setTitle("patch some test job title");
        return experience;
    }

    public static Experience patchExperienceDataWithEmptyTitleField() {
        Experience experience = new Experience();
        experience.setFrom("");
        return experience;
    }
}
