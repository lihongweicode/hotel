package org.dppc.vv.common.help;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @描述 :
 * @作者 :	Hou Chen
 * @日期 :	2017/8/9
 * @时间 :	10:49
 */
public class JsonHelp {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JsonHelp() {
    }

    public static String toJSon(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public static <T> T readJson(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <T> T readJson(String content, Class<?> collectionClass, Class... elementClasses) throws Exception {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        return objectMapper.readValue(content, javaType);
    }

    public static Object fromJson(String string,Class classType){
        try{
            return objectMapper.readValue(string,classType)  ;
        }catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
