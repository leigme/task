package me.leig.task.controller;

import me.leig.task.base.BaseController;
import me.leig.task.base.Constant;
import me.leig.task.dao.model.TaskInfoWithBLOBs;
import me.leig.task.model.Result;
import me.leig.task.service.TaskManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskManageController extends BaseController {

    @Autowired
    private TaskManageService taskManageService;

    @RequestMapping(value = "find", method = {RequestMethod.POST})
    @ResponseBody
    public Result<List<TaskInfoWithBLOBs>> selectTaskInfoList(@RequestBody TaskInfoWithBLOBs taskInfoWithBLOBs) {
        Result<List<TaskInfoWithBLOBs>> result = new Result<>();
        if (null == taskInfoWithBLOBs) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        List<TaskInfoWithBLOBs> taskInfoWithBLOBsList = taskManageService.selectTaskInfo(taskInfoWithBLOBs);
        if (CollectionUtils.isEmpty(taskInfoWithBLOBsList)) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        result.setT(taskInfoWithBLOBsList);
        return result;
    }

    @RequestMapping(value = "add", method = {RequestMethod.POST})
    @ResponseBody
    public Result<Boolean> addTaskInfo(@RequestBody TaskInfoWithBLOBs taskInfoWithBLOBs) {
        Result<Boolean> result = new Result<>();
        if (null == taskInfoWithBLOBs) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        boolean taskResult = taskManageService.createTask(taskInfoWithBLOBs);
        if (!taskResult) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        result.setT(true);
        return result;
    }

    @RequestMapping(value = "update", method = {RequestMethod.POST})
    @ResponseBody
    public Result<Boolean> updateTaskInfo(@RequestBody TaskInfoWithBLOBs taskInfoWithBLOBs) {
        Result<Boolean> result = new Result<>();
        if (null == taskInfoWithBLOBs) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        boolean taskResult = taskManageService.updateTask(taskInfoWithBLOBs);
        if (!taskResult) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        result.setT(true);
        return result;
    }

    @RequestMapping(value = "delete", method = {RequestMethod.POST})
    @ResponseBody
    public Result<Boolean> deleteTaskInfo(@RequestBody TaskInfoWithBLOBs taskInfoWithBLOBs) {
        Result<Boolean> result = new Result<>();
        if (null == taskInfoWithBLOBs) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        boolean taskResult = taskManageService.deleteTask(taskInfoWithBLOBs);
        if (!taskResult) {
            result.setErrorCode(Constant.ERROR_CODE_FAILURE);
            result.setErrorMessage(Constant.ERROR_MESSAGE_FAILURE);
            return result;
        }
        result.setT(true);
        return result;
    }
}
