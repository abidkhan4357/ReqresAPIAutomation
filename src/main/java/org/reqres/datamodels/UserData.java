package org.reqres.datamodels;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set")
public class UserData {

    private String name;
    private String job;
}
