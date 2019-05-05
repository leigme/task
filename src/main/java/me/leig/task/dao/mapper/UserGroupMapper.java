package me.leig.task.dao.mapper;

import me.leig.task.dao.model.Resource;
import me.leig.task.dao.model.UserGroup;

import java.util.List;

public interface UserGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);

    /**
     * 通过用户账号获取用户权限
     *
     * @param username
     * @return
     */
    List<Resource> selectResourceByUsername(String username);
}