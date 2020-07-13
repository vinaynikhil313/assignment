package com.vinaypabba.service3;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserName {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Surname")
    private String surname;

}
