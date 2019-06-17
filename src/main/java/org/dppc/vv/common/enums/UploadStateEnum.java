package org.dppc.vv.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 :  上传状态
 * @作者 :	lhw
 * @日期 :	2018/8/10
 * @时间 :	14:02
 */
public enum UploadStateEnum {

    NON_UPLOADE("未上传", 0),
    UPLODE("上传",1),
    UPLOADE_ERR("上传失败", 2);

    private String name;
    private int value;

    UploadStateEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static UploadStateEnum valueOf(int value) {
        UploadStateEnum[] enums = values();
        for (UploadStateEnum type : enums) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("未定义" + UploadStateEnum.class.getSimpleName() + "，value=" + value + "的数据");
    }

    public static UploadStateEnum nameOf(String name) {
        UploadStateEnum[] enums = values();
        for (UploadStateEnum type : enums) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未定义" + UploadStateEnum.class.getSimpleName() + "，name=" + name + "的数据");
    }

    public static List<EnumTypeVO> getEnumList() {
        List<EnumTypeVO> list = new ArrayList<>();
        UploadStateEnum[] values = UploadStateEnum.values();
        for (UploadStateEnum v : values) {
            list.add(v.getEnumTypeVO());
        }
        return list;
    }
    public static List<EnumTypeVO> getQueryList() {
        List<EnumTypeVO> list = new ArrayList<>();
        UploadStateEnum[] values = UploadStateEnum.values();
        for (UploadStateEnum v : values) {
            list.add(new EnumTypeVO(v.getName(), v.name()));
        }
        return list;
    }

    public EnumTypeVO getEnumTypeVO() {
        return new EnumTypeVO(getName(), String.valueOf(getValue()));
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

    @Override
    public String toString() {
        return "UploadStateEnum{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
