package org.reqres.tests;

import io.restassured.response.Response;
import org.reqres.apiclient.APIClient;
import org.reqres.datamodels.UserData;
import org.reqres.testdatagenerator.UserTestData;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserTests extends APIClient {
    APIClient apiClient = new APIClient();
    private Response apiResponse;
    private UserData userTestData;


    @BeforeTest
    public void setUpDataForTests(){
        userTestData = UserTestData.generateUserData();
    }
    @Test
    public void createuser(){
        apiResponse = apiClient.post("users", userTestData);
        apiResponse.prettyPrint();
    }

}
