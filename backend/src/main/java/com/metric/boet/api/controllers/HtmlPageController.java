package com.metric.boet.api.controllers;

import com.metric.boet.api.dto.HtmlPageDto;
import com.metric.boet.api.entity.HtmlPage;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payload.request.HtmlPageRequest;
import com.metric.boet.api.payload.response.MessageResponse;
import com.metric.boet.api.repository.HtmlPageRepository;
import com.metric.boet.api.repository.UserRepository;
import com.metric.boet.api.service.dto.mapper.imp.SimpleMapperService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/html/pages")
public class HtmlPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HtmlPageRepository htmlPageRepository;

    @Autowired
    private SimpleMapperService simpleMapperService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<HtmlPageDto>> getHtmlPages() throws ExecutionControl.NotImplementedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<HtmlPage> htmlPages = htmlPageRepository.findAll();
        List<HtmlPageDto> mappedDevices = simpleMapperService.getHtmlPagesDto(htmlPages);


        return ResponseEntity.ok(mappedDevices);
    }

    @GetMapping("/{url}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<HtmlPageDto> getHtmlPageByUrl(@PathVariable String url) throws ExecutionControl.NotImplementedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<HtmlPage> htmlPage = htmlPageRepository.getByUrl(url);
        if (htmlPage.isPresent()) {
            HtmlPageDto htmlPageDto = simpleMapperService.getHtmlPageDto(htmlPage.get());
            return ResponseEntity.ok(htmlPageDto);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> registerDeviceForUser(@Valid @RequestBody HtmlPageRequest htmlPageRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Date createDate = new Date();
        HtmlPage htmlPage = new HtmlPage();
        htmlPage.setHtml(htmlPageRequest.getHtml());
        htmlPage.setUrl(htmlPage.getUrl());
        htmlPageRepository.save(htmlPage);
        return ResponseEntity.ok(new MessageResponse("Html Page registered successfully!"));
    }
}


