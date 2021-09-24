package com.example.oauth2_34pr31.config;

import com.example.oauth2_34pr31.service.CustomOAuth2UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomOAuth2UserService userService;

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
        ;
    }
}
