package com.example.oauth2_34pr31.config;

import com.example.oauth2_34pr31.service.CustomOAuth2UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomOAuth2UserService userService;

    private OidcUserService oidcUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/secured/**").authenticated()
                .and()
                .oauth2Login()
                .loginPage("/login/oauth2")
                .userInfoEndpoint()
                .userService(userService)
                .oidcUserService(oidcUserService)
        ;
    }
}
