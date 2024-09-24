package com.metric.boet.api.service.beans;

import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.entity.HtmlPage;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.HtmlPageApiRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.repository.HtmlPageRepository;
import com.metric.boet.api.util.service.AbstractTrackedDataBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HtmlPageServiceTracked extends AbstractTrackedDataBeanService<HtmlPageApiRequestAbstract, HtmlPage> {

    @Autowired
    HtmlPageRepository htmlPageRepository;

    @Autowired
    UserServiceTracked userService;

    @Override
    public HtmlPage findBeanById(Long id) throws NoSuchElementException {
        Optional<HtmlPage> htmlPageOptional = htmlPageRepository.findById(id);
        if (htmlPageOptional.isPresent()) {
            return htmlPageOptional.get();
        }

        throw new NoSuchElementException("No html page by that ID " + id);    }

    @Override
    public List<HtmlPage> findBeanByCreatedDate(Date createdAt) {
        return htmlPageRepository.findByCreatedAt(createdAt);
    }

    @Override
    public List<HtmlPage> findBeanByUpdatedDate(Date updatedAt) {
        return htmlPageRepository.findByUpdatedAt(updatedAt);
    }

    @Override
    public List<HtmlPage> findBeanByCreatedUserId(Long userId) {
        try {
            User user = userService.findBeanById(userId);
            return findBeanByCreatedUser(user);
        } catch (NoSuchElementException e) {
            return new ArrayList<>();
        }    }

    @Override
    public List<HtmlPage> findBeanByCreatedUser(User user) {
        return htmlPageRepository.findByUserCreated(user);
    }

    @Override
    public List<HtmlPage> findBeanByUpdatedUserId(Long userId) {
        try {
            User user = userService.findBeanById(userId);
            return findBeanByUpdatedUser(user);
        } catch (NoSuchElementException e) {
            return new ArrayList<>();
        }    }

    @Override
    public List<HtmlPage> findBeanByUpdatedUser(User user) {
        return htmlPageRepository.findByLastUpdatedUser(user);
    }

    public BasicAPIResponse findDtoByUrl(String url) throws Exception {
        BasicAPIResponse basicAPIResponse = findByUrl(url);

        if (basicAPIResponse.getSuccessful()) {
            basicAPIResponse.setData(((HtmlPage) basicAPIResponse.getData()).mapToDTO());
            return basicAPIResponse;
        }

        return basicAPIResponse;
    }


    public BasicAPIResponse findByUrl(String url) {
        Optional<HtmlPage> htmlPageOptional = htmlPageRepository.findByUrl(url);
        BasicAPIResponse basicAPIResponse = new BasicAPIResponse();

        if (htmlPageOptional.isPresent()) {
            basicAPIResponse.setData(htmlPageOptional.get());
            return basicAPIResponse;
        }

        basicAPIResponse.setSuccessful(false);
        basicAPIResponse.setMessage("Unable to find by url provided.");

        return basicAPIResponse;
    }

    @Override
    public BasicAPIResponse create(HtmlPageApiRequestAbstract requestBean, IUserAudit userAudit) {

        if (htmlPageRepository.existsByUrl(requestBean.getUrl())) {
            return new BasicAPIResponse("Error: Url is already taken!", false);
        }

        HtmlPage htmlPage = new HtmlPage(userAudit);

        htmlPage.setUrl(requestBean.getUrl());
        htmlPage.setHtml(requestBean.getHtml());
        htmlPage.setVisibleOnNav(requestBean.getVisibleOnNav());
        htmlPage.setVisibleForUsers(requestBean.getVisibleForUsers());
        htmlPage.setVisibleForModerators(requestBean.getVisibleForModerators());

        htmlPageRepository.save(htmlPage);

        return new BasicAPIResponse("Html created successfully!", true);

    }

    @Override
    public BasicAPIResponse replace(HtmlPageApiRequestAbstract requestBean, IUserAudit userAudit) {
        Optional<HtmlPage> htmlPageOptional = htmlPageRepository.findByUrl(requestBean.getUrl());

        if (htmlPageOptional.isPresent()) {
            HtmlPage htmlPage = htmlPageOptional.get();

            htmlPage.setUrl(requestBean.getUrl());
            htmlPage.setHtml(requestBean.getHtml());
            htmlPage.setVisibleOnNav(requestBean.getVisibleOnNav());
            htmlPage.setVisibleForUsers(requestBean.getVisibleForUsers());
            htmlPage.setVisibleForModerators(requestBean.getVisibleForModerators());

            updateLastUpdated(htmlPage, userAudit);

            htmlPageRepository.save(htmlPage);

            return new BasicAPIResponse("Html Page replaced successfully!", true);
        }

        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse update(HtmlPageApiRequestAbstract requestBean, IUserAudit userAudit) {
        Optional<HtmlPage> htmlPageOptional = htmlPageRepository.findByUrl(requestBean.getUrl());

        if (htmlPageOptional.isPresent()) {
            HtmlPage htmlPage = htmlPageOptional.get();

            htmlPage.setHtml(requestBean.getHtml());
            htmlPage.setVisibleOnNav(requestBean.getVisibleOnNav());
            htmlPage.setVisibleForUsers(requestBean.getVisibleForUsers());
            htmlPage.setVisibleForModerators(requestBean.getVisibleForModerators());

            updateLastUpdated(htmlPage, userAudit);

            htmlPageRepository.save(htmlPage);

            return new BasicAPIResponse("Html Page replaced successfully!", true);
        }

        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse delete(HtmlPageApiRequestAbstract requestBean, IUserAudit userAudit) {
        Optional<HtmlPage> htmlPageOptional = htmlPageRepository.findByUrl(requestBean.getUrl());

        if (htmlPageOptional.isPresent()) {
            HtmlPage htmlPage = htmlPageOptional.get();

            htmlPageRepository.delete(htmlPage);

            return new BasicAPIResponse("Html Page deleted successfully!", true);
        }

        return getNegativeResponse();
    }
}
