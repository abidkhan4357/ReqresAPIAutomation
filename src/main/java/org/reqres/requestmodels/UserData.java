package org.reqres.requestmodels;

import io.github.sskorol.data.Source;
import lombok.Data;

@Data
@Source(path = "userdata.json")
public class UserData {

    private String name;
    private String job;
}
