package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @描述 ：1集团管理员2经销商管理员
 * @作者 ：gaoj
 * @日期 ：2017/8/4
 * @时间 ：7:55
 */
public enum UserTypeEnum {

    VV(1, "集团管理员"),
    DEALER(2, "经销商管理员");

    private String name;
    private Integer value;

    UserTypeEnum(Integer value, String name) {
        this.name = name;
        this.value = value;
    }

    public static UserTypeEnum valueOf(Integer value) {
        UserTypeEnum[] enums = values();
        for (UserTypeEnum type : enums) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未定义" + UserTypeEnum.class.getSimpleName() + "，value=" + value + "的数据");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
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
