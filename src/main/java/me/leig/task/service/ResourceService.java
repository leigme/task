package me.leig.task.service;

import me.leig.task.dao.model.Resource;

import java.util.List;

public interface ResourceService {

    List<Resource> selectResourceByUsername(String username);

}
