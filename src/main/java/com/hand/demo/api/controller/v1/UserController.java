package com.hand.demo.api.controller.v1;

import com.hand.demo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    //  http://localhost:8080/v1/users/login
    //  {"name":"player","password":"123456"}
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody Map<String ,Object> map) {
        List<Integer> list = null;
        try {
            list = userService.login(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list.size() == 0) {
            return "Login Fail!";
        }
        return "Login Success!";
    }

}
