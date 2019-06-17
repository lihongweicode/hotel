package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @描述 ：数据清洗规则类型：1非空，2数字，3数值范围>=,4数值范围<=
 * @作者 ：gaoj
 * @日期 ：2017/8/4
 * @时间 ：7:55
 */
public enum CleaningRuleEnum {

    NOT_NULL(1, "进场"),
    NUM(2, "出场"),
    GT(3, "数值范围>="),
    LT(4, "数值范围<=");

    private String name;
    private int value;

    CleaningRuleEnum(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public static CleaningRuleEnum valueOf(int value) {
        CleaningRuleEnum[] enums = values();
        for (CleaningRuleEnum type : enums) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("未定义" + CleaningRuleEnum.class.getSimpleName() + "，value=" + value + "的数据");
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
