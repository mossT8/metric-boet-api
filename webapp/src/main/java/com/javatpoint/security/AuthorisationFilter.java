package com.javatpoint.security;

import com.javatpoint.common.log.LogManager;
import jakarta.servlet.FilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Authorization is the process of verifying what you have access to.
 */
@Component
@Order(2)
public class AuthorisationFilter implements Filter {
    private LogManager logger;

    @Autowired
    public AuthorisationFilter(LogManager logger) {
        this.logger = logger;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, jakarta.servlet.ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get the user's authorization token from the request
        String authToken = httpRequest.getHeader("Authorization");
        String ip = httpRequest.getHeader("x-forwarded-for");

        // Validate the user's authorization token
        boolean isAuthorized = authorizationBasicAuthToken(authToken, ip);

        if (!isAuthorized) {
            // If the user is not authorized, return a 401 Unauthorized status code
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // If the user is authorized, continue processing the request
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code goes here
    }

    private boolean authorizationBasicAuthToken(String authHeader, String ip) {
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return false;
        }
      //  logger.logInfo("AUTHORIZING_IP: " + ip);

        String base64Credentials = authHeader.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                Charset.forName("UTF-8"));
        final String[] values = credentials.split(":", 2);

        String username = values[0];
        String password = values[1];

      //  logger.logInfo("AUTHORIZING_USER: " + username);

        // Check the username and password against a database or other service to determine if the user is authorized
        // Return true if the user is authorized, false otherwise
        return true;
    }

}