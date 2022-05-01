package org.reqres.apiclient;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class APICore {

    protected abstract void setBaseUrl(String baseUrl);
    protected abstract RequestSpecification createRequest();
    protected abstract Response getResponse(String httpMethod, RequestSpecification requestSpecification, String basePath);
    protected abstract Response get(String basePath);
    protected abstract Response post(String basePath, Object payload);
}
