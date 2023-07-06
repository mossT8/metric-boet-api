package com.metric.boet.api.controllers;

import com.metric.boet.api.util.uuid.imp.DeviceUidGenerator;
import com.metric.boet.api.dto.DeviceDto;
import com.metric.boet.api.entity.Device;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.DeviceApiRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.repository.DeviceRepository;
import com.metric.boet.api.repository.UserRepository;
import com.metric.boet.api.service.mapper.imp.MapperService;

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
    private MapperService mapperService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<DeviceDto>> getDevicesByUsername() throws ExecutionControl.NotImplementedException {
        // authenticate user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // get list
        List<Device> devices = new ArrayList<>();
        List<DeviceDto> mappedDevices = mapperService.getDevicesDto(devices);

        return ResponseEntity.ok(mappedDevices);
    }

    @GetMapping("/{uid}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<DeviceDto> getDeviceByUid(@RequestParam String uid) throws ExecutionControl.NotImplementedException {
        // authenticate user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // get device
        Optional<Device> device = deviceRepository.findByUuid(uid);
        if (device.isPresent()) {
            DeviceDto deviceDto = mapperService.getDeviceDto(device.get());
            return ResponseEntity.ok(deviceDto);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> addDeviceForUser(@Valid @RequestBody DeviceApiRequestAbstract deviceApiRequestAbstract) {
        // authenticate user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // add device
        Device newDevice = new Device(deviceApiRequestAbstract.getName(), deviceUidGenerator.getNextUnique(), deviceApiRequestAbstract.getType(), deviceApiRequestAbstract.getLocation(), deviceApiRequestAbstract.getStatus(), deviceApiRequestAbstract.getToken(), user.get());
        deviceRepository.save(newDevice);
        return ResponseEntity.ok(new BasicAPIResponse("Device added successfully for user!", true));
    }
}