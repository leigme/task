package me.leig.task.service;

import me.leig.task.dao.model.TaskInfoWithBLOBs;

import java.util.List;

/**
 * 任务管理服务
 */
public interface TaskManageService {

    /**
     * 创建任务
     *
     * @param taskInfoWithBLOBs
     * @return
     */
    boolean createTask(TaskInfoWithBLOBs taskInfoWithBLOBs);

    /**
     * 更新任务
     *
     * @param taskInfoWithBLOBs
     * @return
     */
    boolean updateTask(TaskInfoWithBLOBs taskInfoWithBLOBs);

    /**
     * 删除任务
     *
     * @param taskInfoWithBLOBs
     * @return
     */
    boolean deleteTask(TaskInfoWithBLOBs taskInfoWithBLOBs);

    /**
     * 检索任务集合
     *
     * @param taskInfoWithBLOBs
     * @return
     */
    List<TaskInfoWithBLOBs> selectTaskInfo(TaskInfoWithBLOBs taskInfoWithBLOBs);

}
