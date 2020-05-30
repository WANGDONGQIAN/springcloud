package com.bagevent.dao;

import com.bagevent.pojo.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;

@Repository("UserDao")
public class UserDao {

    @Resource
    private SessionFactory sessionFactory;

    public void save(User user){
        sessionFactory.getCurrentSession().save(user);
    }

    public User findByAccountAndPassword(String account,String password){
        return (User)sessionFactory.getCurrentSession().
                createQuery("from User where password=? and (cellphone=? or email=?)").
                setParameter(0,password).setParameter(1,account).
                setParameter(2,account).uniqueResult();
    }
}
