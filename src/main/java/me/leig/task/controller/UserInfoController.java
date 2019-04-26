package me.leig.task.controller;

import me.leig.task.base.BaseController;
import me.leig.task.base.Constant;
import me.leig.task.dao.model.UserInfo;
import me.leig.task.model.Result;
import me.leig.task.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "login", method = {RequestMethod.POST})
    @ResponseBody
    public Result<String> login(@RequestParam String username, @RequestParam String password) {
        Result<String> result = new Result<>();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        password = userInfoService.passwordEncryption(username, password);
        if (StringUtils.isEmpty(password)) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        String token = userInfoService.receiveToken(username, password);
        if (StringUtils.isEmpty(token)) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        result.setT(token);
        return result;
    }

    @RequestMapping(value = "add", method = {RequestMethod.POST})
    @ResponseBody
    public Result<Boolean> addUserInfo(@RequestBody UserInfo userInfo) {
        Result<Boolean> result = new Result<>();
        if (null == userInfo || StringUtils.isEmpty(userInfo.getAccount())) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        if (StringUtils.isEmpty(userInfo.getPassword())) {
            userInfo.setPassword("123456");
        }
        String password = userInfoService.passwordEncryption(userInfo.getAccount(), userInfo.getPassword());
        userInfo.setPassword(password);
        boolean addResult = userInfoService.addUserInfo(userInfo);
        if (!addResult) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        result.setT(true);
        return result;
    }

    @RequestMapping(value = "find", method = {RequestMethod.POST})
    @ResponseBody
    public Result<List<UserInfo>> selectUserInfoList(@RequestBody UserInfo userInfo) {
        Result<List<UserInfo>> result = new Result<>();
        if (null == userInfo || StringUtils.isEmpty(userInfo.getAccount())) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        List<UserInfo> userInfoList = userInfoService.selectUserinfo(userInfo);
        if (CollectionUtils.isEmpty(userInfoList)) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        result.setT(userInfoList);
        return result;
    }
}
