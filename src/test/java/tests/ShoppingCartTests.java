package tests;

import api.Auth;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static tests.DataTest.getTestPassword;
import static tests.DataTest.getTestUsername;


public class ShoppingCartTests {

    @Test
    void addShoppingCartTest() throws ParseException {
        // Создаем cookie
        Map<String, String> cookies = new Auth().getAuthorizedCookies(getTestUsername(), getTestPassword());
        //Создаем сессию в браузере для вставки cookie
        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        //Вставляем cookie в сессию
        getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookies.get("Nop.customer")));


        //Проверка
        open("http://demowebshop.tricentis.com/login");

        $("#Email").shouldHave(text(getTestUsername()));

        String initialCartValue = new TestBase().getInitialCartCount();
        int initialCartCount = Integer.parseInt(initialCartValue.substring(1, initialCartValue.length() - 1));

        String response =
                given()
                        .contentType("application/json; charset=utf-8")
                        .cookies(cookies)
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/catalog/31/1/1")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                        .extract().response().asString();

        // Проверка увеличения товаров в корзине
        JSONParser parser = new JSONParser();
        JSONObject JSONResponse = (JSONObject) parser.parse(response);
        String cartCount = (String) JSONResponse.get("updatetopcartsectionhtml");

        open("http://demowebshop.tricentis.com");
        $("a[href='/cart'] .cart-qty").shouldHave(text((cartCount)));

    }

}
