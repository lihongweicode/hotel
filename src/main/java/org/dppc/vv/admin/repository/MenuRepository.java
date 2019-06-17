package org.dppc.vv.admin.repository;

import org.dppc.vv.admin.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @描述 :  菜单
 * @作者 :	zhumh
 * @日期 :	2018/12/3
 * @时间 :	15:48
 */
public interface MenuRepository  extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {

    @Query(value = "select m.* from sdipt_menu m where menu_parent is null", nativeQuery = true)
    List<Menu> findMenuByMenuParent();
}
