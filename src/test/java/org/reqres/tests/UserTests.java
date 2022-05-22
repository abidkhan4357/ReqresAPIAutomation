package org.reqres.tests;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.reqres.apiclient.APIClient;
import org.reqres.constants.ResponseCode;
import org.reqres.constants.Services;
import org.reqres.requestmodels.UserData;
import org.reqres.testdatasupplier.UserCreationData;
import org.reqres.utils.JsonSchemaUtil;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class UserTests extends APIClient {
    APIClient apiClient = new APIClient();
    private Response apiResponse;

    @Test(dataProvider = "getTwoUserData", dataProviderClass = UserCreationData.class)
    public void createTwoSetsOfUsers(UserData userData){
        apiResponse = apiClient.post(Services.USERS, userData);
        Assertions.assertThat(apiResponse.statusCode()).isEqualTo(ResponseCode.STATUS_CODE_201);
        System.out.println(apiResponse.prettyPrint());
        MatcherAssert.assertThat(apiResponse.getBody().asString(),
        JsonSchemaValidator.matchesJsonSchema(new File(JsonSchemaUtil.getSchemaFilepath("UserCreation.json"))));
        Assertions.assertThat(apiResponse.getTimeIn(TimeUnit.SECONDS)).isLessThanOrEqualTo(2);
        Assertions.assertThat(apiResponse.getHeader("Content-Type")).contains("application/json");
    }

}
