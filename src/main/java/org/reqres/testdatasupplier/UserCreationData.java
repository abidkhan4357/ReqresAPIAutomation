package org.reqres.testdatasupplier;

import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.JsonReader;
import one.util.streamex.StreamEx;
import org.reqres.requestmodels.UserData;

import static io.github.sskorol.data.TestDataReader.use;

public final class UserCreationData {

    private UserCreationData(){}

    @DataSupplier
    public StreamEx<UserData> getTwoUserData(){
        return use(JsonReader.class)
                .withTarget(UserData.class)
                .read();
    }
}
