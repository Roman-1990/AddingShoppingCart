package tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.DriverSettings.configureDriver;
import static tests.DataTest.getApiUrl;
import static tests.DataTest.getWebUrl;

public class TestBase {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = getApiUrl();
        Configuration.baseUrl = getWebUrl();
        configureDriver();
    }

    public String getInitialCartCount() {
        open("http://demowebshop.tricentis.com");
        return $("a[href='/cart'] .cart-qty").getText();
    }

}
