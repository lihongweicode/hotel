package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ProductUnitEnum
 * @Description 产品单位枚举
 * @Author lhw
 * @Data 2019/4/16 15:55
 * @Version 1.0
 **/
public enum ProductUnitEnum {

    BOTTLE(0, "瓶"),
    BAG(1, "袋"),
    BOX(2, "箱");

    private String name;
    private int value;

    ProductUnitEnum(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public static ProductUnitEnum valueOf(int value) {
        ProductUnitEnum[] enums = values();
        for (ProductUnitEnum type : enums) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("未定义" + ProductUnitEnum.class.getSimpleName() + "，value=" + value + "的数据");
    }
    public static List<EnumTypeVO> getEnumList() {
        List<EnumTypeVO> list = new ArrayList<>();
        ProductUnitEnum[] values = ProductUnitEnum.values();
        for (ProductUnitEnum v : values) {
            list.add(v.getEnumTypeVO());
        }
        return list;
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
