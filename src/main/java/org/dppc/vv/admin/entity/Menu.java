package org.dppc.vv.admin.entity;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.dppc.vv.admin.enums.MenuTypeEnum;
import org.dppc.vv.common.annotation.PredicateInfo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @描述 :
 * @作者 :	zhumh
 * @日期 :	2018/11/30
 * @时间 :	17:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sdipt_menu")
@Entity
@JsonRootName(value="menu")
public class Menu implements Serializable {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    @ApiModelProperty(value = "主键id")
    private Long menuId;

    @PredicateInfo(queryType = PredicateInfo.QueryType.INNER_LIKE)
    @NonNull
    @Column(name = "menu_name")
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单LOGO")
    @Column(name = "menu_icon")
    private String  menuIcon;

    @ApiModelProperty(value = "父级菜单ID")
    @ManyToOne
    @JoinColumn(name = "menu_parent")
    private Menu menuParent;

    @NonNull
    @ApiModelProperty("菜单类型:1、菜单；2、按钮")
    @Column(name = "menu_type")
    @Enumerated(EnumType.ORDINAL)
    private MenuTypeEnum menuType;

    @ApiModelProperty("接口ID")
    @Column(name = "api_id")
    private Long apiId;

    @ApiModelProperty("菜单路径")
    @Column(name = "menu_url")
    private String menuUrl;

    @ApiModelProperty("菜单排序")
    @Column(name = "menu_order")
    private int menuOrder;

    public String getMenuParentName(){ //父级菜单名称
        return menuParent != null ? menuParent.getMenuName() : "";
    }

    public Long getId() {
        return getMenuId();
    }

    public String getName() {
        return getMenuName();
    }

    public Long getPid() {
        return menuParent != null ? getMenuParent().getMenuId() : null;
    }


}
