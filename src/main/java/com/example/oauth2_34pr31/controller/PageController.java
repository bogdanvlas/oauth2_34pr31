package com.example.oauth2_34pr31.controller;

import com.example.oauth2_34pr31.model.GitHubUserInfo;
import com.example.oauth2_34pr31.model.GoogleUserInfo;
import com.example.oauth2_34pr31.model.UserInfo;
import com.example.oauth2_34pr31.model.UserInfoFactory;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class PageController {
    private UserInfoFactory userInfoFactory;

    @GetMapping
    public String index() {
        return "indexPage";
    }

    @GetMapping("/secured")
    public String securedPage(OAuth2AuthenticationToken token, Model m) {
        String provider = token.getAuthorizedClientRegistrationId();
        UserInfo userInfo = userInfoFactory.create(provider, token.getPrincipal().getAttributes());
        m.addAttribute("login", userInfo.getLogin());
        m.addAttribute("provider", provider);
        return "securedPage";
    }

    @GetMapping("/login/oauth2")
    public String loginPage() {
        return "loginPage";
    }
}
