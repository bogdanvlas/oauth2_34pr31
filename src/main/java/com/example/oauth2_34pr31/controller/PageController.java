package com.example.oauth2_34pr31.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PageController {
    @GetMapping
    public String index() {
        return "indexPage";
    }

    @GetMapping("/secured")
    public String securedPage(OAuth2AuthenticationToken token, Model m) {
        String provider = token.getAuthorizedClientRegistrationId();
        String login = token.getPrincipal().getAttribute("login");
        m.addAttribute("login", login);
        m.addAttribute("provider", provider);
        return "securedPage";
    }
}
