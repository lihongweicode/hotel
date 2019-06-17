package org.dppc.vv.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @描述 :  验证信息
 * @作者 :	lhw
 * @日期 :	2018/12/6
 * @时间 :	8:48
 */
@Data
@NoArgsConstructor
public class VerifyDTO {
    //属性名
    private String objectName;
    //验证信息
    private String defaultMessage;
}
