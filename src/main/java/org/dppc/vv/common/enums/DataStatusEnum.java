package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 ：上传数据状态枚举
 * @作者 ：wyl
 * @日期 ：2018/1/24
 * @时间 ：14:07
 */
public enum DataStatusEnum {

    NORMAL(0, "正常数据"),
    WRONG(1, "问题数据");

    private int value;
    private String name;

    DataStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static DataStatusEnum valueOf(int value) {
        DataStatusEnum[] enums = values();
        for (DataStatusEnum type : enums) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("未定义" + DataStatusEnum.class.getSimpleName() + "，value=" + value + "的数据");
    }

    public static List<EnumTypeVO> getEnumList() {
        List<EnumTypeVO> list = new ArrayList<>();
        DataStatusEnum[] values = DataStatusEnum.values();
        for (DataStatusEnum v : values) {
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
