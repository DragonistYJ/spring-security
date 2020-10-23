package love.mmjj.simple.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YuJian
 * @description
 * @since 2020/10/23
 */
@RestController
public class LoginController {
    @RequestMapping(value = "/login/success")
    public String loginSuccess() {
        return "login success";
    }
}
