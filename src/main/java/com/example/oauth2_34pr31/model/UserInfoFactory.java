package com.example.oauth2_34pr31.model;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserInfoFactory {
    public UserInfo create(String provider, Map<String, Object> attr) {
        UserInfo userInfo = null;
        if (provider.equalsIgnoreCase("github")) {
            userInfo = new GitHubUserInfo(attr);
        }
        if (provider.equalsIgnoreCase("google")) {
            userInfo = new GoogleUserInfo(attr);
        }
        return userInfo;
    }
}
