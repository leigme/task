package me.leig.task.dao.mapper;

import me.leig.task.dao.model.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKeyWithBLOBs(Resource record);

    int updateByPrimaryKey(Resource record);
}