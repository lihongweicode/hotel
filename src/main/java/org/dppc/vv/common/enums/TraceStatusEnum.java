package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @ClassName TraceStatusEnum
 * @Description 商品二维码交易状态
 * @Author lhw
 * @Data 2019/04/17 17:26
 * @Version 1.0
 **/
public enum TraceStatusEnum {

    STAY_IN(0, "待入库"),
    IN(1, "已入库"),
    OUT(2, "已交易");

    private String name;
    private int value;

    TraceStatusEnum(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public static TraceStatusEnum valueOf(int value) {
        TraceStatusEnum[] enums = values();
        for (TraceStatusEnum type : enums) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("未定义" + TraceStatusEnum.class.getSimpleName() + "，value=" + value + "的数据");
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
