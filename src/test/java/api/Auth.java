package api;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class Auth {
    public Map<String, String> getAuthorizedCookies(String testUsername, String testPassword) {
        return
                given()

                        .contentType("application/json; charset=utf-8")
                        .formParam("Email", testUsername)
                        .formParam("Password", testPassword)
                        .when()
                        .post("http://demowebshop.tricentis.com/login")
                        .then()
                        .statusCode(302)
                        .extract().cookies();
    }

}
