package org.dppc.vv.common.serialize;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @描述 :  JSON日期序列化，格式yyyy-MM-dd
 * @作者 :  Zhao Yun
 * @日期 :  2017/08/07
 * @时间 :  13:01
 */
public class DateSerialize extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(DateUtil.format(value, DatePattern.NORM_DATE_PATTERN));
    }

}
