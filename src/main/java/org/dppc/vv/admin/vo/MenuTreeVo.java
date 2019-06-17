package org.dppc.vv.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dppc.vv.admin.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 :
 * @作者 :	zhumh
 * @日期 :	2018/12/17
 * @时间 :	16:16
 */
@Data
@AllArgsConstructor
public class MenuTreeVo {
    private Long id;
    private Long pid;
    private String title;
    private String name;
    private String href;
    private String icon;
    private boolean open;
//    private boolean checked;
    private List<MenuTreeVo> children;

    public MenuTreeVo() {
    }

    public MenuTreeVo(Long id, Long pid, String title, String href, String icon, boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.name = title;
        this.href = href;
        this.icon = icon;
        this.open = spread;
//        this.checked = checked;
    }

    public static List<MenuTreeVo> generationTreeBySysMenu(List<Menu> menu){
        List<MenuTreeVo> trees = new ArrayList<>();
        List<MenuTreeVo> childTrees = new ArrayList<>();
        List<Menu> menus = new ArrayList<>();//第一次匹配不上的菜单
        MenuTreeVo menuTreeVo = null;
        for (Menu me : menu) {
            if(me.getMenuParent() == null){
                if(menuTreeVo != null){
                    menuTreeVo.setChildren(childTrees);
                    childTrees = new ArrayList<>();
                    trees.add(menuTreeVo);
                    menuTreeVo = null;

                }

                menuTreeVo = new MenuTreeVo(
                        me.getMenuId(),
                        0l,
                        me.getMenuName(),
                        me.getMenuUrl() != null &&  !me.getMenuUrl().equals("") ? "/html" + me.getMenuUrl() : me.getMenuUrl(),
                        me.getMenuIcon() == null ? "":me.getMenuIcon(),
                        true
                );
            }else if(menuTreeVo != null){
                if(me.getMenuParent() != null && me.getMenuParent().getMenuId().equals(menuTreeVo.getId())){
                    MenuTreeVo mtv = new MenuTreeVo(
                            me.getMenuId(),
                            me.getMenuParent().getMenuId(),
                            me.getMenuName(),
                            me.getMenuUrl() != null &&  !me.getMenuUrl().equals("") ? "/html" + me.getMenuUrl() : me.getMenuUrl(),
                            me.getMenuIcon() == null ? "":me.getMenuIcon(),
                            true
                    );
                    childTrees.add(mtv);
                }else{
                    menus.add(me);
                }
            }else{
                menus.add(me);
            }
        }
        if(menuTreeVo != null && !menuTreeVo.equals("")){
            menuTreeVo.setChildren(childTrees);
            childTrees = new ArrayList<>();
            trees.add(menuTreeVo);
            menuTreeVo = null;
        }

  /*      if(menus != null && menus.size() > 0){
            for (Menu me : menu) {
                for (MenuTreeVo mtv : trees) {
                    if(mtv.getId().equals(me.getMenuParent().getMenuId())){
                        MenuTreeVo m = new MenuTreeVo(
                                me.getMenuId(),
                                me.getMenuParent().getMenuId(),
                                me.getMenuName(),
                                me.getMenuUrl(),
                                 me.getMenuIcon() == null ? "":me.getMenuIcon(),
                                true
                        );
                        childTrees.add(m);
                    }
                }
            }
        }*/

        return trees;
    }
}
