package com.vodafone.controller;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BooksController extends BaseController{

    public static Response registerRequest (String requestBody) {
        return given()
                .spec(baseRequestSpecification)
                .body(requestBody)
                .when().post("/auth");
    }


    public static Response addBookingRequest(String requestBody) {
        return given()
                .spec(baseRequestSpecification)
                .body(requestBody)
                .when().post("/booking");
    }



    public static Response getBookingsRequest () {
        return given()
                .relaxedHTTPSValidation()
                .spec(baseRequestSpecification)
                .when().get("/booking");
    }


}
