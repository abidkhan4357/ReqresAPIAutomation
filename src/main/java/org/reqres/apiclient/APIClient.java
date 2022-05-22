package org.reqres.apiclient;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.reqres.config.ConfigFactory;
import org.reqres.enums.HttpMethod;
import org.reqres.interfaces.ISendRequest;
import org.reqres.utils.JsonSerializerUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.reqres.enums.HttpMethod.*;

public class APIClient extends APICore{
    private static RequestSpecification requestSpecification = RestAssured.given();
    protected static ThreadLocal<String> requestResponseBody = new ThreadLocal<>();

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
            getAPIResponseAndLogIt(response);
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

     private static void getAPIResponseAndLogIt(Response response){
         QueryableRequestSpecification queryable = SpecificationQuerier.query(requestSpecification);
         String requestLog = RequestPrinter.print((RequestSpecificationImpl) requestSpecification, queryable.getMethod(),
                 queryable.getURI(), LogDetail.ALL, new HashSet<>(),
                 new PrintStream(new ByteArrayOutputStream()), true);
         requestResponseBody.set(requestLog + "\n\nResponse Headers:\n " + response.headers().toString()
                 + "\n\nResponse Body:\n " + response.getBody().asPrettyString());
     }

    public ThreadLocal<String> getRequestResponseLogs(){
        return requestResponseBody;
    }


}
