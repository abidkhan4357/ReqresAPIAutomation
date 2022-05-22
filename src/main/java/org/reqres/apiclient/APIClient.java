package org.reqres.apiclient;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.reqres.config.ConfigFactory;
import org.reqres.enums.HttpMethod;
import org.reqres.interfaces.ISendRequest;
import org.reqres.utils.JsonSerializerUtil;

import java.util.HashMap;
import java.util.Map;

import static org.reqres.enums.HttpMethod.*;

public class APIClient extends APICore{
    private static RequestSpecification requestSpecification = RestAssured.given();
     public APIClient(){
         setBaseUrl(ConfigFactory.getConfig().baseURL());
    }
    
    @Override
    protected void setBaseUrl(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    @Override
    protected RequestSpecification createRequest() {
        RequestSpecification request = RestAssured.given();
        return request;
    }

    @Override
    protected Response getResponse(HttpMethod httpMethod, RequestSpecification requestSpecification, String basePath) {
        Response response = null;
        try {
            response = requestMethod.get(httpMethod).performAction(basePath, requestSpecification);
        }catch (IllegalArgumentException e){
            System.out.println(httpMethod + " method not allowed. Please pass the correct http method");
        }
        return response;
    }

    @Override
    public Response get(String basePath) {
        requestSpecification = createRequest();
        return getResponse(GET, requestSpecification, basePath);
    }

    @Override
    public Response post(String basePath, Object payload) {
        requestSpecification = createRequest();
        requestSpecification.body(JsonSerializerUtil.getSerializedJSON(payload));
        return getResponse(POST, requestSpecification, basePath);
    }

    private static Map<HttpMethod, ISendRequest> requestMethod = new HashMap<>() {
        private static final long serialVersionUID = 1L;
        {
            put(GET, (basePath, requests) -> requestSpecification.get(basePath));
            put(POST, (basePath, requests) -> requestSpecification.post(basePath));
            put(PUT, (basePath, requests) -> requestSpecification.put(basePath));
            put(PATCH, (basePath, requests) -> requestSpecification.patch(basePath));
            put(DELETE, (basePath, requests) -> requestSpecification.delete(basePath));
        }
    };

}
