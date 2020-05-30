package com.bagevent.service;

import com.bagevent.common.ServerResponse;
import com.bagevent.dao.UserDao;
import com.bagevent.dao.UserLoginLogDao;
import com.bagevent.pojo.User;
import com.bagevent.pojo.UserLoginLog;
import com.bagevent.util.MD5Util;
import com.bagevent.util.PropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private UserLoginLogDao userLoginLogDao;

    @Transactional
    public ServerResponse<String> register(User user){
        user.setUserId(null);
        user.setState(true);
        user.setCreateTime(new Date());
        user.setSalt(PropertiesUtil.getProperty("password.salt", ""));
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword(),user.getSalt()));
        try {
            userDao.save(user);
            return ServerResponse.createBySuccessMessage("注册成功");
        }catch (Exception e){
            return ServerResponse.createByErrorMes("注册失败");
        }
    }

    @Transactional
    public ServerResponse<List<UserLoginLog>> login(String account, String password, String ip){
        User user = userDao.findByAccountAndPassword(account, MD5Util.MD5EncodeUtf8(password));
        if(user==null){
            return ServerResponse.createByErrorMes("用户不存在");
        }
        UserLoginLog userLoginLog=new UserLoginLog();
        userLoginLog.setLoginIp(ip);
        userLoginLog.setLoginTime(new Date());
        userLoginLog.setUserId(user.getUserId());
        userLoginLogDao.save(userLoginLog);
        return ServerResponse.createBySuccess(userLoginLogDao.selectAll());
    }
}
