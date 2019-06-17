package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 ：经销商等级
 * @作者 ：gaoj
 * @日期 ：2018/1/24
 * @时间 ：14:07
 */
public enum DealerLevelEnum {

    PROVINCE(1, "省级经销商"),
    CITY(2, "市级经销商"),
    AREA(3, "区级经销商"),
    TERMINAL(4, "终端经销商");

    private int value;
    private String name;

    DealerLevelEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static DealerLevelEnum valueOf(int value) {
        DealerLevelEnum[] enums = values();
        for (DealerLevelEnum type : enums) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("未定义" + DealerLevelEnum.class.getSimpleName() + "，value=" + value + "的数据");
    }

    public static List<EnumTypeVO> getEnumList() {
        List<EnumTypeVO> list = new ArrayList<>();
        DealerLevelEnum[] values = DealerLevelEnum.values();
        for (DealerLevelEnum v : values) {
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
