package io.qameta.junit5;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class ApiRemoteTest {

    String id;

   ApiRemoteTest apiRemoteTest;

    public String getId() {
        return id;
    }

    @BeforeEach
    public void ApiRemoteTest (){
        apiRemoteTest = new ApiRemoteTest();
    }

    @BeforeAll
    public static void setUpAll(){

        Configuration.remote = "http://35.238.172.233:4444/wd/hub";
        Configuration.browser = "chrome"; // System.getProperty("browserName");
        Configuration.browserSize = "1920x1080";
        Configuration.browserVersion = "114.0";

        Configuration.browserCapabilities.setCapability("selenoid:options",
                Map.<String, Object>of(
                        "enableVNC", true,
                        "enableVideo", true
                ));
    }

//    @BeforeEach
//    public void setUp(){
//        System.out.println("Trying to open browsers");
//        Selenide.open( "http://51.250.6.164:8080/test-orders/5");
//        System.out.println("browser Opened OK");
//    }

    @AfterEach
    public void tearDown(){
        closeWebDriver();
    }

    // Positive test for searching order with id=5
    @Test
    public void setUpId5() {
        System.out.println("Trying to open browsers");
        Selenide.open("http://51.250.6.164:8080/test-orders/5" );
        System.out.println("browser Opened OK");
        apiRemoteTest.simplePositiveTestResponseBodyStatusOpenGetId5();
    }

        public void simplePositiveTestResponseBodyStatusOpenGetId5 () {
            id = "5";
            id = given()
                    .when()
                    .log()
                    .all()
                    .get("http://51.250.6.164:8080/test-orders/5")
                    .then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_OK)
                    .and()
                    .extract()
                    .path(id);

            Assertions.assertEquals(id, getId());

            Selenide.sleep(7500);
        }


    // Positive test for searching order with id=10
    @Test
    public void setUpId10() {
        System.out.println("Trying to open browsers");
        Selenide.open("http://51.250.6.164:8080/test-orders/10" );
        System.out.println("browser Opened OK");
        apiRemoteTest.simplePositiveTestResponseBodyStatusOpenGetId10();
    }

    public void simplePositiveTestResponseBodyStatusOpenGetId10() {

        id = "10";
        String status = given()
                .when()
                .log()
                .all()
                .get("http://51.250.6.164:8080/test-orders/" + id)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .path(id);

        Assertions.assertEquals(id, getId());

        Selenide.sleep(7500);
    }

    // Negative test for searching order with id=10
    @Test
    public void setUpId11() {
        System.out.println("Trying to open browsers");
        Selenide.open("http://51.250.6.164:8080/test-orders/11" );
        System.out.println("browser Opened OK");
        apiRemoteTest.simpleNegativeTestResponseBodyStatusOpenGetId11();
    }

    public void simpleNegativeTestResponseBodyStatusOpenGetId11() {

        id = "11";
        String status = given()
                .when()
                .log()
                .all()
                .get("http://51.250.6.164:8080/test-orders/" + id)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .extract()
                .path(id);

        Assertions.assertEquals(id, getId());

        Selenide.sleep(7500);
    }
}

