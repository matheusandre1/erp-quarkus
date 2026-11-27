package br.com.erp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class HealthCheckTest {

    @Test
    void liveness() {
        given().when().get("/q/health/live").then().statusCode(200).body("status", is("UP"));
    }

    @Test
    void readinessIncludesDatabase() {
        given().when()
                .get("/q/health/ready")
                .then()
                .statusCode(200)
                .body("status", is("UP"))
                .body("checks.find { it.name == 'Database connections health check' }.status", is("UP"));
    }
}
