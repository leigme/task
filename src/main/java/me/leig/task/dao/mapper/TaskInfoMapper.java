package me.leig.task.dao.mapper;

import me.leig.task.dao.model.TaskInfo;
import me.leig.task.dao.model.TaskInfoWithBLOBs;

import java.util.List;

public interface TaskInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskInfoWithBLOBs record);

    int insertSelective(TaskInfoWithBLOBs record);

    TaskInfoWithBLOBs selectByPrimaryKey(Integer id);

    List<TaskInfoWithBLOBs> selectSelective(TaskInfoWithBLOBs record);

    int updateByPrimaryKeySelective(TaskInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TaskInfoWithBLOBs record);

    int updateByPrimaryKey(TaskInfo record);
}