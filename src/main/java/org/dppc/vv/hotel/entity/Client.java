package org.dppc.vv.hotel.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dppc.vv.common.annotation.PredicateInfo;

import javax.persistence.*;

/**
 * @Description 客户信息表
 * @Author lhw
 * @Data 2019/06/13 18:10
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel_client")
public class Client implements Serializable {

    @Id
    @GeneratedValue
    /** 客户Id */
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

    /** 密码 */
    @Column(name = "password", length = 20)
    private String password;

    /** 剩余数量 */
    @Column(name = "surplus_num")
    private Double surplusNum;

    /** 账户余额 */
    @Column(name = "surplus_price")
    private Double surplusPrice;

    /** 改变数量 */
    @Transient
    private Double changeNum;

    /** 改变余额 */
    @Transient
    private Double changePrice;

    /** 是否删除 0:未删除  1：已删除 */
    @Column(name = "is_delete")
    @PredicateInfo
    private Integer isDelete;

    @Override
    public String toString() {
        return  "客户姓名： " + clientName +
                ", 手机号： " + phone +
                ", 密码: " + password +
                ", 剩余数量： " + surplusNum +
                ", 账户余额： " + surplusPrice + "\n";
    }
}
