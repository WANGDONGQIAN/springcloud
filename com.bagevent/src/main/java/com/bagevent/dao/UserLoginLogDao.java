package com.bagevent.dao;

import com.bagevent.pojo.UserLoginLog;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("UserLoginLogDao")
public class UserLoginLogDao {
    @Resource
    private SessionFactory sessionFactory;

    public void save(UserLoginLog userLoginLog){
        sessionFactory.getCurrentSession().save(userLoginLog);
    }

    public List<UserLoginLog> selectAll(){
        return sessionFactory.getCurrentSession().createCriteria(UserLoginLog.class).list();
    }
}
