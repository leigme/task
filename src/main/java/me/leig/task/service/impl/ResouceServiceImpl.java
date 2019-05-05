package me.leig.task.service.impl;

import me.leig.task.dao.mapper.UserGroupMapper;
import me.leig.task.dao.model.Resource;
import me.leig.task.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ResouceServiceImpl implements ResourceService {

    @Autowired
    private UserGroupMapper userGroupMapper;

    @Override
    public List<Resource> selectResourceByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }

        List<Resource> resources = userGroupMapper.selectResourceByUsername(username);

        if (CollectionUtils.isEmpty(resources)) {
            return null;
        }

        return resources;
    }
}
