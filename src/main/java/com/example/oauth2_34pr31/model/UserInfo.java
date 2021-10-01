package com.example.oauth2_34pr31.model;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public abstract class UserInfo {
    protected Map<String, Object> atrributes;

    public abstract String getLogin();
}
