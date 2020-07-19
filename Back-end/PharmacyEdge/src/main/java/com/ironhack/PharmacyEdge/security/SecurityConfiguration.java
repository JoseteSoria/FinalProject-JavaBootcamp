package com.ironhack.PharmacyEdge.security;

import com.ironhack.PharmacyEdge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests()
                //User controller
                .mvcMatchers(HttpMethod.GET, "/users").hasAuthority("ROLE_OWNER")
                .mvcMatchers(HttpMethod.POST, "/users").hasAuthority("ROLE_OWNER")
                .mvcMatchers(HttpMethod.GET, "/users/{id}").hasAuthority("ROLE_OWNER")
                .mvcMatchers(HttpMethod.DELETE, "/users/{id}").hasAuthority("ROLE_OWNER")
                //Patient controller
                .mvcMatchers(HttpMethod.GET, "/patients").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/patients/{id}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/patients/name/{name}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.POST, "/patients").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.DELETE, "/patients/{id}").hasAnyAuthority("ROLE_OWNER")
                .anyRequest().permitAll()
                .and().requestCache().requestCache(new NullRequestCache())
                .and().logout().deleteCookies("JSESSIONID");
    }

}

