package org.dppc.vv.hotel.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dppc.vv.common.annotation.PredicateInfo;

import javax.persistence.*;

/**
 * @Description
 * @Author lhw
 * @Data 2019/06/13 18:10
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel_operation_log")
public class OperationLog implements Serializable {

    @Id
    @GeneratedValue
    /**  */
    @Column(name = "log_id")
    private Integer logId;

    /** 客户ID */
    @Column(name = "client_id")
    private Integer clientId;

    /** 客户姓名 */
    @PredicateInfo(queryType = PredicateInfo.QueryType.INNER_LIKE)
    @Column(name = "client_name", length = 30)
    private String clientName;

    /** 手机号 */
    @PredicateInfo(queryType = PredicateInfo.QueryType.INNER_LIKE)
    @Column(name = "phone", length = 20)
    private String phone;

    /** 操作信息 */
    @Column(name = "operation_info", length = 200)
    private String operationInfo;

    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
}
