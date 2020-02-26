package stepdefs;

import constants.CarConstant;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;
import org.testng.Assert;
import services.BuildDates;
import services.MainTypes;
import services.Manufacturer;
import utils.Utils;

import java.util.Map;

public class CarStepDefinitions {

    private Response response;
    Logger logger=Logger.getLogger("CarStepDefinitions");

    @Given("^feature is available to the user$")
    public void featureIsAvailableToUser() {
        logger.info("Checking whether the CAR feature is available");
        Assert.assertEquals(Manufacturer.getManufacturer().getStatusCode(), 200,"Status is not as expected");
        logger.info("The CAR feature is available and the status is 200");
    }

    @When("^user selects manufacturer \"([^\"]*)\"$" )
    public void userSelectsManufacturer(String manufacturerName) {

        response = Manufacturer.getManufacturer();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getMap("wkda").isEmpty());
        JsonPath manufacturerResponse = response.jsonPath();

        CarConstant.MANUFACTURER = Utils.getKey(manufacturerResponse.getMap("wkda"), manufacturerName);
        logger.info("User selects the manufacture "+manufacturerName);
    }

    @And("^selects main-type \"([^\"]*)\"$")
    public void selectsMainType(String mainType) {

        response = MainTypes.getMainTypes(CarConstant.MANUFACTURER);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getMap("wkda").isEmpty());
        JsonPath mainTypeResponse = response.jsonPath();

        CarConstant.MAIN_TYPES = (String) mainTypeResponse.getMap("wkda").get(mainType);
        logger.info("User selects the main-type "+mainType);
    }

    @And("^user retrieve built-dates by selected main-type$")
    public void userRetrieveBuiltDatesBySelectedMainType() {

        response = BuildDates.getBuildDates(CarConstant.MANUFACTURER, CarConstant.MAIN_TYPES);

        Assert.assertFalse(response.jsonPath().getMap("wkda").isEmpty());

        logger.info("User retrieve built-dates by selected main-type");

    }

    @Then("^the status code should be (\\d+)$")
    public void verifyStatusCode(int statusCode) {

        Assert.assertEquals(statusCode, response.getStatusCode());

        logger.info("The status code is as expected i.e"+statusCode);
    }

    @And("^response includes the following dates$")
    public void responseIncludesData(Map<String, String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if (StringUtils.isAlphanumeric(field.getValue())) {
                Assert.assertTrue(response.jsonPath().getMap("wkda").containsKey(field.getKey()));
                Assert.assertTrue(response.jsonPath().getMap("wkda").containsValue(field.getValue()));
            }

            logger.info("The built dates are as expected in the response i.e"+field.getValue());
        }
        }

    @When("^user try to create new record for manufacturer$")
    public void userTryToCreateNewRecordForManufacturer() {
        response = Manufacturer.postRequestManufacturer();
        logger.info("user trying to create new record for manufacturer");
    }

    @When("^user try to delete \"([^\"]*)\" manufacturer$")
    public void userTryToDeleteManufacturer(String manufacturerName) {
        Response manufacturer = Manufacturer.getManufacturer();

        Assert.assertEquals(manufacturer.getStatusCode(), 200);
        logger.info("user trying to delete manufacturer"+manufacturerName);
        response = Manufacturer.deleteManufacturer(Utils.getKey(manufacturer.jsonPath().getMap("wkda"),
                manufacturerName), manufacturerName);
        logger.info("The manufacturer got deleted");
    }

    @When("^user selects \"([^\"]*)\" manufacturer with invalid wa_key$")
    public void userSelectsManufacturerWithInvalidWa_key(String manufacturer) {
        response = Manufacturer.getManufacturerWithInvalidKey();
        logger.info("User is able to Selects Manufacturer With Invalid Wa_key ");
    }

    @When("^user selects \"([^\"]*)\" manufacturer without wa_key$")
    public void userSelectsManufacturerWithoutWa_key(String manufacturer) {
        response = Manufacturer.getManufacturerWithoutKey();
        logger.info("User is able to Selects Manufacturer Without Wa_key ");
    }

    @And("^user sends bad request to fetch main-types$")
    public void userSendsBadRequestToFetchMainTypes() {
        response = MainTypes.getMainTypesBadRequestWithoutManufacturer();
        logger.info("User sent bad request to fetch main-types ");
    }

}


