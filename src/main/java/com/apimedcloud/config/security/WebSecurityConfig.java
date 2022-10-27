package com.apimedcloud.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST).hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE).hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET).hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT).hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager configureAuthentication() {
        List<UserDetails> userDetails = new ArrayList<>();
        List<GrantedAuthority> userRoles = new ArrayList<>();
        userRoles.add(new SimpleGrantedAuthority("USER"));

        List<GrantedAuthority> adminRoles = new ArrayList<>();
        adminRoles.add(new SimpleGrantedAuthority("ADMIN"));

        userDetails.add(new User("employee",
                "$2a$10$2072nXWTNNk2aNX9wkM/g.FYSZSe.A/i/VX7nv.Knl8sqk2BVTGei",
                userRoles));
        userDetails.add(new User("admin",
                "$2a$10$2072nXWTNNk2aNX9wkM/g.FYSZSe.A/i/VX7nv.Knl8sqk2BVTGei",
                adminRoles));
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
