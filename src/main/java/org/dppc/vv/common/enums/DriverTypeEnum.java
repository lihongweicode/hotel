package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import org.dppc.vv.admin.enums.MenuTypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述 :  驾驶证类型
 * @作者 :	zhumh
 * @日期 :	2019/4/19
 * @时间 :	9:41
 */
public enum DriverTypeEnum {
    A1(0,"A1"), A2(1,"A2"), A3(2,"A3"),
    B1(3,"B1"), B2(4,"B2"), C1(5,"C1"),
    C2(6,"C2"), C3(7,"C3"), C4(8,"C4"),
    D(9,"D"), E(10,"E"), F(11,"F"),
    M(12,"M"), N(13,"N"), P(14,"P");

    private int value;
    private String name;

    DriverTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static List<EnumTypeVO> getEnumList() {
        List<EnumTypeVO> list = new ArrayList<>();
        DriverTypeEnum[] values = DriverTypeEnum.values();
        for (DriverTypeEnum v : values) {
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
