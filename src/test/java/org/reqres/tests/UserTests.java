package org.reqres.tests;

import io.restassured.response.Response;
import org.reqres.apiclient.APIClient;
import org.reqres.datamodels.UserData;
import org.reqres.testdatasupplier.UserCreationData;
import org.testng.annotations.Test;

public class UserTests extends APIClient {
    APIClient apiClient = new APIClient();
    private Response apiResponse;

    @Test(dataProvider = "getTwoUserData", dataProviderClass = UserCreationData.class)
    public void createTwoSetsOfUsers(UserData userData){
        apiResponse = apiClient.post("users", userData);
        apiResponse.prettyPrint();
    }

}
