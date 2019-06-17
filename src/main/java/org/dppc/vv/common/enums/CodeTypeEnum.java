package org.dppc.vv.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 ：编码前缀类型
 * @作者 ：majt
 * @日期 ：2019/4/23
 * @时间 ：15:32
 */
public enum CodeTypeEnum {
    STACK("D", "垛码"),
    BOX("X","箱码"),
    PACK("B","袋码");

    private String code;
    private String name;

    CodeTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CodeTypeEnum valueof(String code) {
        CodeTypeEnum[] codeTypeEnums = values();
        for (CodeTypeEnum type : codeTypeEnums) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("codeTypeEnum，code=" + code + "的数据");
    }

    public static List<EnumTypeVO> getEnumList() {
        List<EnumTypeVO> list = new ArrayList<>();
        CodeTypeEnum[] values = CodeTypeEnum.values();
        for (CodeTypeEnum v : values) {
            list.add(v.getEnumTypeVO());
        }
        return list;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonValue
    public EnumTypeVO getEnumTypeVO() {
        return new EnumTypeVO(getName(), String.valueOf(getCode()));
    }

    @Override
    public String toString() {
        return "CodeTypeEnum{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }}
