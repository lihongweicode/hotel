package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @ClassName InStatusEnum
 * @Description 入库状态状态
 * @Author lhw
 * @Data 2019/04/17 17:26
 * @Version 1.0
 **/
public enum InTypeEnum {
    /** 整垛 */
    STACK(0, "整垛"),
    /** 整箱 */
    BOX(1, "整箱"),
    /** 单品 */
    SINGLE(2, "单品");

    private String name;
    private int value;

    InTypeEnum(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public static InTypeEnum valueOf(int value) {
        InTypeEnum[] enums = values();
        for (InTypeEnum type : enums) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("未定义" + InTypeEnum.class.getSimpleName() + "，value=" + value + "的数据");
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
