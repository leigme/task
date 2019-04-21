package me.leig.task.controller;

import lombok.extern.slf4j.Slf4j;
import me.leig.task.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class IndexController extends BaseController {

    @RequestMapping(value = "index", method = {RequestMethod.GET})
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (null == session) {
            return "login";
        }
        log.info("登录的Session是 {}", session.getId());
        return "index";
    }
}
