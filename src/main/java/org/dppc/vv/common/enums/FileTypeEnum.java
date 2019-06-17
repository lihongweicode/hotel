package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @描述 ：上传文件类型
 * @作者 ：gaoj
 * @日期 ：2017/8/4
 * @时间 ：7:55
 */
public enum FileTypeEnum {

    IMAGE(1, "检测报告", "/detection");

    private String name;
    private int value;
    private String path;//上传文件路径

    FileTypeEnum(int value, String name) {
        this.name = name;
        this.value = value;
    }

    FileTypeEnum(int value, String name, String path) {
        this.name = name;
        this.value = value;
        this.path = path;
    }

    public static FileTypeEnum valueOf(int value) {
        FileTypeEnum[] enums = values();
        for (FileTypeEnum type : enums) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("未定义" + FileTypeEnum.class.getSimpleName() + "，value=" + value + "的数据");
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
