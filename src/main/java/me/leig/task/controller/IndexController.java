package me.leig.task.controller;

import lombok.extern.slf4j.Slf4j;
import me.leig.task.base.BaseController;
import me.leig.task.base.Constant;
import me.leig.task.dao.model.Resource;
import me.leig.task.dao.model.UserGroup;
import me.leig.task.model.Result;
import me.leig.task.service.CacheService;
import me.leig.task.service.ResourceService;
import me.leig.task.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Slf4j
public class IndexController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "index", method = {RequestMethod.GET})
    public String index(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(token)) {
            return "login";
        }
        String serviceToken = cacheService.getCache(username);
        if (StringUtils.isEmpty(serviceToken)) {
            return "login";
        }
        if (!token.equals(serviceToken)) {
            return "login";
        }
        model.addAttribute("title", "任务列表");
        // 获取用户权限
        List<Resource> resources = resourceService.selectResourceByUsername(username);
        // 根据用户权限加载菜单
        model.addAttribute("resources", resources);
        // 根据第一个菜单加载显示数据
        Resource resource = resources.get(0);

        return "index";
    }

    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public String login(Model model) {
        model.addAttribute("title", "登录页面");
        return "login";
    }
}
