package org.reqres.interfaces;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface ISendRequest {
    Response performAction(String basePath, RequestSpecification requests);
}
