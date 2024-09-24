package com.metric.boet.api.controllers;

import com.metric.boet.api.endpoints.open.pages.ViewHtmlPage;
import com.metric.boet.api.payloads.request.basic.KeyApiRequestAbstract;
import com.metric.boet.api.util.api.open.PublicApiGatewayService;
import com.metric.boet.api.util.api.request.WebAppApiRequestHolderBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v2/public")
public class PublicGatewayController {

    @Autowired
    PublicApiGatewayService publicApiGatewayService;

    @Autowired
    ViewHtmlPage viewHtmlPage;

    @GetMapping("/view")
    public ResponseEntity<?> handleHelloRequestPost(HttpServletRequest httpServletRequest, @RequestBody KeyApiRequestAbstract apiPayload) throws Exception {
        return viewHtmlPage.processRequest(httpServletRequest, apiPayload);
    }

    @PostMapping("/")
    public ResponseEntity<?> handleRequestPost(HttpServletRequest httpServletRequest, @RequestBody WebAppApiRequestHolderBean apiPayload) throws Exception {
        return publicApiGatewayService.handleRequestProcess(httpServletRequest, apiPayload);
    }

    @GetMapping("/")
    public ResponseEntity<?> handleRequestGet(HttpServletRequest httpServletRequest, @RequestBody WebAppApiRequestHolderBean apiPayload) throws Exception {
        return publicApiGatewayService.handleRequestProcess(httpServletRequest, apiPayload);
    }

    @PatchMapping("/")
    public ResponseEntity<?> handleRequestPatch(HttpServletRequest httpServletRequest, @RequestBody WebAppApiRequestHolderBean apiPayload) throws Exception {
        return publicApiGatewayService.handleRequestProcess(httpServletRequest, apiPayload);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> handleRequestDelete(HttpServletRequest httpServletRequest, @RequestBody WebAppApiRequestHolderBean apiPayload) throws Exception {
        return publicApiGatewayService.handleRequestProcess(httpServletRequest, apiPayload);
    }
}
