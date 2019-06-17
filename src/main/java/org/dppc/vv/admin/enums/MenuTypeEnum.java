package org.dppc.vv.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @描述 :  菜单类型
 * @作者 :	zhumh
 * @日期 :	2018/12/7
 * @时间 :	15:15
 */
public enum MenuTypeEnum {
    MENU(0,"菜单"),
    BUTTON(1,"按钮");

    private int value;
    private String name;

    MenuTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @JsonValue
    public Map<String,Object> getEnum(){
        Map<String,Object> m = new HashMap<>();
        for(MenuTypeEnum me : MenuTypeEnum.values()){
            if(me.getValue() == getValue()){
                m.put("key",me.name());
                m.put("value",me.getValue());
                m.put("name",me.getName());
            }
        }
        return m;
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
