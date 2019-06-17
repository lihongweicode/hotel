package org.dppc.vv.admin.service;

import org.dppc.vv.admin.entity.User;
import org.dppc.vv.common.model.QueryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @描述 :
 * @作者 :	zhumh
 * @日期 :	2018/11/30
 * @时间 :	9:57
 */
public interface UserService {
    /**
     * 保存用户信息
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 用户名和密码获取单条用户数据
     * @param username
     * @param password
     * @return
     */
    User getUserOne(String username,String password);

    /**
     * 用户数据分页
     * @param queryDTO
     * @param user
     * @return
     */
    Page<User> getUserPage(QueryDTO queryDTO, User user);

    /**
     * 删除用户
     * @param user
     */
    void deleteUser(User user);

    /**
     * 获取一条用户数据
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 通过用户名获取一个用户
     * @param user
     * @return
     */
    User getUserByUsername(User user);
}
