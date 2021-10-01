package com.example.oauth2_34pr31.service;

import com.example.oauth2_34pr31.model.User;
import com.example.oauth2_34pr31.model.UserInfo;
import com.example.oauth2_34pr31.model.UserInfoFactory;
import com.example.oauth2_34pr31.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomOidcUserService extends OidcUserService {
    private UserRepository userRepository;
    private UserInfoFactory userInfoFactory;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oauth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();

        UserInfo userInfo = userInfoFactory.create(provider, oauth2User.getAttributes());

        boolean exists = userRepository.existsByLoginAndProvider(userInfo.getLogin(), provider);
        if (!exists) {
            User user = new User();
            user.setLogin(userInfo.getLogin());
            user.setProvider(provider);
            userRepository.save(user);
        }
        return oauth2User;
    }
}
