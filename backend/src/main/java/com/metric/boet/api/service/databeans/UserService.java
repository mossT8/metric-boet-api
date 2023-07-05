package com.metric.boet.api.service.databeans;

import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.user.UserRequest;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.repository.UserRepository;
import com.metric.boet.api.service.uiid.imp.SimpleUuidService;
import com.metric.boet.api.util.service.AbstractDataBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService extends AbstractDataBeanService<UserRequest> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SimpleUuidService simpleUuidService;

    @Autowired
    PasswordEncoder encoder;


    @Override
    public BasicAPIResponse getById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            BasicAPIResponse response = new BasicAPIResponse();
            response.setData(mapperService.getUserDto(userOptional.get()));

            return response;
        }

        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse getByCreatedDate(Date createdAt) {
        List<User> users = userRepository.getByCreatedAt(createdAt);
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getUsersDto(users));

        return response;
    }

    @Override
    public BasicAPIResponse getByUpdatedDate(Date updatedAt) {
        List<User> users = userRepository.getByUpdatedAt(updatedAt);
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getUsersDto(users));

        return response;
    }

    @Override
    public BasicAPIResponse getByCreatedUserId(Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            return getByCreatedUser(userOptional.get());
        }

        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse getByCreatedUser(User user) {
        List<User> users = userRepository.getByUserCreated(user);
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getUsersDto(users));

        return response;
    }

    @Override
    public BasicAPIResponse getByUpdatedUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            return getByUpdatedUser(userOptional.get());
        }

        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse getByUpdatedUser(User user) {
        List<User> users = userRepository.getByLastUpdatedUser(user);
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getUsersDto(users));

        return response;
    }

    @Override
    public BasicAPIResponse create(UserRequest requestBean, IUserAudit userAudit) {

        if (userRepository.existsByUsername(requestBean.getUsername())) {
            return new BasicAPIResponse("Error: Username is already taken!", false);
        }

        if (userRepository.existsByEmail(requestBean.getEmail())) {
            return new BasicAPIResponse("Error: Email is already in use!", false);
        }

        User user = new User(userAudit);

        user.setFirstName(requestBean.getFirstName());
        user.setAccountCode(simpleUuidService.getNextUserAccountCode());
        user.setLastName(requestBean.getLastName());
        user.setUsername(requestBean.getUsername());
        user.setPassword(encoder.encode(requestBean.getPassword()));
        user.setEmail(requestBean.getEmail());
        user.setPhone(requestBean.getPhone());

        userRepository.save(user);

        return new BasicAPIResponse("User created successfully!", true);
    }

    @Override
    public BasicAPIResponse replace(UserRequest requestBean, IUserAudit userAudit) {
        Optional<User> userOptional = userRepository.findByUsername(requestBean.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setFirstName(requestBean.getFirstName());
            user.setAccountCode(simpleUuidService.getNextUserAccountCode());
            user.setLastName(requestBean.getLastName());
            user.setUsername(requestBean.getUsername());
            user.setPassword(encoder.encode(requestBean.getPassword()));
            user.setEmail(requestBean.getEmail());
            user.setPhone(requestBean.getPhone());

            userRepository.save(user);

            return new BasicAPIResponse("User replaced successfully!", true);
        }


        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse update(UserRequest requestBean, IUserAudit userAudit) {
        Optional<User> userOptional = userRepository.findByUsername(requestBean.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setFirstName(requestBean.getFirstName());
            user.setLastName(requestBean.getLastName());
            user.setPhone(requestBean.getPhone());

            userRepository.save(user);

            return new BasicAPIResponse("User updated successfully!", true);
        }


        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse delete(UserRequest requestBean, IUserAudit userAudit) {
        Optional<User> userOptional = userRepository.findByUsername(requestBean.getUsername());

        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());

            return new BasicAPIResponse("User deleted successfully!", true);
        }


        return getNegativeResponse();
    }
}
