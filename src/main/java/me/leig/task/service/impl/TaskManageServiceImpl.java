package me.leig.task.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.leig.task.base.Constant;
import me.leig.task.dao.mapper.TaskInfoMapper;
import me.leig.task.dao.model.TaskInfoWithBLOBs;
import me.leig.task.service.TaskManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TaskManageServiceImpl implements TaskManageService {

    @Autowired
    private TaskInfoMapper taskInfoMapper;


    @Override
    public boolean createTask(TaskInfoWithBLOBs taskInfoWithBLOBs) {
        if (null == taskInfoWithBLOBs || StringUtils.isEmpty(taskInfoWithBLOBs.getTitle())) {
            log.info(Constant.MESSAGE_PARAMETER_NULL);
            return false;
        }
        taskInfoWithBLOBs.setCreatetime(new Date());
        return false;
    }

    @Override
    public boolean updateTask(TaskInfoWithBLOBs taskInfoWithBLOBs) {
        if (null == taskInfoWithBLOBs || 0 == taskInfoWithBLOBs.getId()) {
            log.info(Constant.MESSAGE_PARAMETER_NULL);
            return false;
        }
        int result = taskInfoMapper.updateByPrimaryKeySelective(taskInfoWithBLOBs);
        return 0 < result;
    }

    @Override
    public boolean deleteTask(TaskInfoWithBLOBs taskInfoWithBLOBs) {
        if (null == taskInfoWithBLOBs || 0 == taskInfoWithBLOBs.getId()) {
            log.info(Constant.MESSAGE_PARAMETER_NULL);
            return false;
        }
        taskInfoWithBLOBs.setDeletetime(new Date());
        taskInfoWithBLOBs.setDeleteflag(2);
        int result = taskInfoMapper.updateByPrimaryKeySelective(taskInfoWithBLOBs);
        return 0 < result;
    }

    @Override
    public List<TaskInfoWithBLOBs> selectTaskInfo(TaskInfoWithBLOBs taskInfoWithBLOBs) {
        if (null == taskInfoWithBLOBs) {
            log.info(Constant.MESSAGE_PARAMETER_NULL);
            return null;
        }
        taskInfoWithBLOBs.setDeleteflag(1);
        List<TaskInfoWithBLOBs> taskInfoWithBLOBsList = taskInfoMapper.selectSelective(taskInfoWithBLOBs);
        if (CollectionUtils.isEmpty(taskInfoWithBLOBsList)) {
            log.info(Constant.MESSAGE_DATA_NULL);
            return null;
        }
        return taskInfoWithBLOBsList;
    }
}
