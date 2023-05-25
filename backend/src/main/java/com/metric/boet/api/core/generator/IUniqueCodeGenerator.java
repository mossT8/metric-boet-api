package com.metric.boet.api.core.generator;

public interface IUniqueCodeGenerator {
    String getNextUnique();
    String getLastUsed();
}
