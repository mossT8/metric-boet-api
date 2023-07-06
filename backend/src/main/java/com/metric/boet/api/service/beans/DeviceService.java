package com.metric.boet.api.service.beans;

import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.entity.Device;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.DeviceApiRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.repository.DeviceRepository;
import com.metric.boet.api.repository.UserRepository;
import com.metric.boet.api.util.service.AbstractDataBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DeviceService extends AbstractDataBeanService<DeviceApiRequestAbstract> {
    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public BasicAPIResponse findById(Long id) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);

        if (deviceOptional.isPresent()) {
            BasicAPIResponse response = new BasicAPIResponse();
            response.setData(mapperService.getDeviceDto(deviceOptional.get()));
            return response;
        }

        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse findByCreatedDate(Date createdAt) {
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getDevicesDto(deviceRepository.findByCreatedAt(createdAt)));
        return response;
    }

    @Override
    public BasicAPIResponse findByUpdatedDate(Date updatedAt) {
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getDevicesDto(deviceRepository.findByCreatedAt(updatedAt)));
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
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getDevicesDto(deviceRepository.findByUserCreated(user)));

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
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getDevicesDto(deviceRepository.findByLastUpdatedUser(user)));

        return response;
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
