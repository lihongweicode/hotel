package org.dppc.vv.common.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @描述 :  返回信息
 * @作者 :
 * @日期 :	2017/10/16
 * @时间 :	15:57
 */
public class ResultDTO {
    private Object msg;
    private Integer code;
    private Integer icon;
    private Map<String, Object> data = new HashMap<String, Object>();

    private ResultDTO(Integer code, Object msg, Integer icon) {
        this.code = code;
        this.msg = msg;
        this.icon = icon;
    }

    //请求成功
    public static ResultDTO success() {
        return new ResultDTO(200, "请求成功!", 1);
    }

    //请求失败
    public static ResultDTO fail() {
        return new ResultDTO(400, "请求失败!", 2);
    }

    //验证异常
    public static ResultDTO verifyFail() {
        return new ResultDTO(410, "验证失败!", 2);
    }

    //自定义异常
    public static ResultDTO customFail(Object msg) {
        return new ResultDTO(420, msg, 2);
    }

    //Token异常
    public static ResultDTO tokenFail(Object msg) {
        return new ResultDTO(430, msg, 2);
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public ResultDTO putDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }
}
