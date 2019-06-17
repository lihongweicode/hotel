package org.dppc.vv.common.help;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/**
 * @描述 :
 * @作者 :	zhumh
 * @日期 :	2018/11/29
 * @时间 :	13:49
 */
public class ValidateHelp {

    public static Map<String, Object> toStringJson(BindingResult result){
        Map<String, Object> map = new HashMap<String, Object>();
        for(FieldError fieldError : result.getFieldErrors()){
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return map;
    }
}
