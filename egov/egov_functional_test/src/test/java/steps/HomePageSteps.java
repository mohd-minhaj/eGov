package steps;

import cucumber.api.java8.En;
import entities.LoginDetails;
import pages.BasePage;
import pages.HomePage;
import excelDataFiles.ExcelReader;

public class HomePageSteps extends BaseSteps implements En {
    public HomePageSteps() {
        Given("^(.*) logs in$", (String currentUser) -> {
            LoginDetails loginDetails = new ExcelReader(loginTestDataFileName).getLoginDetails(currentUser);
//            if (System.getProperty("env").equalsIgnoreCase("qa"))
//                loginDetails.setPassword("eGov@123");
            pageStore.get(HomePage.class).loginAs(loginDetails);
        });

        And("^the next user will be logged in$", () -> {
            LoginDetails loginDetails = new ExcelReader(loginTestDataFileName).getLoginDetails(scenarioContext.getUser());
//            if (System.getProperty("env").equalsIgnoreCase("qa"))
//                loginDetails.setPassword("eGov@123");
            pageStore.get(HomePage.class).loginAs(loginDetails);
        });

        And("^user will be notified by \"([^\"]*)\"$", (String expectedMessage) -> {
            String actualMessage = scenarioContext.getActualMessage();
            pageStore.get(BasePage.class).isSuccesful(expectedMessage,actualMessage);
        });

        Given("^user log on to the website$", () -> {
            pageStore.get(HomePage.class).visitWebsite();
        });
    }
}