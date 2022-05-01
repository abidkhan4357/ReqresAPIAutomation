package org.reqres.interfaces;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface SendRequest {

    public Response performAction(String basePath, RequestSpecification requests);
}
