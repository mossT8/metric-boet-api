package com.metric.boet.api.core.generator.imp;

import com.metric.boet.api.core.generator.IAccountCodeGenerator;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class AccountCodeGenerator implements IAccountCodeGenerator {
    final static String _PREFIX = "A";
    final static String _SUFFIX = "001";

    @Autowired
    UserRepository userRepository;

    @Override
    public String getCurrentMaxAccountCode() {

        List<User> users = userRepository.findAllByOrderByAccountCodeDesc();

        if (users.size() == 0) {
            return _PREFIX + _SUFFIX;
        } else {
            User lastUser = users.get(0);
            return lastUser.getAccountCode();
        }
    }

    @Override
    public String getNextAccountCode() {

        String maxAccountCode = getCurrentMaxAccountCode();

        String currentPrefix = maxAccountCode.substring(0, 1);
        String currentSuffix = maxAccountCode.substring(1);

        int nextSuffix = Integer.parseInt(currentSuffix) + 1;
        if (nextSuffix > 999) {
            char nextPrefix = (char) (currentPrefix.charAt(0) + 1);
            return nextPrefix + "001";
        } else {
            return currentPrefix + String.format("%03d", nextSuffix);
        }
    }
}
