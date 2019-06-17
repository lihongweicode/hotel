package org.dppc.vv.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @描述 :  为生成{@link javax.persistence.criteria.Predicate }提供的注解
 * @作者 :  Zhao Yun
 * @日期 :  2017/08/07
 * @时间 :  16:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PredicateInfo {

    /** Zhao Yun 2017/8/7 基本对象的属性名 */
    String propName() default "";
    /** Zhao Yun 2017/8/7 查询方式 */
    QueryType queryType() default QueryType.BASIC;

    enum QueryType {
        /** Zhao Yun 2017/8/7 基本 */
        BASIC
        /** Zhao Yun 2017/8/7 大于等于 */
        , GREATER_THAN
        /** Zhao Yun 2017/8/7 小于等于 */
        , LESS_THAN
        /** Zhao Yun 2017/8/7 中模糊查询 */
        , INNER_LIKE
        /** Zhao Yun 2017/8/7 左模糊查询 */
        , LEFT_LIKE
        /** Zhao Yun 2017/8/7 右模糊查询 */
        , RIGHT_LIKE
        /** Zhao Yun 2017/8/7 小于 */
        , LESS_THAN_NQ
    }

}
