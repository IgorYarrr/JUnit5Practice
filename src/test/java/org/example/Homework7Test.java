package org.example;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.example.pojo.Users;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Homework7Test {
    public static final String BASE_URI = "https://reqres.in/";
    public static final String USER_BASE_PATH = "api/users";
    public static final String UNKNOWN_BASE_PATH = "api/unknown";

    @Test
    public void getSingleUser(){
        given()
                .pathParam("user", 2)
                .baseUri(BASE_URI)
                .when()
                .get("/api/users/{user}")
                .then()
                .statusCode(200);
    }

    @Test
    public void getListUser(){
        given()
                .queryParam("page", 2)
                .baseUri(BASE_URI)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200);
    }

    RequestSpecification userReqSpec = given()
            .baseUri(BASE_URI)
            .basePath(USER_BASE_PATH);
    ResponseSpecification resSpec = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    @Test
    public void specTest(){
        given()
                .pathParam("user", 2)
                .spec(userReqSpec)
                .when()
                .get("/{user}")
                .then()
                .spec(resSpec);
    }


    RequestSpecification unknownReqSpec = given()
            .baseUri(BASE_URI)
            .basePath(UNKNOWN_BASE_PATH);
    @Test
    public void getSingleResource(){
        given()
                .pathParam("id", 23)
                .spec(unknownReqSpec)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void postCreate() {
        Users user = Users.builder()
                .name("Alex")
                .job("Manager")
                .build();
        given()
                .baseUri(BASE_URI)
                .basePath(USER_BASE_PATH)
                .contentType(ContentType.JSON)
                .body(user)
                .log().all()
                .post()
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }
}
