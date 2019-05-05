package me.leig.task.controller;

import lombok.extern.slf4j.Slf4j;
import me.leig.task.base.BaseController;
import me.leig.task.base.Constant;
import me.leig.task.model.Result;
import me.leig.task.service.CacheService;
import me.leig.task.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class IndexController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "index", method = {RequestMethod.GET})
    public String index(HttpServletResponse response) {
        String username = response.getHeader("username");
        String token = response.getHeader("token");
        log.info("登录的Token是 {}", token);
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(token)) {
            return "login";
        }
        String serviceToken = cacheService.getCache(username);
        if (StringUtils.isEmpty(serviceToken) || !serviceToken.equals(token)) {
            return "login";
        }
        return "index";
    }

    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public String login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        Result<String> result = new Result<>();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return "login";
        }
        password = userInfoService.passwordEncryption(username, password);
        if (StringUtils.isEmpty(password)) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return "login";
        }
        String token = userInfoService.receiveToken(username, password);
        if (StringUtils.isEmpty(token)) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return "login";
        }
        result.setT(token);
        cacheService.setCache(username, token);
        response.setHeader("username", username);
        response.setHeader("token", token);
        return "index";
    }
}
