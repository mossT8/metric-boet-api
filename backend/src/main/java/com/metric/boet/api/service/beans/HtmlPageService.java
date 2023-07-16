package com.metric.boet.api.service.beans;

import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.entity.HtmlPage;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.HtmlPageApiRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.repository.HtmlPageRepository;
import com.metric.boet.api.util.service.AbstractDataBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
public class HtmlPageService extends AbstractDataBeanService<HtmlPageApiRequestAbstract> {

    @Autowired
    UserService userService;

    @Autowired
    HtmlPageRepository htmlPageRepository;

    @Override
    public BasicAPIResponse findById(Long id) {
        Optional<HtmlPage> htmlPageOptional = htmlPageRepository.findById(id);

        if (htmlPageOptional.isPresent()) {
            BasicAPIResponse response = new BasicAPIResponse();
            response.setData(mapperService.getHtmlPageDto(htmlPageOptional.get()));

            return response;
        }

        return getNegativeResponse();
    }

    public BasicAPIResponse findDtoByUrl(String url) throws Exception {
        BasicAPIResponse basicAPIResponse = findByUrl(url);

        if (basicAPIResponse.getSuccessful()) {
            basicAPIResponse.setData(mapperService.getHtmlPageDto((HtmlPage) basicAPIResponse.getData()));
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
    public BasicAPIResponse findByCreatedDate(Date createdAt) {
        List<HtmlPage> htmlPageList = htmlPageRepository.findByCreatedAt(createdAt);
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getHtmlPagesDto(htmlPageList));

        return response;
    }

    @Override
    public BasicAPIResponse findByUpdatedDate(Date updatedAt) {
        List<HtmlPage> htmlPageList = htmlPageRepository.findByUpdatedAt(updatedAt);
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getHtmlPagesDto(htmlPageList));

        return response;
    }

    @Override
    public BasicAPIResponse findByCreatedUserId(Long userId) {
        BasicAPIResponse userResponse = userService.findById(userId);

        if (userResponse.getSuccessful()) {
            return findByCreatedUser((User) userResponse.getData());
        }

        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse findByCreatedUser(User user) {
        List<HtmlPage> htmlPages = htmlPageRepository.findByUserCreated(user);

        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getHtmlPagesDto(htmlPages));

        return response;
    }

    @Override
    public BasicAPIResponse findByUpdatedUserId(Long userId) {
        BasicAPIResponse userResponse = userService.findById(userId);

        if (userResponse.getSuccessful()) {
            return findByUpdatedUser((User) userResponse.getData());
        }

        return getNegativeResponse();
    }

    @Override
    public BasicAPIResponse findByUpdatedUser(User user) {
        List<HtmlPage> htmlPages = htmlPageRepository.findByLastUpdatedUser(user);

        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(mapperService.getHtmlPagesDto(htmlPages));

        return response;
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
