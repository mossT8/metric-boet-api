package com.javatpoint.security.config;

import com.javatpoint.common.log.LogManager;
import com.javatpoint.security.BasicAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private LogManager logger;

    @Autowired
    BasicAuthenticationProvider customAuthProvider;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthProvider);
        createInMemoryUsers(authenticationManagerBuilder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager)
            throws Exception {
        http.csrf().disable().httpBasic()
                .and()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/metrics", "/metrics/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                ).authenticationManager(authManager);
        return http.build();
    }

    protected void createInMemoryUsers(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("client-agent")
                .password(passwordEncoder.encode("client-agent"))
                .roles(Roles.AGENT.name())
                .and()
                .withUser("client")
                .password(passwordEncoder.encode("client"))
                .roles(Roles.ADMIN.name())
                .and()
                .withUser("tannar")
                .password(passwordEncoder.encode("moss"))
                .roles(Roles.SUPERUSER.name());
    }

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }

    @EventListener
    public void handleAuthenticationSuccessEvent(AuthenticationSuccessEvent event) {
        WebAuthenticationDetails details = (WebAuthenticationDetails) event.getAuthentication().getDetails();
        String accountId = event.getAuthentication().getName();
        String ipAddress = details.getRemoteAddress();

        // Log a message indicating a successful authentication
        logger.logInfo("Successful authentication: Account ID = {" + accountId + "}, IP address = {" + ipAddress + "}, Time = {" + LocalDateTime.now() + "}");
    }

    @EventListener
    public void handleAuthenticationFailureBadCredentialsEvent(AuthenticationFailureBadCredentialsEvent event) {
        WebAuthenticationDetails details = (WebAuthenticationDetails) event.getAuthentication().getDetails();
        String accountId = event.getAuthentication().getName();
        String ipAddress = details.getRemoteAddress();

        // Log a message indicating an unsuccessful authentication
        logger.logInfo("Unsuccessful authentication: Account ID = {" + accountId + "}, IP address = {" + ipAddress + "}, Time = {" + LocalDateTime.now() + "}");
    }
}