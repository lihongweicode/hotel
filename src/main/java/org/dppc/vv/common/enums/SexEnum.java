package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 :  性别枚举
 * @作者 :	zhumh
 * @日期 :	2019/4/19
 * @时间 :	9:41
 */
public enum SexEnum {
    MAN(0,"男"),
    WOMAN(1,"女");

    private int value;
    private String name;

    SexEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static List<EnumTypeVO> getEnumList() {
        List<EnumTypeVO> list = new ArrayList<>();
        SexEnum[] values = SexEnum.values();
        for (SexEnum v : values) {
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
