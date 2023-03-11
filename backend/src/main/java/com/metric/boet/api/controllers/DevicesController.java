package com.metric.boet.api.controllers;

import com.metric.boet.api.core.generator.imp.DeviceUidGenerator;
import com.metric.boet.api.dto.DeviceDto;
import com.metric.boet.api.entity.Device;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payload.request.DeviceRequest;
import com.metric.boet.api.payload.response.MessageResponse;
import com.metric.boet.api.repository.DeviceRepository;
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
import java.util.*;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/devices")
public class DevicesController {

    @Autowired
    DeviceUidGenerator deviceUidGenerator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private SimpleMapperService simpleMapperService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<DeviceDto>> getDevicesByUsername() throws ExecutionControl.NotImplementedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<Device> devices = deviceRepository.findByUserUsername(username);
        List<DeviceDto> mappedDevices = simpleMapperService.getDevicesDto(devices);


        return ResponseEntity.ok(mappedDevices);
    }

    @GetMapping("/{uid}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<DeviceDto> getDeviceByUid(@RequestParam String uid) throws ExecutionControl.NotImplementedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Device> device = deviceRepository.findByUuid(uid);
        if (device.isPresent()) {
            DeviceDto deviceDto = simpleMapperService.getDeviceDto(device.get());
            return ResponseEntity.ok(deviceDto);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> registerDeviceForUser(@Valid @RequestBody DeviceRequest deviceRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Date createDate = new Date();
        Device newDevice = new Device(deviceRequest.getName(), deviceUidGenerator.getNextUid(), deviceRequest.getType(), deviceRequest.getLocation(), deviceRequest.getStatus(), deviceRequest.getToken(), user.get(), createDate, createDate);
        deviceRepository.save(newDevice);
        return ResponseEntity.ok(new MessageResponse("Device registered successfully!"));
    }
}




