package com.example.jwtdemo.config;

import com.example.jwtdemo.domain.security.Authority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
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
                // set pages which do not require authentication
                .antMatchers("/", "/welcome", "/login").permitAll()
                //set pages which require authentication (no ROLE_ prefix)
                .antMatchers("/authenticated/**").hasAnyRole(Authority.USER, Authority.ADMIN)
                .antMatchers("/api/**").hasAnyRole(Authority.USER, Authority.ADMIN)
                // handle login and logout
                .and().httpBasic()
                .and().formLogin().loginPage("/login").permitAll().failureUrl("/login-error")
                .and().logout().logoutSuccessUrl("/welcome").permitAll()
                // enable Cross Site Reference Forgery, except for H2 console and REST APIs
                .and().csrf().ignoringAntMatchers("/h2-console/**").ignoringAntMatchers("/api/**")
                // maximum of one session per user
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).maximumSessions(1);
    }

    // required for AWS or other systems that require explicit template resolvers
    @Bean
    public ClassLoaderTemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver =
                new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");

        return templateResolver;
    }

    // required for AWS
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());

        // add support for Spring Security dialect
        // https://www.baeldung.com/spring-security-thymeleaf
        templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }
}
