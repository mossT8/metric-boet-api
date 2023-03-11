package com.metric.boet.api.core.generator.imp;

import com.metric.boet.api.core.generator.IDeviceUidGenerator;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class DeviceUidGenerator implements IDeviceUidGenerator {
    private final UUID namespace;
    private long currentMaxUid;

    public DeviceUidGenerator() {
        // Use a random UUID as the namespace for generating UIDs
        this.namespace = UUID.randomUUID();
        // Initialize the currentMaxUid to 0
        this.currentMaxUid = 0;
    }

    @Override
    public String getNextUid() {
        // Increment the current max uid and generate a UUID based on the namespace and the new max uid
        currentMaxUid++;
        UUID uid = new UUID(namespace.getMostSignificantBits(), currentMaxUid);
        // Return the UUID in the format "xxxx-xxxxx-xxxx-xxxx-xxxxx"
        return String.format("%04x-%05x-%04x-%04x-%05x", uid.getMostSignificantBits() >> 48 & 0xffff,
                uid.getMostSignificantBits() >> 32 & 0x1ffff, uid.getMostSignificantBits() >> 16 & 0xffff,
                uid.getMostSignificantBits() & 0xffff, uid.getLeastSignificantBits() & 0xfffff);
    }
}