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
@RequestMapping("/resource")
public class ResourceController {
    @RequestMapping(value = "/resource1", method = RequestMethod.GET)
    public String resource1() {
        return "resource 1";
    }

    @RequestMapping(value = "/resource2", method = RequestMethod.GET)
    public String resource2() {
        return "resource 2";
    }
}
