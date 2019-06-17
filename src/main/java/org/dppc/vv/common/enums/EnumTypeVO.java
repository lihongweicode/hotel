package org.dppc.vv.common.enums;

/**
 * @描述 : 枚举类型VO
 * @作者 : yanggang
 * @日期 : 2017/1/16
 * @时间 : 15:19
 */
public class EnumTypeVO {

    private String name;
    private String value;

    public EnumTypeVO() {
    }

    public EnumTypeVO(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EnumTypeVO{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
