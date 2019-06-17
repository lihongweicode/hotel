package org.dppc.vv.admin.service;

import org.dppc.vv.admin.entity.Menu;
import org.dppc.vv.common.model.QueryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @描述 :  菜单
 * @作者 :	zhumh
 * @日期 :	2018/12/3
 * @时间 :	15:51
 */
public interface MenuService {

    /**
     * 更新菜单
     * @param menu
     */
    void update(Menu menu);

    /**
     * 删除菜单
     * @param menu
     */
    void delete(Menu menu);

    /**
     * 菜单分页
     * @param menu
     * @return
     */
    Page<Menu> getMenuPage(QueryDTO queryDTO, Menu menu);

    /**
     * 获取所有菜单
     * @return
     */
    List<Menu> getMenuAll();

    /**
     * 保存菜单
     * @param menu
     */
    void saveMenu(Menu menu);

    /**
     * 获取一条菜单数据
     * @param menu
     * @return
     */
    Menu getMenu(Menu menu);

    /**
     * 获取所有一级菜单
     * @return
     */
    List<Menu> getAllParentMenu();
}
