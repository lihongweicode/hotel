package org.dppc.vv.admin.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.dppc.vv.common.annotation.PredicateInfo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @描述：  用户实体
 * @作者： zhumh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sdipt_user")
@Entity
public class User implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @ApiModelProperty(value = "主键id")
    private Integer userId;
    /**
     * 用户名
     */
    @NonNull
    @PredicateInfo(queryType = PredicateInfo.QueryType.INNER_LIKE)
    @Column(name = "username", length = 20, unique = true)
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 密码 需加密
     */
    @NonNull
    @Column(name = "password", length = 200)
    @ApiModelProperty(value = "密码 加密")
    @JSONField(serialize = false)
    private String password;
    /**
     * 电子邮件
     */
    @Column(name = "email", length = 50)
    @ApiModelProperty(value = "电子邮件")
    private String email;
    /**
     * 用户姓名
     */
    @Column(name = "name", length = 20)
    @PredicateInfo(queryType = PredicateInfo.QueryType.INNER_LIKE)
    @ApiModelProperty(value = "用户姓名")
    private String name;
    /**
     * 用户图片地址
     */
    @Column(name = "image_url")
    @ApiModelProperty(value = "用户图片地址")
    private String imageUrl;
    /**
     * 是否启用 1：启用  0：禁用
     */
    @Column(name = "enable")
    @ApiModelProperty(value = "是否启用 1：启用  0：禁用")
    private Integer enable;
    /**
     * 联系电话
     */
    @Column(name = "phone")
    @ApiModelProperty(value = "联系电话")
    private String phone;
    /**
     * 住址
     */
    @Column(name = "address")
    @ApiModelProperty(value = "住址")
    private String address;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    private String createTime;

    /**
     * 所属权限
     */
    @ApiModelProperty(value = "所属权限")
    @ManyToOne()
    @JoinColumn(name = "auth_id")
    private Auth auth;

    /**
     * 用户类别: UserTypeEnum 1集团管理员2经销商管理员
     */
    @Column(name = "type")
    @PredicateInfo
    private Integer type;

    /**
     * 经销商编码
     */
    @Column(name = "dealer_code", length = 50)
    private String dealerCode;

}
