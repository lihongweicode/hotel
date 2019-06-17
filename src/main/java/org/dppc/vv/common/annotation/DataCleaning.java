package org.dppc.vv.common.annotation;

import java.lang.annotation.*;

/**
 * @描述 :  用于标记可供数据清洗的字段
 * @作者 :	GAOJ
 * @日期 :	2018/12/05
 * @时间 :	9:41
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataCleaning {

	 String value() default "";

}
