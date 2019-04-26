package me.leig.task.service;

import me.leig.task.dao.model.UserInfo;

import java.util.List;

/**
 * 用户管理服务
 */
public interface UserInfoService {

    /**
     * 添加新用户
     *
     * @param userInfo
     * @return
     */
    boolean addUserInfo(UserInfo userInfo);

    /**
     * 批量添加用户
     *
     * @param userInfoList
     * @return
     */
    boolean addUserInfo(List<UserInfo> userInfoList);

    /**
     * 更新用户
     *
     * @param userInfo
     * @return
     */
    boolean updateUserInfo(UserInfo userInfo);

    /**
     * 删除用户
     *
     * @param userInfo
     * @return
     */
    boolean deleteUserInfo(UserInfo userInfo);

    /**
     * 通过用户账号检索用户详细信息
     *
     * @param account
     * @return
     */
    UserInfo selectUserinfo(String account);

    /**
     * 通过用户条件检索用户集合
     *
     * @param userInfo
     * @return
     */
    List<UserInfo> selectUserinfo(UserInfo userInfo);

    /**
     * 获取用户登录Token
     *
     * @param username
     * @param password
     * @return
     */
    String receiveToken(String username, String password);

    /**
     * 用户密码加密
     *
     * @param username
     * @param password
     * @return
     */
    String passwordEncryption(String username, String password);

}
