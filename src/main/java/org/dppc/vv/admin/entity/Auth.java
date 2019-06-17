package org.dppc.vv.admin.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dppc.vv.common.annotation.PredicateInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @描述 : 权限
 * @作者 :	zhumh
 * @日期 :	2018/12/7
 * @时间 :	15:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sdipt_auth")
@Entity
@EqualsAndHashCode(callSuper=false)
@JsonRootName(value="auth")
public class Auth implements Serializable{

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    @ApiModelProperty(value = "主键id")
    private Long authId;

    @PredicateInfo(queryType = PredicateInfo.QueryType.INNER_LIKE)
    @Column(name = "auth_name")
    @ApiModelProperty(value = "权限名称")
    private String authName;

    @JsonManagedReference
    @ApiModelProperty(value = "所属菜单集合")
    @ManyToMany
    @JoinTable(name = "sdipt_auth_menu",
            joinColumns = @JoinColumn(name="auth_id"),
            inverseJoinColumns = @JoinColumn(name="menu_id"))
    @OrderBy("menuOrder asc")
    private List<Menu> menuList;

    @JsonManagedReference
    @ApiModelProperty(value = "所属接口集合")
    @ManyToMany
    @JoinTable(name = "sdipt_auth_api",
            joinColumns = @JoinColumn(name="auth_id"),
            inverseJoinColumns = @JoinColumn(name="api_id"))
    private List<Api> apiList;
}
