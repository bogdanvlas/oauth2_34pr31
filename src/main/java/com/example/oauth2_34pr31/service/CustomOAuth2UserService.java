package com.example.oauth2_34pr31.service;

import com.example.oauth2_34pr31.model.User;
import com.example.oauth2_34pr31.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User =  super.loadUser(userRequest);
        String login = oauth2User.getAttribute("login");
        String provider = userRequest.getClientRegistration().getRegistrationId();
        boolean exists= userRepository.existsByLoginAndProvider(login, provider);
        if(!exists){
            User user = new User();
            user.setLogin(login);
            user.setProvider(provider);
            userRepository.save(user);
        }
        return oauth2User;
    }
}
