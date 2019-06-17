package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @描述        :   环节编码枚举（注意微信）
 * @作者        :	薛涛
 * @版本        :	v1.0
 * @日期        :	2018/1/17 10:00
 */
public enum TacheCodeTypeEnum {


    PLANTATION("01", "种植"),
    CULTIVATION("00", "养殖"),
    ABATTOIR("23", "屠宰节点"),
    PROCESS_NODE("10", "加工"),
    STORAGE_AND_DELIVERY("21", "仓储配送"),
    SUPER_MARKET("24", "超市"),
    TEAM_BUY("30", "团体消费"),
    WHOLESALE("20","批发"),
    RETAIL("22","零售"),
    SUPPLIER("25","供应商"),//拓展节点所用
    HARVESTER("26","收货商");//拓展节点所用


    /** 节点编码 */
    private String value;
    /** 节点名称 */
    private String name;

    TacheCodeTypeEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }
    public static TacheCodeTypeEnum valueof(String value) {
        TacheCodeTypeEnum[] nodeTypeEnum = values();
        for (TacheCodeTypeEnum type : nodeTypeEnum) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return  null;
       // throw new IllegalArgumentException("nodeTypeEnum，value=" + value + "的数据");
    }

    @JsonValue
    public EnumTypeVO getEnumTypeVO() {
        return new EnumTypeVO(getName(), String.valueOf(getValue()));
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
