package org.dppc.vv.admin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dppc.vv.common.annotation.PredicateInfo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @描述 :  接口实体（存储接口）
 * @作者 :	zhumh
 * @日期 :	2018/11/30
 * @时间 :	16:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sdipt_api")
@Entity
public class Api implements Serializable {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @ApiModelProperty(value = "主键id")
    private Long apiId;

    @PredicateInfo(queryType = PredicateInfo.QueryType.INNER_LIKE)
    @ApiModelProperty("接口名称")
    @Column(name = "api_name")
    private String apiName;

    @ApiModelProperty("接口路径")
    @Column(name = "api_url")
    private String apiUrl;

    /**
     *  接口状态
     * 1、正常，2、禁用
     */
    @ApiModelProperty("接口状态(1.正常|2.禁用)")
    @Column(name = "api_status")
    private String apiStatus;
    /**
     * 接口请求方式 ：GET、POST、PUT、DELETE
     */
    @ApiModelProperty("接口请求方式 ：GET、POST、PUT、DELETE")
    @Column(name = "method")
    private String method;

    public Long getId() {
        return getApiId();
    }

    public String getName() {
        return getApiName();
    }

    public Long getPid() {
        return 0l;
    }

}
