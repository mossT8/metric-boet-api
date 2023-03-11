package com.metric.boet.api.core.generator;

public interface IAccountCodeGenerator {

    String getCurrentMaxAccountCode();
    String getNextAccountCode();
}
