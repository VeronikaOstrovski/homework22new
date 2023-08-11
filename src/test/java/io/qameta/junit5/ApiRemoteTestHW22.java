package io.qameta.junit5;

import com.google.gson.Gson;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

// Homework22
public class ApiRemoteTestHW22 {

    String id;

    ApiRemoteTestHW22 apiRemoteTest;

    public String getId() {
        return id;
    }

    @BeforeEach
    public void ApiRemoteTest() {
        apiRemoteTest = new ApiRemoteTestHW22();
    }


    // Positive test for searching order with id=5
    @Test
    public void simplePositiveTestResponseBodyGetId() {
        id = "5";
        id = given()
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
    }

    // Negative test for searching order with id=11
    @Test
    public void simpleNegativeTestResponseBodyGetId() {

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
    }

    // Create an order (API)
    @Test
    public void dataForOrderFields() {
        CreateOrderHW22 createOrder = new CreateOrderHW22("OPEN", 0, "Tester", "+37255599977", "Today", 0);
        createOrder.setStatus("OPEN");
        createOrder.setCourierId(0);
        createOrder.setCustomerName("Tester");
        createOrder.setCustomerPhone("+37255599977");
        createOrder.setComment("Today");
        createOrder.setId(0);

        Gson gson = new Gson();

        given()
                .when()
                .header("Content-Type", "application/json")
                .log()
                .all()
                .body(gson.toJson(createOrder))
                .post("http://51.250.6.164:8080/test-orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);
    }
}

