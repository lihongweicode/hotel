package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 ：订单状态（1待审核、2审核通过、3审核未通过、4已出库、5配送中、6已完成）
 * @作者 ：gaoj
 * @日期 ：2019/1/24
 * @时间 ：7:55
 */
public enum OrderStatusEnum {

    NOT_AUDIT(1, "待审核"),
    PASS(2, "审核通过"),
    NOT_PASS(3, "审核未通过"),
    OUT_STOCK(4, "已出库"),
    DELIVER(5, "配送中"),
    END(6, "已完成");

    private String name;
    private int value;

    OrderStatusEnum(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public static OrderStatusEnum valueOf(int value) {
        OrderStatusEnum[] enums = values();
        for (OrderStatusEnum type : enums) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("未定义" + OrderStatusEnum.class.getSimpleName() + "，value=" + value + "的数据");
    }

    public static List<EnumTypeVO> getEnumList() {
        List<EnumTypeVO> list = new ArrayList<>();
        OrderStatusEnum[] values = OrderStatusEnum.values();
        for (OrderStatusEnum v : values) {
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
