package com.example.a48900.ledsistem.request;

import org.codehaus.jackson.annotate.JsonProperty;

public class LoginRequest {

    @JsonProperty("email")
    public String email;

    @JsonProperty("password")
    public String password;

}