package com.bagevent.controller;

import com.bagevent.common.ServerResponse;
import com.bagevent.pojo.User;
import com.bagevent.pojo.UserLoginLog;
import com.bagevent.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user/")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("register")
    public String register(User user, Map map){
        ServerResponse<String> register = userService.register(user);
        map.put("mes",register.getMes());
        System.out.println("haha");
        return "register";
    }

    @RequestMapping("login")
    public String login(@RequestParam(name = "account",required = true) String account,
                                        @RequestParam(name = "password",required = true) String password,
                        HttpServletRequest req,Map map){
        String ip=req.getRemoteAddr();
        ServerResponse<List<UserLoginLog>> login = userService.login(account, password,ip);
        map.put("mes",login.getMes());
        if(!login.isSuccess()){
            return "failure";
        }
        map.put("data",login.getData());
        return "success";
    }
}
