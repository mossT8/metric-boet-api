package com.metric.boet.api.util.api;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.repository.UserRepository;
import com.metric.boet.api.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;


public abstract class AbstractPrivateWebAppEndpointHandler<REQUEST_CLASS extends AbstractWebAppEndpointApiRequest> extends AbstractWebAppEndpointHandler<REQUEST_CLASS> {

    @Autowired
    protected UserRepository userRepo;

    @Autowired
    JwtUtils jwtUtils;

    protected User getUserFromRequest(HttpServletRequest httpServletRequest, REQUEST_CLASS payload) throws Exception {
        // Use the UserRepository to retrieve the user
        String username = jwtUtils.getUsernameFromRequest(httpServletRequest);
        User user = userRepo.findByUsername(username).orElseThrow(() -> new Exception("User not found"));

        return user;
    }

    protected abstract ResponseEntity<BasicAPIResponse> performRequest(HttpServletRequest httpServletRequest, REQUEST_CLASS payload, User user) throws Exception;

    protected abstract boolean isRequestForUser(HttpServletRequest httpServletRequest, REQUEST_CLASS payload, User user) throws Exception;

    protected ResponseEntity<BasicAPIResponse> processRequest(HttpServletRequest httpServletRequest, REQUEST_CLASS payload) {
        try {
            User user = getUserFromRequest(httpServletRequest, payload);
            if (isRequestForUser(httpServletRequest, payload, user)) {
                return performRequest(httpServletRequest, payload, user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().body(new BasicAPIResponse("Unable perform action due to user rights!", false));

    }
}
