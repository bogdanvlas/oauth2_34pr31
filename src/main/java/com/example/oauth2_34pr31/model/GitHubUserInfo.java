package com.example.oauth2_34pr31.model;

import java.util.Map;

public class GitHubUserInfo extends UserInfo {

    public GitHubUserInfo(Map<String, Object> atrributes) {
        super(atrributes);
    }

    @Override
    public String getLogin() {
        return (String) atrributes.get("login");
    }
}
