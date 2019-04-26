package me.leig.task.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.leig.task.base.Constant;
import me.leig.task.base.aes.AESUtils;
import me.leig.task.dao.mapper.UserInfoMapper;
import me.leig.task.dao.model.UserInfo;
import me.leig.task.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private AESUtils aesUtils;

    @Override
    public boolean addUserInfo(UserInfo userInfo) {
        if (null == userInfo) {
            log.info(Constant.MESSAGE_PARAMETER_NULL);
            return false;
        }
        if (StringUtils.isEmpty(userInfo.getAccount())) {
            log.info(Constant.MESSAGE_PARAMETER_NULL);
            return false;
        }
        int result = userInfoMapper.insertSelective(userInfo);
        return 0 < result;
    }

    @Override
    public boolean addUserInfo(List<UserInfo> userInfoList) {
        if (CollectionUtils.isEmpty(userInfoList)) {
            log.info(Constant.MESSAGE_PARAMETER_NULL);
            return false;
        }
        try {
            for (UserInfo userInfo: userInfoList) {
                addUserInfo(userInfo);
            }
            return true;
        } catch (Exception e) {
            log.info(Constant.MESSAGE_SQL_ERROR + e);
        }
        return false;
    }

    @Override
    public boolean updateUserInfo(UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean deleteUserInfo(UserInfo userInfo) {
        return false;
    }

    @Override
    public UserInfo selectUserinfo(String account) {
        if (StringUtils.isEmpty(account)) {
            log.info(Constant.MESSAGE_PARAMETER_NULL);
            return null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(account);
        List<UserInfo> userInfos = selectUserinfo(userInfo);
        if (CollectionUtils.isEmpty(userInfos)) {
            log.info(Constant.MESSAGE_DATA_NULL);
            return null;
        }
        return userInfos.get(0);
    }

    @Override
    public List<UserInfo> selectUserinfo(UserInfo userInfo) {
        if (null == userInfo) {
            log.info(Constant.MESSAGE_PARAMETER_NULL);
            return null;
        }
        if (StringUtils.isEmpty(userInfo.getAccount())) {
            log.info(Constant.MESSAGE_PARAMETER_NULL);
            return null;
        }
        List<UserInfo> userInfos = userInfoMapper.selectSelective(userInfo);
        if (CollectionUtils.isEmpty(userInfos)) {
            log.info(Constant.MESSAGE_DATA_NULL);
            return null;
        }
        return userInfos;
    }

    @Override
    public String receiveToken(String username, String password) {
        UserInfo userInfo = selectUserinfo(username);
        if (null == userInfo) {
            log.info(Constant.MESSAGE_DATA_NULL);
            return null;
        }
        if (userInfo.getPassword().equals(password)) {
            return aesUtils.encrypt(username + new Date());
        }
        return null;
    }

    @Override
    public String passwordEncryption(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            log.info(Constant.MESSAGE_PARAMETER_NULL);
            return null;
        }
        return aesUtils.encrypt(username + password);
    }
}
