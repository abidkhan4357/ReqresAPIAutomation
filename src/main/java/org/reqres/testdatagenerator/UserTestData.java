package org.reqres.testdatagenerator;

import com.github.javafaker.Faker;
import org.reqres.datamodels.UserData;

import java.util.Locale;

public final class UserTestData {

    private UserTestData(){}

    public static UserData generateUserData(){
        Faker fakerData = new Faker(new Locale("en-US"));
        return UserData.builder().setName(fakerData.name().firstName())
                .setJob(fakerData.job().position()).build();
    }
}
