package org.dppc.vv.common.help;

import org.dppc.vv.common.model.VerifyDTO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashSet;
import java.util.Set;

/**
 * @描述 :  统一错误处理
 * @作者 :	lhw
 * @日期 :	2018/12/5
 * @时间 :	18:00
 */
public class ErrorHelp {

    /**
     * @描述 :  错误消息转换
     * @作者 :	lhw
     * @日期 :	2018/12/5
     * @时间 :	18:06
     * @param : [result]
     * @return : java.util.Set<java.util.Map<java.lang.String,java.lang.String>>
     */
    public static Set<VerifyDTO> processor(BindingResult result) {
        Set<VerifyDTO> set = new HashSet<>();
        for (ObjectError error : result.getAllErrors()) {
            VerifyDTO verifyDTO = new VerifyDTO();
            verifyDTO.setObjectName(((FieldError)error).getField());
            verifyDTO.setDefaultMessage(error.getDefaultMessage());
            set.add(verifyDTO);
        }
        return set;
    }
}
