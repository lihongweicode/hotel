package org.dppc.vv.admin.service.impl;

import org.dppc.vv.admin.entity.Menu;
import org.dppc.vv.admin.repository.MenuRepository;
import org.dppc.vv.admin.service.MenuService;
import org.dppc.vv.common.help.BeanHelp;
import org.dppc.vv.common.model.QueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @描述 :
 * @作者 :	zhumh
 * @日期 :	2018/12/4
 * @时间 :	10:01
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void update(Menu menu) {
        menuRepository.saveAndFlush(menu);
    }

    @Override
    public void delete(Menu menu) {
        menuRepository.delete(menu);
    }

    @Override
    public Page<Menu> getMenuPage(QueryDTO queryDTO, Menu menu) {
        Pageable pageable = queryDTO.gainPageable();
        queryDTO.setQuery(menu);
        Page<Menu> page = menuRepository.findAll((root, query1, cb) -> BeanHelp.getPredicate(root, queryDTO.getQuery(), cb), pageable);
        return page;
    }

    @Override
    public List<Menu> getMenuAll() {
        return menuRepository.findAll();
    }

    @Override
    public void saveMenu(Menu menu) {
        menuRepository.save(menu);
    }

    @Override
    public Menu getMenu(Menu menu) {
        return menuRepository.findOne(menu.getMenuId());
    }

    @Override
    public List<Menu> getAllParentMenu() {
        return menuRepository.findMenuByMenuParent();
    }
}
