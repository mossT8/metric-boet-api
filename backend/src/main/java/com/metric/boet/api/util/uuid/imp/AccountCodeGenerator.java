package com.metric.boet.api.util.uuid.imp;

import com.metric.boet.api.util.uuid.IUniqueCodeGenerator;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountCodeGenerator implements IUniqueCodeGenerator {
    final static String _PREFIX = "A";
    final static String _SUFFIX = "001";

    @Autowired
    UserRepository userRepository;

    @Override
    public String getNextUnique() {
        String maxAccountCode = getLastUsed();

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

    @Override
    public String getLastUsed() {
        List<User> users = userRepository.findAllByOrderByAccountCodeDesc();

        if (users.size() == 0) {
            return _PREFIX + _SUFFIX;
        } else {
            User lastUser = users.get(0);
            return lastUser.getAccountCode();
        }
    }
}
