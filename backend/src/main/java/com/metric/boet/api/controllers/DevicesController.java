package com.metric.boet.api.controllers;

import com.metric.boet.api.models.Device;
import com.metric.boet.api.models.User;
import com.metric.boet.api.payload.request.DeviceRequest;
import com.metric.boet.api.payload.response.MessageResponse;
import com.metric.boet.api.repository.DeviceRepository;
import com.metric.boet.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/devices")
public class DevicesController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/user/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Device>> getDevicesByUsername(@PathVariable(value = "username") String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<Device> devices = deviceRepository.findByUserUsername(username);
        return ResponseEntity.ok(devices);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> registerDeviceForUser(@Valid @RequestBody DeviceRequest deviceRequest, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Date createDate = new Date();
        Device newDevice = new Device(deviceRequest.getName(), deviceRequest.getType(), deviceRequest.getLocation(), deviceRequest.getStatus(), deviceRequest.getToken(), user.get(), createDate, createDate);
        deviceRepository.save(newDevice);
        return ResponseEntity.ok(new MessageResponse("Device registered successfully!"));
    }
}




