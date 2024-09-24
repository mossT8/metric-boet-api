package com.metric.boet.api.service.beans;

import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.entity.Device;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.DeviceApiRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.repository.DeviceRepository;
import com.metric.boet.api.util.service.AbstractTrackedDataBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceServiceTracked extends AbstractTrackedDataBeanService<DeviceApiRequestAbstract, Device> {
    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    UserServiceTracked userService;

    @Override
    public Device findBeanById(Long id) throws NoSuchElementException {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if (deviceOptional.isPresent()) {
            return deviceOptional.get();
        }

        throw new NoSuchElementException("No device by that ID " + id);
    }

    @Override
    public List<Device> findBeanByCreatedDate(Date createdAt) {
        return deviceRepository.findByCreatedAt(createdAt);
    }

    @Override
    public List<Device> findBeanByUpdatedDate(Date updatedAt) {
        return deviceRepository.findByUpdatedAt(updatedAt);
    }

    @Override
    public List<Device> findBeanByCreatedUserId(Long userId) {
        try {
            User user = userService.findBeanById(userId);
            return findBeanByCreatedUser(user);
        } catch (NoSuchElementException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Device> findBeanByCreatedUser(User user) {
        return deviceRepository.findByUserCreated(user);
    }

    @Override
    public List<Device> findBeanByUpdatedUserId(Long userId) {
        try {
            User user = userService.findBeanById(userId);
            return findBeanByUpdatedUser(user);
        } catch (NoSuchElementException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Device> findBeanByUpdatedUser(User user) {
        return deviceRepository.findByLastUpdatedUser(user);
    }

    public BasicAPIResponse getBeanByUiid(String uiid) {
        Optional<Device> deviceOptional = deviceRepository.findByUuid(uiid);

        if (deviceOptional.isPresent()) {
            BasicAPIResponse response = new BasicAPIResponse();
            response.setData(deviceOptional.get());
            return response;
        }

        return getNegativeResponse();
    }

    public BasicAPIResponse findByUiid(String uiid) {
        Optional<Device> deviceOptional = deviceRepository.findByUuid(uiid);

        if (deviceOptional.isPresent()) {
            BasicAPIResponse response = new BasicAPIResponse();
            response.setData(deviceOptional.get().mapToDTO());
            return response;
        }

        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse create(DeviceApiRequestAbstract requestBean, IUserAudit userAudit) {
        return null;
    }

    @Override
    public BasicAPIResponse replace(DeviceApiRequestAbstract requestBean, IUserAudit userAudit) {
        return null;
    }

    @Override
    public BasicAPIResponse update(DeviceApiRequestAbstract requestBean, IUserAudit userAudit) {
        return null;
    }

    @Override
    public BasicAPIResponse delete(DeviceApiRequestAbstract requestBean, IUserAudit userAudit) {
        Optional<Device> deviceOptional = deviceRepository.findByUuid(requestBean.getUuid());

        if (deviceOptional.isPresent()) {
            deviceRepository.delete(deviceOptional.get());

            return new BasicAPIResponse("Device deleted successfully!", true);
        }


        return getNegativeResponse();
    }
}
