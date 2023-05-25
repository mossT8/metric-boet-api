package com.metric.boet.api.core.generator.imp;

import com.metric.boet.api.core.generator.IUniqueCodeGenerator;
import com.metric.boet.api.entity.Device;
import com.metric.boet.api.repository.DeviceRepository;
import com.metric.boet.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class DeviceUidGenerator implements IUniqueCodeGenerator {
    private final UUID namespace;

    @Autowired
    DeviceRepository deviceRepository;

    public DeviceUidGenerator() {
        // Use a random UUID as the namespace for generating UIDs
        this.namespace = UUID.randomUUID();
    }

    @Override
    public String getNextUnique() {
        // Increment the current max uid and generate a UUID based on the namespace and the new max uid
        long currentMaxUid = Long.parseLong(getLastUsed());
        UUID uid = new UUID(namespace.getMostSignificantBits(), currentMaxUid);
        // Return the UUID in the format "xxxx-xxxxx-xxxx-xxxx-xxxxx"
        return String.format("%04x-%05x-%04x-%04x-%05x", uid.getMostSignificantBits() >> 48 & 0xffff,
                uid.getMostSignificantBits() >> 32 & 0x1ffff, uid.getMostSignificantBits() >> 16 & 0xffff,
                uid.getMostSignificantBits() & 0xffff, uid.getLeastSignificantBits() & 0xfffff);
    }

    @Override
    public String getLastUsed() {
        Optional<Device> lastDevice = deviceRepository.findLastCreated();
        return Long.toString(lastDevice.isPresent() ? lastDevice.get().getId() : 0L);
    }

}