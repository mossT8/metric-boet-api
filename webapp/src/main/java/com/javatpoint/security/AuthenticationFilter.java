package com.javatpoint.security;

import com.javatpoint.common.log.LogManager;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

import jakarta.servlet.Filter;

/**
 * Authentication is the process of verifying who you are.
 * Authentication is a prerequisite for Authorization,
 * as you need to know who the user is before you can check what they have access to
 */
@Component
@Order(1)
public class AuthenticationFilter implements Filter {
    private LogManager logger;

    @Autowired
    public AuthenticationFilter(LogManager logger) {
        this.logger = logger;
    }

    private boolean authenticateBasicAuthToken(String authHeader, String ip) {
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return false;
        }

     //   logger.logInfo("AUTHENTICATING_IP: " + ip);

        String base64Credentials = authHeader.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                Charset.forName("UTF-8"));
        final String[] values = credentials.split(":", 2);

        String username = values[0];
        String password = values[1];

     //   logger.logInfo("AUTHENTICATING_USER: " + username);


        // Check the username and password against a database or other service to determine if the user is authorized
        // Return true if the user is authorized, false otherwise
        return true;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, jakarta.servlet.FilterChain chain) throws IOException, jakarta.servlet.ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Extract the authentication token from the request header
        String authToken = httpRequest.getHeader("Authorization");
        String ip = httpRequest.getHeader("X-Forwarded-For");

        // If the authentication token is missing or invalid, return a 401 Unauthorized response
        if (!authenticateBasicAuthToken(authToken, ip)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Continue processing the request if the authentication token is valid
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Clean up resources if necessary
    }
}
