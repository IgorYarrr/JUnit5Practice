package org.example;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.pojo.Users;
import org.example.pojo.UsersPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.number.OrderingComparison.lessThan;

public class Homework8Test {
    public static final String BASE_URI = "https://reqres.in/";
    public static final String USER_BASE_PATH = "api/users";

    RequestSpecification userReqSpec = given()
            .baseUri(BASE_URI)
            .basePath(USER_BASE_PATH);

    @Test
    public void headerContentTypeTest(){
        Users user = Users.builder()
                .name("Alex")
                .job("Manager")
                .build();
        given()
                .spec(userReqSpec)
                .contentType(ContentType.JSON)
                .body(user)
                .post()
                .then()
                .assertThat().contentType(ContentType.JSON)
                .assertThat().header("Access-Control-Allow-Origin", "*");
    }

    @Test
    public void statusLineBody(){
        given()
                .queryParam("page", 2)
                .spec(userReqSpec)
                .when()
                .get()
                .then()
                .body("support.url", response -> equalTo("https://reqres.in/#support-heading"))
                .body("support.text", response -> equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"))
                .assertThat().statusLine(containsString("HTTP/1.1"));
    }

    @Test
    public void bodyPojo(){
        UsersPage pojo = given()
                .queryParam("page", 2)
                .spec(userReqSpec)
                .when()
                .get()
                .then()
                .extract().body().as(UsersPage.class);
        Assertions.assertEquals(pojo.getSupport().getText(), "To keep ReqRes free, contributions towards server costs are appreciated!");
        Assertions.assertEquals(pojo.getSupport().getUrl(), "https://reqres.in/#support-heading");
    }

    @Test
    public void timeCheck(){
        long reqTime = get(BASE_URI+USER_BASE_PATH).time();
        System.out.println("Time for request: " + reqTime);
        given()
                .spec(userReqSpec)
                .when()
                .get()
                .then()
                .time(lessThan(reqTime));
    }
}
