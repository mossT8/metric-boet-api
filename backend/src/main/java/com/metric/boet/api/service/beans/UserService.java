package com.metric.boet.api.service.beans;

import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.user.UserRequestAbstract;
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
public class UserService extends AbstractDataBeanService<UserRequestAbstract> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SimpleUuidService simpleUuidService;

    @Autowired
    PasswordEncoder encoder;


    @Override
    public BasicAPIResponse findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            BasicAPIResponse response = new BasicAPIResponse();
            response.setData(mapperService.getUserDto(userOptional.get()));

            return response;
        }

        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse findByCreatedDate(Date createdAt) {
        List<User> users = userRepository.findByCreatedAt(createdAt);
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getUsersDto(users));

        return response;
    }

    @Override
    public BasicAPIResponse findByUpdatedDate(Date updatedAt) {
        List<User> users = userRepository.findByUpdatedAt(updatedAt);
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getUsersDto(users));

        return response;
    }

    @Override
    public BasicAPIResponse findByCreatedUserId(Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            return findByCreatedUser(userOptional.get());
        }

        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse findByCreatedUser(User user) {
        List<User> users = userRepository.findByUserCreated(user);
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getUsersDto(users));

        return response;
    }

    @Override
    public BasicAPIResponse findByUpdatedUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            return findByUpdatedUser(userOptional.get());
        }

        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse findByUpdatedUser(User user) {
        List<User> users = userRepository.findByLastUpdatedUser(user);
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getUsersDto(users));

        return response;
    }

    @Override
    public BasicAPIResponse create(UserRequestAbstract requestBean, IUserAudit userAudit) {

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
    public BasicAPIResponse replace(UserRequestAbstract requestBean, IUserAudit userAudit) {
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

            updateLastUpdated(user, userAudit);

            userRepository.save(user);

            return new BasicAPIResponse("User replaced successfully!", true);
        }


        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse update(UserRequestAbstract requestBean, IUserAudit userAudit) {
        Optional<User> userOptional = userRepository.findByUsername(requestBean.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setFirstName(requestBean.getFirstName());
            user.setLastName(requestBean.getLastName());
            user.setPhone(requestBean.getPhone());

            updateLastUpdated(user, userAudit);

            userRepository.save(user);

            return new BasicAPIResponse("User updated successfully!", true);
        }


        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse delete(UserRequestAbstract requestBean, IUserAudit userAudit) {
        Optional<User> userOptional = userRepository.findByUsername(requestBean.getUsername());

        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());

            return new BasicAPIResponse("User deleted successfully!", true);
        }


        return getNegativeResponse();
    }
}
