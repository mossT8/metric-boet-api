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
    public ResponseEntity<List<HtmlPageDto>> listPages() throws ExecutionControl.NotImplementedException {
        // authenticate user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // get list
        List<HtmlPage> htmlPages = htmlPageRepository.findAll();
        List<HtmlPageDto> mappedDevices = simpleMapperService.getHtmlPagesDto(htmlPages);


        return ResponseEntity.ok(mappedDevices);
    }

    @GetMapping("/{url}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<HtmlPageDto> getPage(@PathVariable String url) throws ExecutionControl.NotImplementedException {
        // authenticate user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // get page
        Optional<HtmlPage> htmlPage = htmlPageRepository.getByUrl(url);
        if (htmlPage.isPresent()) {
            HtmlPageDto htmlPageDto = simpleMapperService.getHtmlPageDto(htmlPage.get());
            return ResponseEntity.ok(htmlPageDto);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createPage(@Valid @RequestBody HtmlPageRequest htmlPageRequest) {
        // authenticate user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // check page url not in already
        Optional<HtmlPage> possiblePage = htmlPageRepository.getByUrl(htmlPageRequest.getUrl());

        if (possiblePage.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Url is already in use!", false));
        }

        // add page
        HtmlPage htmlPage = new HtmlPage();
        htmlPage.setHtml(htmlPageRequest.getHtml());
        htmlPage.setUrl(htmlPage.getUrl());
        htmlPageRepository.save(htmlPage);

        return ResponseEntity.ok(new MessageResponse("Html Page created successfully!", true));
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updatePage(@Valid @RequestBody HtmlPageRequest htmlPageRequest) {
        // authenticate user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // check page url is in use
        Optional<HtmlPage> possiblePage = htmlPageRepository.getByUrl(htmlPageRequest.getUrl());

        if (!possiblePage.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Url is not in use!", false));
        }

        // update page
        HtmlPage htmlPage = possiblePage.get();
        htmlPage.setHtml(htmlPageRequest.getHtml());
        htmlPage.setUrl(htmlPageRequest.getUrl());
        htmlPage.setVisibleForModerators(htmlPageRequest.getVisibleForModerators());
        htmlPage.setVisibleForUsers(htmlPageRequest.getVisibleForUsers());
        htmlPage.setVisibleOnNav(htmlPageRequest.getVisibleOnNav());

        htmlPageRepository.save(htmlPage);

        return ResponseEntity.ok(new MessageResponse("Html Page updated successfully!", true));
    }
}


