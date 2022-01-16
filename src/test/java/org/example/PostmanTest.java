package org.example;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;



public class PostmanTest {
    static Map<String,String> headers = new HashMap<>();


   @BeforeAll
    public static void setUp() throws IOException {
       RestAssured.filters(new AllureRestAssured());
       RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
       headers.put ("A", "B");
       /*FileInputStream fis;
       fis = new FileInputStream("src/test/resources/my.properties");
       prop.load (fis);*/
    }

    @AfterAll
    public static void tearDown() {

    }

    @Test
     void ListUsers() {
        given()
        .when()
                .request(Method.GET, "https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }
    @Test
    void SingleUser () {
        given()
                .when()
                .request(Method.GET, "https://reqres.in/api/users/2")
                .then()
                .statusCode(200);
    }

    @Test
    void SingleUserNotFound (){
        given()
                .when()
                .request(Method.GET, "https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }
    @Test
    void ListResource () {
        given()
                .when()
                .request(Method.GET, "https://reqres.in/api/unknown")
                .then()
                .statusCode(200);
    }

    @Test
    void SingleResource () {
        given()
                .when()
                .request(Method.GET, "https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200);
    }

    @Test
    void SingleResourceNotFound () {
        given()
                .when()
                .request(Method.GET, "https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404);
    }
    @Test
    void DelayedResponse () {
        given()
                .when()
                .request(Method.GET, "https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(200);
    }

    @Test
    void ListUsersC () {
        given()
                .when()
                .request(Method.GET, "https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }

    @Test
    void SingleUserC () {
        given()
                .when()
                .request(Method.GET, "https://reqres.in/api/users/2")
                .then()
                .statusCode(200);
    }
    @Test
    void SingleResourceNotFoundC () {
        given()
                .when()
                .request(Method.GET, "https://reqres.in/api/users/33")
                .then()
                .statusCode(404);
    }

    @Test
    void create () {
       String token = given().contentType("application/json; charset=utf-8")
                .when().body("{\"name\": \"morpheus\",\"job\":\"leader\"}")
                .request(Method.POST, "https://reqres.in/api/users")
               .prettyPeek()
                .then().statusCode(201).extract()
               .response()
               .jsonPath()
               .getString("token");
    }
    @Test
    void Update () {
        String token = given().contentType("application/json; charset=utf-8")
                .when().body("{\"name\": \"morpheus\",\"job\":\"zion resident\"}")
                .request(Method.PUT, "https://reqres.in/api/users/2")
                .prettyPeek()
                .then().statusCode(200).extract()
                .response()
                .jsonPath()
                .getString("token");
    }

}
