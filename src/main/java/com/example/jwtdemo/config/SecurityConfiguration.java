package com.example.jwtdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// @Secured for Controllers
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // this should be removed at production (csrf disabled for h2-console only)
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                //set pages which do not require authentication
                .antMatchers("/").permitAll()
                // enable Cross Site Reference Forgery, except for H2 console and REST APIs
                .and().csrf().ignoringAntMatchers("/h2-console/**").ignoringAntMatchers("/api/**")
                //maximum of one session per user
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).maximumSessions(1);
    }
}
