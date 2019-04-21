package me.leig.task.controller;

import me.leig.task.base.BaseController;
import me.leig.task.base.Constant;
import me.leig.task.model.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @RequestMapping(value = "login",method = {RequestMethod.POST})
    @ResponseBody
    public Result<String> login(@RequestParam String username, @RequestParam String password) {
        Result<String> result = new Result<>();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
        }

        return result;
    }
}
