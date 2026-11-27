package br.com.erp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class OpenApiTest {

    @Test
    void openApiDocumentIsPublished() {
        given().queryParam("format", "json")
                .when()
                .get("/q/openapi")
                .then()
                .statusCode(200)
                .body("openapi", notNullValue());
    }
}
