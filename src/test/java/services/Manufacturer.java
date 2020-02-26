package services;

import constants.ConfigurationConstant;
import constants.ServicesConstant;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Manufacturer {

    public static Response getManufacturer() {

        return given()
                .baseUri(ConfigurationConstant.APP_URL)
                .header("accept-language", ConfigurationConstant.ACCEPT_LANGUAGE)
                .queryParam("wa_key", ConfigurationConstant.APP_KEY)
                .when()
                .get(ConfigurationConstant.END_POINT + ServicesConstant.MANUFACTURER);
    }


    public static Response getManufacturerWithoutKey() {

        return given()
                .baseUri(ConfigurationConstant.APP_URL)
                .header("accept-language", ConfigurationConstant.ACCEPT_LANGUAGE)
                .when()
                .get(ConfigurationConstant.END_POINT + ServicesConstant.MANUFACTURER);
    }

    public static Response getManufacturerWithInvalidKey() {

        return given()
                .baseUri(ConfigurationConstant.APP_URL)
                .header("accept-language", ConfigurationConstant.ACCEPT_LANGUAGE)
                .queryParam("wa_key", ConfigurationConstant.INVALID_APP_KEY)
                .when()
                .get(ConfigurationConstant.END_POINT + ServicesConstant.MANUFACTURER);
    }


    public static Response deleteManufacturer(String key, String manufacturerName) {

        return given()
                .baseUri(ConfigurationConstant.APP_URL)
                .header("accept-language", ConfigurationConstant.ACCEPT_LANGUAGE)
                .queryParam("wa_key", ConfigurationConstant.APP_KEY)
                .body("\"wkda\" : {\n    \"" + key + "\" : \"" + manufacturerName + "\"\n    }")
                .when()
                .delete(ConfigurationConstant.END_POINT + ServicesConstant.MANUFACTURER);
    }


    public static Response postRequestManufacturer() {

        return given()
                .baseUri(ConfigurationConstant.APP_URL)
                .header("accept-language", ConfigurationConstant.ACCEPT_LANGUAGE)
                .queryParam("wa_key", ConfigurationConstant.APP_KEY)
                .body("'wkda' : {" +
                        "'Hemant' : 'Hemant'" +
                        "}")
                .when()
                .post(ConfigurationConstant.END_POINT + ServicesConstant.MANUFACTURER);
    }

}
