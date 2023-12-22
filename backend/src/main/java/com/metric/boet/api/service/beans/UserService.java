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

import java.util.*;

@Service
public class UserService extends AbstractDataBeanService<UserRequestAbstract, User> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SimpleUuidService simpleUuidService;

    @Autowired
    PasswordEncoder encoder;

    public User findBeanByUsername(String username) throws NoSuchElementException{
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new NoSuchElementException("No user by that username " + username);
    }

    public BasicAPIResponse findResponseByUsername(String accountCode) {
        User bean = findBeanByUsername(accountCode);
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(bean.mapToDTO());
        return response;
    }

    public User findBeanByAccountCode(String accountCode) throws NoSuchElementException{
        Optional<User> userOptional = userRepository.findByAccountCode(accountCode);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new NoSuchElementException("No user by that Account Code " + accountCode);
    }

    public BasicAPIResponse findResponseByAccountCode(String accountCode) {
        try {
            User bean = findBeanByAccountCode(accountCode);
            BasicAPIResponse response = new BasicAPIResponse();
            response.setData(bean.mapToDTO());
            return response;
        } catch (NoSuchElementException e) {
            return getNegativeResponse();
        }
    }


    @Override
    public User findBeanById(Long id) throws NoSuchElementException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new NoSuchElementException("No user by that ID " + id);
    }

    @Override
    public List<User> findBeanByCreatedDate(Date createdAt) {

        return userRepository.findByCreatedAt(createdAt);
    }

    @Override
    public List<User> findBeanByUpdatedDate(Date updatedAt) {
        return userRepository.findByUpdatedAt(updatedAt);
    }

    @Override
    public List<User> findBeanByCreatedUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            return findBeanByCreatedUser(userOptional.get());
        }

        return new ArrayList<>();
    }

    @Override
    public List<User> findBeanByCreatedUser(User user) {
        return userRepository.findByUserCreated(user);
    }

    @Override
    public List<User> findBeanByUpdatedUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            return findBeanByUpdatedUser(userOptional.get());
        }

        return new ArrayList<>();
    }

    @Override
    public List<User> findBeanByUpdatedUser(User user) {
        return userRepository.findByLastUpdatedUser(user);
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
