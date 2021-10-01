package com.example.oauth2_34pr31.model;

import java.util.Map;

public class GoogleUserInfo extends UserInfo {

    public GoogleUserInfo(Map<String, Object> atrributes) {
        super(atrributes);
    }

    @Override
    public String getLogin() {
        return (String) atrributes.get("email");
    }
}
