package org.dppc.vv.admin.service;

import org.dppc.vv.admin.entity.Auth;
import org.dppc.vv.admin.entity.Menu;
import org.dppc.vv.common.model.QueryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @描述 :  权限
 * @作者 :	zhumh
 * @日期 :	2018/12/3
 * @时间 :	15:51
 */
public interface AuthService {

    /**
     * 更新权限
     * @param auth
     */
    void update(Auth auth);

    /**
     * 删除权限
     * @param auth
     */
    void delete(Auth auth);

    /**
     * 权限分页
     * @param auth
     * @return
     */
    Page<Auth> getAuthPage(QueryDTO queryDTO, Auth auth);

    /**
     * 获取所有权限
     * @return
     */
    List<Auth> getAuthAll();

    /**
     * 保存权限
     * @param auth
     */
    void saveAuth(Auth auth);

    /**
     * 获取一条权限数据
     * @param auth
     * @return
     */
    Auth getAuth(Auth auth);

    /**
     * 获取所有权限
     * @return
     */
    List<Auth> getAllauth();
}
