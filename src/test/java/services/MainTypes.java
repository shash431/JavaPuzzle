package services;

import constants.ConfigurationConstant;
import constants.ServicesConstant;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class MainTypes {

    public static Response getMainTypes(String manufacturer) {

        return given()
                .baseUri(ConfigurationConstant.APP_URL)
                .header("accept-language", ConfigurationConstant.ACCEPT_LANGUAGE)
                .queryParam("wa_key", ConfigurationConstant.APP_KEY)
                .queryParam("manufacturer", manufacturer)
                .when()
                .get(ConfigurationConstant.END_POINT + ServicesConstant.MAIN_TYPES);
    }

    public static Response getMainTypesBadRequestWithoutManufacturer() {
        return given()

                .baseUri(ConfigurationConstant.APP_URL)
                .header("accept-language", ConfigurationConstant.ACCEPT_LANGUAGE)
                .queryParam("wa_key", ConfigurationConstant.APP_KEY)
                .when()
                .get(ConfigurationConstant.END_POINT + ServicesConstant.MAIN_TYPES);
    }
}
