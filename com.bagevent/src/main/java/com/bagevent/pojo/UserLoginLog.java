package com.bagevent.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userLoginLog")
public class UserLoginLog {

    @Id
    @Column(name = "login_id")
    @GeneratedValue
    private Long loginId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login_ip")
    private String loginIp;

    @Column(name = "login_time")
    private Date loginTime;

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
