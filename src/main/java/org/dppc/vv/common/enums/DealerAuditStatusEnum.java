package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 ：经销商审核状态1未审核2审核通过3审核未通过
 * @作者 ：gaoj
 * @日期 ：2018/1/24
 * @时间 ：14:07
 */
public enum DealerAuditStatusEnum {

    HAVE_NOT(1, "待审核"),
    PASS(2, "审核通过"),
    NOT_PASS(3, "审核未通过");

    private int value;
    private String name;

    DealerAuditStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static DealerAuditStatusEnum valueOf(int value) {
        DealerAuditStatusEnum[] enums = values();
        for (DealerAuditStatusEnum type : enums) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("未定义" + DealerAuditStatusEnum.class.getSimpleName() + "，value=" + value + "的数据");
    }

    public static List<EnumTypeVO> getEnumList() {
        List<EnumTypeVO> list = new ArrayList<>();
        DealerAuditStatusEnum[] values = DealerAuditStatusEnum.values();
        for (DealerAuditStatusEnum v : values) {
            list.add(v.getEnumTypeVO());
        }
        return list;
    }

    @JsonValue
    public EnumTypeVO getEnumTypeVO() {
        return new EnumTypeVO(getName(), String.valueOf(getValue()));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
