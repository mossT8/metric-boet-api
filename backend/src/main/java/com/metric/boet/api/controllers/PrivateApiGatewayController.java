package com.metric.boet.api.controllers;

import com.metric.boet.api.util.api.ApiRequest;
import com.metric.boet.api.util.api.PrivateApiGatewayService;
import com.metric.boet.api.util.api.PublicApiGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v2/private")
public class PrivateApiGatewayController {

    @Autowired
    PrivateApiGatewayService privateApiGatewayService;

    @PostMapping("/")
    public ResponseEntity<?> handleRequestPost(HttpServletRequest httpServletRequest, @RequestBody ApiRequest<?> apiPayload) throws Exception {
        return privateApiGatewayService.handleRequestProcess(httpServletRequest, apiPayload);
    }

    @GetMapping("/")
    public ResponseEntity<?> handleRequestGet(HttpServletRequest httpServletRequest, @RequestBody ApiRequest<?> apiPayload) throws Exception {
        return privateApiGatewayService.handleRequestProcess(httpServletRequest, apiPayload);
    }

    @PatchMapping("/")
    public ResponseEntity<?> handleRequestPatch(HttpServletRequest httpServletRequest, @RequestBody ApiRequest<?> apiPayload) throws Exception {
        return privateApiGatewayService.handleRequestProcess(httpServletRequest, apiPayload);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> handleRequestDelete(HttpServletRequest httpServletRequest, @RequestBody ApiRequest<?> apiPayload) throws Exception {
        return privateApiGatewayService.handleRequestProcess(httpServletRequest, apiPayload);
    }
}
