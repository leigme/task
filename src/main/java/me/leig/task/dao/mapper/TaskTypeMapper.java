package me.leig.task.dao.mapper;

import me.leig.task.dao.model.TaskType;

public interface TaskTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskType record);

    int insertSelective(TaskType record);

    TaskType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskType record);

    int updateByPrimaryKeyWithBLOBs(TaskType record);

    int updateByPrimaryKey(TaskType record);
}