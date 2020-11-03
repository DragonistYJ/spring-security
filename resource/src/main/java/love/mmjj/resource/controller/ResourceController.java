package love.mmjj.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('p1')")
    public String resource1() {
        return "resource 1";
    }

    @RequestMapping(value = "/resource2", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('p2')")
    public String resource2() {
        return "resource 2";
    }

    @RequestMapping(value = "/resource3", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('p3')")
    public String resource3() {
        return "resource 3";
    }
}
