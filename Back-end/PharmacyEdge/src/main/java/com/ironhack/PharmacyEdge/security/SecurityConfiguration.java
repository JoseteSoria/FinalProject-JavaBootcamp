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
                //Medicine controller
                .mvcMatchers(HttpMethod.GET, "/medicines").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/medicines/{id}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/medicines/name/{name}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/warehouse-medicines").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/warehouse-medicines/{id}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/warehouse-medicines/name/{name}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/warehouse-medicines/name/{name}/quantity").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.PUT, "/warehouse-medicines/{id}/update-price/{price}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.POST, "/warehouse-medicines/{id}/add/{quantity}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.DELETE, "/warehouse-medicines/delete/{id}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                //Order Controller
                .mvcMatchers(HttpMethod.GET, "/orders").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/orders/{id}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/orders/last").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.POST, "/orders").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/medicines-ordered").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/medicines-ordered/{id}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/medicines-ordered/order/{id}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.POST, "/medicines-ordered").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                //Sell Controller
                .mvcMatchers(HttpMethod.GET, "/sales").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/sales/{id}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/sales/last").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.POST, "/sales").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/medicines-sold").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/medicines-sold/{id}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.GET, "/medicines-sold/sales/{id}").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .mvcMatchers(HttpMethod.POST, "/medicines-sold").hasAnyAuthority("ROLE_OWNER", "ROLE_ASSISTANT", "ROLE_PHARMACIST")
                .anyRequest().permitAll()
                .and().requestCache().requestCache(new NullRequestCache())
                .and().logout().deleteCookies("JSESSIONID");
    }

}

