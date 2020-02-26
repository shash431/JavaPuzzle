package services;

import constants.ConfigurationConstant;
import constants.ServicesConstant;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BuildDates {

    public static Response getBuildDates(String manufacturer, String mainType) {

        return given()
                .baseUri(ConfigurationConstant.APP_URL)
                .header("accept-language", ConfigurationConstant.ACCEPT_LANGUAGE)
                .queryParam("wa_key", ConfigurationConstant.APP_KEY)
                .queryParam("manufacturer", manufacturer)
                .queryParam("main-type", mainType)
                .when()
                .get(ConfigurationConstant.END_POINT + ServicesConstant.BUILT_DATES);
    }
}
