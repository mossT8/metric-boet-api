package com.metric.boet.api.util.repo;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.util.repo.bean.AbstractTrackedDataBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Date;
import java.util.List;

@NoRepositoryBean
public interface IAbstractTrackedDataBeanRepository<BEAN_CLASS extends AbstractTrackedDataBean> extends JpaRepository<BEAN_CLASS, Long> {

    List<BEAN_CLASS> findByCreatedAt(Date createdAt);

    List<BEAN_CLASS> findByUpdatedAt(Date updatedAt);

    List<BEAN_CLASS> findByUserCreated(User user);

    List<BEAN_CLASS> findByLastUpdatedUser(User user);
}
