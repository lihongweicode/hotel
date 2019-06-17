package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @描述 ：进出类型
 * @作者 ：wyl
 * @日期 ：2017/8/4
 * @时间 ：7:55
 */
public enum InOutTypeEnum {

    IN(1, "进场"),
    OUT(2, "出场");

    private String name;
    private int value;

    InOutTypeEnum(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public static InOutTypeEnum valueOf(int value) {
        InOutTypeEnum[] enums = values();
        for (InOutTypeEnum type : enums) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("未定义" + InOutTypeEnum.class.getSimpleName() + "，value=" + value + "的数据");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @JsonValue
    public EnumTypeVO getEnumTypeVO() {
        return new EnumTypeVO(getName(), String.valueOf(getValue()));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

}
