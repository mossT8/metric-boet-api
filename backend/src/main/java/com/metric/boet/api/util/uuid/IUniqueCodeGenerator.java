package com.metric.boet.api.util.uuid;

public interface IUniqueCodeGenerator {
    String getNextUnique();
    String getLastUsed();
}
