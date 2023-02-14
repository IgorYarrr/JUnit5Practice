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
    public static final String baseUri = "https://reqres.in/";
    public static final String userBasePath = "api/users";
    public static final String unknownBasePath = "api/unknown";

    @Test
    public void getSingleUser(){
        given()
                .pathParam("user", 2)
                .baseUri(baseUri)
                .when()
                .get("/api/users/{user}")
                .then()
                .statusCode(200);
    }

    @Test
    public void getListUser(){
        given()
                .queryParam("page", 2)
                .baseUri(baseUri)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200);
    }

    RequestSpecification userReqSpec = given()
            .baseUri(baseUri)
            .basePath(userBasePath);
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
            .baseUri(baseUri)
            .basePath(unknownBasePath);
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
    public void postCreate(){
        Users user = Users.builder()
                .name("Alex")
                .job("Manager")
                .build();
        given()
                .baseUri(baseUri)
                .basePath(userBasePath)
                .contentType(ContentType.JSON)
                .body(user.toString())
                .post()
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }
}
